package com.jahedul.productparserservice.service;

import com.jahedul.productparserservice.entity.Product;
import com.jahedul.productparserservice.model.FileUploadSummaryResource;
import com.jahedul.productparserservice.repository.ProductRepository;
import com.jahedul.productparserservice.util.ProductExcelParser;
import com.jahedul.productparserservice.util.ProductValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DefaultProductServiceTest {
    @Mock
    private FileUploadSummaryService fileUploadSummaryService;
    @Mock
    private ProductExcelParser productExcelParser;
    @Mock
    private ProductValidator productValidator;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private MultipartFile mockFile;

    private DefaultProductService productService;

    private Product product1;
    private static final String sku1 = "ELE-WATCH-M-001";
    private static final String nonExistingSku = "ELE-WATCH-M-002";
    private Product product2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new DefaultProductService(productRepository, fileUploadSummaryService, productExcelParser, productValidator);


        product1 = new Product();
        product1.setSku(sku1);
        product1.setTitle("Watch");
        product1.setPrice(BigDecimal.valueOf(10000.00));
        product1.setQuantity(10);

        product2 = new Product();
        product2.setSku("ELE-PHO-M-002");
        product2.setTitle("Smartphone");
        product2.setPrice(BigDecimal.valueOf(20000.00));
        product2.setQuantity(10);
    }

    @Test
    public void testGetProductBySku_forValidSku_shouldReturnTheResource() {
        when(productRepository.findById(sku1)).thenReturn(Optional.of(product1));
        Optional<Product> foundProduct = productService.getProductBySku(sku1);

        assertTrue(foundProduct.isPresent(), "Expected a product to be found");
        assertEquals(sku1, foundProduct.get().getSku());
        assertEquals("Watch", foundProduct.get().getTitle());
        verify(productRepository).findById(sku1);
    }

    @Test
    public void testGetProductBySku_forInvalidSku_shouldReturnNull() {
        when(productRepository.findById(nonExistingSku)).thenReturn(Optional.empty());
        Optional<Product> foundProduct = productService.getProductBySku(nonExistingSku);
        assertFalse(foundProduct.isPresent(), "Expected no product to be found");
        verify(productRepository).findById(nonExistingSku);
    }

    @Test
    public void testUploadProducts_FromValidFile_UploadShouldBeDone() throws IOException {
        List<Product> newProducts = Arrays.asList(product1, product2);
        when(productExcelParser.parseFileToProducts(mockFile)).thenReturn(newProducts);
        Set<String> existingSkus = new HashSet<>(Collections.singletonList(sku1));
        when(productRepository.findAllSkus()).thenReturn(new ArrayList<>(existingSkus));
        when(productRepository.findAllById(Collections.singleton(sku1))).thenReturn(Collections.singletonList(product1));

        productService.uploadProductsFromFile(mockFile);

        verify(productValidator, times(2)).validate(any(Product.class));
        verify(productRepository, times(1)).saveAll(anyList());
        verify(fileUploadSummaryService).saveFileUploadSummary(any(FileUploadSummaryResource.class));
    }

    @Test
    public void testUploadProductsFromFile_whenNoNewProducts_shouldNotCallSaveAll() throws IOException {
        List<Product> newProducts = Arrays.asList(product1);
        when(productExcelParser.parseFileToProducts(mockFile)).thenReturn(newProducts);
        Set<String> existingSkus = new HashSet<>(Arrays.asList(sku1));
        when(productRepository.findAllSkus()).thenReturn(new ArrayList<>(existingSkus));
        when(productRepository.findAllById(existingSkus)).thenReturn(Collections.singletonList(product1));

        productService.uploadProductsFromFile(mockFile);

        verify(productRepository, never()).saveAll(anyList());
        verify(fileUploadSummaryService).saveFileUploadSummary(any(FileUploadSummaryResource.class));
    }

    @Test
    public void testUploadProductsFromFile_ForEachProduct_ValidationWillBeInvoked() throws IOException {
        Product product = new Product();
        product.setTitle("Watch");
        product.setPrice(BigDecimal.valueOf(10000.00));
        product.setQuantity(10);

        when(productExcelParser.parseFileToProducts(mockFile)).thenReturn(List.of(product, product1, product2));
        when(productRepository.findAllSkus()).thenReturn(Collections.emptyList());

        productService.uploadProductsFromFile(mockFile);

        verify(productValidator).validate(product);
        verify(productValidator).validate(product1);
        verify(productValidator).validate(product2);

    }


    @Test
    public void testGetAllProducts_withProducts_shouldReturnPagedProducts() {
        Pageable pageable = PageRequest.of(0, 50);
        Page<Product> productPage = new PageImpl<>(Arrays.asList(product1, product2));
        when(productRepository.findAll(pageable)).thenReturn(productPage);
        Page<Product> products = productService.getAllProducts(pageable);

        assertNotNull(products);
        assertEquals(2, products.getContent().size());
        assertEquals(sku1, products.getContent().get(0).getSku());
        assertEquals("ELE-PHO-M-002", products.getContent().get(1).getSku());

        verify(productRepository).findAll(pageable);
    }

    @Test
    public void testGetAllProducts_withNoProducts_shouldReturnEmptyPage() {
        Pageable pageable = PageRequest.of(0, 50);
        Page<Product> emptyProductPage = new PageImpl<>(Collections.emptyList());
        when(productRepository.findAll(pageable)).thenReturn(emptyProductPage);
        Page<Product> result = productService.getAllProducts(pageable);

        assertNotNull(result);
        assertTrue(result.getContent().isEmpty(), "Expected no products to be found");
        verify(productRepository).findAll(pageable);
    }
}
