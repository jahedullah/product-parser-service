package com.jahedul.productparserservice.util;

import com.jahedul.productparserservice.entity.Product;
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
