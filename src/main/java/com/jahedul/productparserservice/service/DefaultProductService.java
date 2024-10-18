package com.jahedul.productparserservice.service;

import com.jahedul.productparserservice.model.ProductResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductService implements ProductService{
    @Override
    public ProductResource getProductBySku(String sku) {
        return null;
    }

    @Override
    public List<ProductResource> getProducts() {
        return null;
    }
}
