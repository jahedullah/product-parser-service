package com.jahedul.productparserservice.service;

import com.jahedul.productparserservice.entity.FileUploadSummary;
import com.jahedul.productparserservice.model.FileUploadSummaryResource;
import org.springframework.stereotype.Service;

@Service
public class DefaultFileUploadSummaryService implements FileUploadSummaryService {
    @Override
    public FileUploadSummary getSummaryOfLastUploadedFile() {
        return null;
    }

    @Override
    public FileUploadSummary saveFileUploadSummary(FileUploadSummaryResource fileUploadSummaryResource) {
        return null;
    }
}
