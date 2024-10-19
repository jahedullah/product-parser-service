package com.jahedul.productparserservice.service;

import com.jahedul.productparserservice.entity.Product;
import com.jahedul.productparserservice.exception.DuplicateSkuException;
import com.jahedul.productparserservice.model.FileUploadSummaryResource;
import com.jahedul.productparserservice.repository.ProductRepository;
import com.jahedul.productparserservice.util.ExceptionParser;
import com.jahedul.productparserservice.util.ProductExcelParser;
import com.jahedul.productparserservice.util.ProductUploadResult;
import com.jahedul.productparserservice.util.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;
    private final FileUploadSummaryService fileUploadSummaryService;
    private final ProductExcelParser productExcelParser;
    private final ProductValidator productValidator;

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> getProductBySku(String sku) {
        return productRepository.findById(sku);
    }


    @Override
    @Transactional
    public void uploadProductsFromFile(MultipartFile file) throws IOException {
        List<Product> newProducts = productExcelParser.parseFileToProducts(file);

        Set<String> existingSkus = new HashSet<>(productRepository.findAllSkus());

        Set<String> newProductSkus = newProducts.stream()
                .map(Product::getSku)
                .collect(Collectors.toSet());

        Set<String> duplicateSkus = newProductSkus.stream()
                .filter(existingSkus::contains)
                .collect(Collectors.toSet());

        List<Product> existingProducts = productRepository.findAllById(duplicateSkus);
        Map<String, Product> existingProductMap = existingProducts.stream()
                .collect(Collectors.toMap(Product::getSku, p -> p));

        ProductUploadResult productUploadResult = processProducts(newProducts, existingProductMap);

        saveProducts(productUploadResult.getProductsToAdd(), productUploadResult.getProductsToUpdate());
        saveFileUploadSummary(file.getOriginalFilename(), productUploadResult);
    }

    private ProductUploadResult processProducts(List<Product> newProducts, Map<String, Product> existingProductMap) {
        List<Product> productsToAdd = new ArrayList<>();
        List<Product> productsToUpdate = new ArrayList<>();
        int productsAdded = 0;
        int productsUpdated = 0;

        for (Product newProduct : newProducts) {
            productValidator.validate(newProduct);

            Product existingProduct = existingProductMap.get(newProduct.getSku());
            if (existingProduct != null) {
                if (productHasChanged(existingProduct, newProduct)) {
                    updateExistingProduct(existingProduct, newProduct);
                    productsToUpdate.add(existingProduct);
                    productsUpdated++;
                }
            } else {
                productsToAdd.add(newProduct);
                productsAdded++;
            }
        }


        return ProductUploadResult.builder()
                .productsToAdd(productsToAdd)
                .productsToUpdate(productsToUpdate)
                .productsAdded(productsAdded)
                .productsUpdated(productsUpdated)
                .build();
    }

    private boolean productHasChanged(Product existingProduct, Product newProduct) {
        return !existingProduct.getTitle().equals(newProduct.getTitle())
                || !existingProduct.getPrice().equals(newProduct.getPrice())
                || existingProduct.getQuantity() != newProduct.getQuantity();
    }

    private void updateExistingProduct(Product existingProduct, Product newProduct) {
        existingProduct.setTitle(newProduct.getTitle());
        existingProduct.setPrice(newProduct.getPrice());
        existingProduct.setQuantity(newProduct.getQuantity());
        existingProduct.setLastUpdated(ZonedDateTime.now());
    }

    private void saveProducts(List<Product> productsToAdd, List<Product> productsToUpdate) {
        try {
            Stream.of(productsToAdd, productsToUpdate)
                    .filter(list -> !list.isEmpty())
                    .forEach(productRepository::saveAll);

        } catch (DuplicateKeyException ex) {
            String sku = ExceptionParser.extractSkuFromExceptionMessage(ex.getMessage());
            throw new DuplicateSkuException(sku);
        }
    }

    private void saveFileUploadSummary(String fileName, ProductUploadResult result) {
        FileUploadSummaryResource summaryResource = new FileUploadSummaryResource();
        summaryResource.setFileName(fileName);
        summaryResource.setUploadDateTime(ZonedDateTime.now());
        summaryResource.setProductsAdded(result.getProductsAdded());
        summaryResource.setProductsUpdated(result.getProductsUpdated());

        fileUploadSummaryService.saveFileUploadSummary(summaryResource);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

}
