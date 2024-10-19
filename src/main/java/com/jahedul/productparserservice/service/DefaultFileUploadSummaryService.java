package com.jahedul.productparserservice.service;

import com.jahedul.productparserservice.entity.FileUploadSummary;
import com.jahedul.productparserservice.model.FileUploadSummaryResource;
import com.jahedul.productparserservice.repository.FileUploadSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class DefaultFileUploadSummaryService implements FileUploadSummaryService {
    private final FileUploadSummaryRepository fileUploadSummaryRepository;

    @Override
    @Transactional(readOnly = true)
    public FileUploadSummary getSummaryOfLastUploadedFile() {
        return fileUploadSummaryRepository.findFirstByOrderByUploadDateTimeDesc();
    }

    @Override
    @Transactional
    public void saveFileUploadSummary(FileUploadSummaryResource fileUploadSummaryResource) {
        FileUploadSummary fileUploadSummary = new FileUploadSummary();
        fileUploadSummary.setFileName(fileUploadSummaryResource.getFileName());
        fileUploadSummary.setProductsUpdated(fileUploadSummaryResource.getProductsUpdated());
        fileUploadSummary.setProductsAdded(fileUploadSummaryResource.getProductsAdded());
        fileUploadSummary.setUploadDateTime(ZonedDateTime.now());

        fileUploadSummaryRepository.save(fileUploadSummary);
        fileUploadSummaryResource.setId(fileUploadSummary.getId());
    }
}
