package com.jahedul.productparserservice.service;

import com.jahedul.productparserservice.model.FileUploadSummaryResource;
import org.springframework.stereotype.Service;

@Service
public class DefaultFileUploadSummaryService implements FileUploadSummaryService{
    @Override
    public FileUploadSummaryResource getSummaryOfLastUploadedFile() {
        return null;
    }
}
