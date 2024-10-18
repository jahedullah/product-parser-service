package com.jahedul.productparserservice.service;

import com.jahedul.productparserservice.model.FileUploadSummaryResource;

public interface FileUploadSummaryService {
    FileUploadSummaryResource getSummaryOfLastUploadedFile();
}
