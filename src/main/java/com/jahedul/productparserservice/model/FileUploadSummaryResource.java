package com.jahedul.productparserservice.model;

import java.time.ZonedDateTime;

public class FileUploadSummaryResource {
    private Long id;
    private String fileName;
    private ZonedDateTime uploadDateTime;
    private int productsAdded;
    private int productsUpdated;
    private int productsDeleted;
}
