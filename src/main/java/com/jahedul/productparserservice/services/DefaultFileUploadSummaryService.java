package com.jahedul.productparserservice.services;

import com.jahedul.productparserservice.entities.FileUploadSummary;
import com.jahedul.productparserservice.models.FileUploadSummaryResource;
import com.jahedul.productparserservice.repositories.FileUploadSummaryRepository;
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
