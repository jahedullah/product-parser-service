package com.jahedul.productparserservice.repository;

import com.jahedul.productparserservice.entity.FileUploadSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadSummaryRepository extends JpaRepository<FileUploadSummary, Long> {
}
