package com.jahedul.productparserservice.exception;

import lombok.Getter;

@Getter
public class DuplicateSkuException extends RuntimeException {
    private final String sku;

    public DuplicateSkuException(String sku) {
        super("Duplicate SKU found: " + sku);
        this.sku = sku;
    }
}
