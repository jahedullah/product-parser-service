package com.jahedul.productparserservice.repositories;

import com.jahedul.productparserservice.entities.FileUploadSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadSummaryRepository extends JpaRepository<FileUploadSummary, Long> {
    FileUploadSummary findFirstByOrderByUploadDateTimeDesc();
}
