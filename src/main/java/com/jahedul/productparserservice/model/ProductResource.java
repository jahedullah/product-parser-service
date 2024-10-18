package com.jahedul.productparserservice.model;

import lombok.Builder;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Builder
@Setter
public class ProductResource {
    private String sku;

    private String title;

    private BigDecimal price;

    private int quantity;

    private ZonedDateTime lastUpdated;

    private Long version;
}
