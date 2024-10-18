package com.jahedul.productparserservice.service;

import com.jahedul.productparserservice.model.ProductResource;

import java.util.List;

public interface ProductService {
    ProductResource getProductBySku(String sku);
    List<ProductResource> getProducts();
}
