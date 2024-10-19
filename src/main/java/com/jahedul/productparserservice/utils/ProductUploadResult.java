package com.jahedul.productparserservice.utils;

import com.jahedul.productparserservice.entities.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProductUploadResult {
    List<Product> productsToAdd;
    List<Product> productsToUpdate;
    int productsAdded;
    int productsUpdated;
}
