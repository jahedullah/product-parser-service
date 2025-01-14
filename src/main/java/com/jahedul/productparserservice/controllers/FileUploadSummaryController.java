package com.jahedul.productparserservice.controllers;

import com.jahedul.productparserservice.entities.FileUploadSummary;
import com.jahedul.productparserservice.exceptions.SummaryNotFoundException;
import com.jahedul.productparserservice.services.FileUploadSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/uploads")
@RequiredArgsConstructor
public class FileUploadSummaryController {
    private final FileUploadSummaryService fileUploadSummaryService;

    @GetMapping("/last-summary")
    public ResponseEntity<FileUploadSummary> getLastUploadSummary(){
        FileUploadSummary fileUploadSummary = fileUploadSummaryService.getSummaryOfLastUploadedFile();
        if(Objects.isNull(fileUploadSummary)){
            throw new SummaryNotFoundException("No last file upload summary found. Did you upload any file yet?");
        }
        return ResponseEntity.ok(fileUploadSummary);
    }
}
