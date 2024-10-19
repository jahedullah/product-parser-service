package com.jahedul.productparserservice.utils;

import com.jahedul.productparserservice.entities.Product;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ProductValidator {
    private final Validator validator;

    public void validate(Product product) {
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException(buildValidationErrorMessage(product, violations));
        }
    }

    private String buildValidationErrorMessage(Product product, Set<ConstraintViolation<Product>> violations) {
        StringBuilder errorMessage = new StringBuilder("Validation error for SKU: " + product.getSku());
        for (ConstraintViolation<Product> violation : violations) {
            errorMessage.append(" - ").append(violation.getMessage());
        }
        return errorMessage.toString();
    }
}
