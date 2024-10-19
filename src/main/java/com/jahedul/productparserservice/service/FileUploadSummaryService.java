package com.jahedul.productparserservice.service;

import com.jahedul.productparserservice.entity.FileUploadSummary;
import com.jahedul.productparserservice.model.FileUploadSummaryResource;

public interface FileUploadSummaryService {
    FileUploadSummary getSummaryOfLastUploadedFile();
    void saveFileUploadSummary(FileUploadSummaryResource fileUploadSummaryResource);
}
