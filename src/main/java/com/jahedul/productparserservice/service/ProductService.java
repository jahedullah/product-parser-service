package com.jahedul.productparserservice.service;

import com.jahedul.productparserservice.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductBySku(String sku);
    List<Product> getProducts();
    void uploadProductsFromFile(MultipartFile file) throws IOException;
    Product saveProduct(Product product);
    Product updateProduct(String sku, Product product);
    void deleteProduct(String sku);
}
