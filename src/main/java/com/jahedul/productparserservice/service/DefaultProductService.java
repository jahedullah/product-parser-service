package com.jahedul.productparserservice.service;

import com.jahedul.productparserservice.entity.Product;
import com.jahedul.productparserservice.model.ProductResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductService implements ProductService{
    @Override
    public Product getProductBySku(String sku) {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(String sku, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(String sku) {

    }
}
