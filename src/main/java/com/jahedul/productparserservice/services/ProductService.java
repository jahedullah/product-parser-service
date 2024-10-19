package com.jahedul.productparserservice.services;

import com.jahedul.productparserservice.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductBySku(String sku);

    void uploadProductsFromFile(MultipartFile file) throws IOException;

    Page<Product> getAllProducts(Pageable pageable);
}
