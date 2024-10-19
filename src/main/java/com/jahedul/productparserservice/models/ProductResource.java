package com.jahedul.productparserservice.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
public class ProductResource {
    private String sku;

    private String title;

    private BigDecimal price;

    private int quantity;

    private ZonedDateTime lastUpdated;

    private Long version;
}
