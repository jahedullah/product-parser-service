package com.jahedul.productparserservice.service;

import com.jahedul.productparserservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductBySku(String sku);

    void uploadProductsFromFile(MultipartFile file) throws IOException;

    Page<Product> getAllProducts(Pageable pageable);
}
