package com.jahedul.productparserservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @NotBlank(message = "sku cannot be empty")
    @Size(min = 5, max = 30, message = "please keep the sku length between 5 and 30")
    private String sku;

    @Column(nullable = false)
    @NotBlank(message = "please provide a title for the product")
    private String title;

    @Column(nullable = false)
    @NotNull(message = "price cannot be empty")
    @Positive(message = "price must be a positive value")
    private BigDecimal price;

    @Column(nullable = false)
    @PositiveOrZero(message = "quantity must be zero or positive")
    private int quantity;

    @Column(nullable = false)
    private ZonedDateTime lastUpdated;

    @Version
    private Long version;
}
