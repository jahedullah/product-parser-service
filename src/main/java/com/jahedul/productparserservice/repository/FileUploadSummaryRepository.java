package com.jahedul.productparserservice.repository;

import com.jahedul.productparserservice.entity.FileUploadSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadSummaryRepository extends JpaRepository<FileUploadSummary, Long> {
    FileUploadSummary findFirstByOrderByUploadDateTimeDesc();
}
