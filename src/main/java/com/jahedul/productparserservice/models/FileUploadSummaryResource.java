package com.jahedul.productparserservice.models;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class FileUploadSummaryResource {
    private Long id;
    private String fileName;
    private ZonedDateTime uploadDateTime;
    private int productsAdded;
    private int productsUpdated;
}
