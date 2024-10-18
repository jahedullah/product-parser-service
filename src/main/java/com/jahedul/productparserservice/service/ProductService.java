package com.jahedul.productparserservice.service;

import com.jahedul.productparserservice.entity.Product;
import com.jahedul.productparserservice.model.ProductResource;

import java.util.List;

public interface ProductService {
    Product getProductBySku(String sku);
    List<Product> getProducts();
    Product saveProduct(Product product);
    Product updateProduct(String sku, Product product);
    void deleteProduct(String sku);
}
