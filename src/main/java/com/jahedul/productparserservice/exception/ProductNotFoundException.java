package com.jahedul.productparserservice.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String msg) {
        super(msg);
    }
}
