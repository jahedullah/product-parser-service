package com.jahedul.productparserservice.service;

import com.jahedul.productparserservice.entity.FileUploadSummary;
import com.jahedul.productparserservice.model.FileUploadSummaryResource;
import com.jahedul.productparserservice.repository.FileUploadSummaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DefaultFileUploadSummaryServiceTest {
    @Mock
    private FileUploadSummaryRepository fileUploadSummaryRepository;

    private DefaultFileUploadSummaryService fileUploadSummaryService;

    private FileUploadSummary fileUploadSummary;
    private FileUploadSummaryResource fileUploadSummaryResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fileUploadSummaryService = new DefaultFileUploadSummaryService(fileUploadSummaryRepository);

        fileUploadSummary = new FileUploadSummary();
        fileUploadSummary.setId(1L);
        fileUploadSummary.setFileName("sample.xlsx");
        fileUploadSummary.setProductsUpdated(5);
        fileUploadSummary.setProductsAdded(10);
        fileUploadSummary.setUploadDateTime(ZonedDateTime.now());

        fileUploadSummaryResource = new FileUploadSummaryResource();
        fileUploadSummaryResource.setFileName("sample.xlsx");
        fileUploadSummaryResource.setProductsUpdated(5);
        fileUploadSummaryResource.setProductsAdded(10);
    }

    @Test
    public void testGetSummaryOfLastUploadedFile_shouldReturnSummary() {
        when(fileUploadSummaryRepository.findFirstByOrderByUploadDateTimeDesc()).thenReturn(fileUploadSummary);

        FileUploadSummary result = fileUploadSummaryService.getSummaryOfLastUploadedFile();

        assertNotNull(result);
        assertEquals("sample.xlsx", result.getFileName());
        assertEquals(5, result.getProductsUpdated());
        assertEquals(10, result.getProductsAdded());
        verify(fileUploadSummaryRepository).findFirstByOrderByUploadDateTimeDesc();
    }

    @Test
    public void testSaveFileUploadSummary_shouldSaveSummary() {
        fileUploadSummaryService.saveFileUploadSummary(fileUploadSummaryResource);
        verify(fileUploadSummaryRepository).save(any(FileUploadSummary.class));
    }
}
