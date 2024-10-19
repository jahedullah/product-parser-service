package com.jahedul.productparserservice.services;

import com.jahedul.productparserservice.entities.FileUploadSummary;
import com.jahedul.productparserservice.models.FileUploadSummaryResource;

public interface FileUploadSummaryService {
    FileUploadSummary getSummaryOfLastUploadedFile();
    void saveFileUploadSummary(FileUploadSummaryResource fileUploadSummaryResource);
}
