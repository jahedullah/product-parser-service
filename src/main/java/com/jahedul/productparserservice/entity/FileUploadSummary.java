package com.jahedul.productparserservice.entity;

import jakarta.persistence.*;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Setter
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
}
