package com.jahedul.productparserservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "file_upload_summaries")
public class FileUploadSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private ZonedDateTime uploadDateTime;

    @Column(nullable = false)
    private int productsAdded;

    @Column(nullable = false)
    private int productsUpdated;

    @Column(nullable = false)
    private int productsDeleted;
}
