package com.jahedul.productparserservice.controllers;

import com.jahedul.productparserservice.entities.Product;
import com.jahedul.productparserservice.exceptions.ProductNotFoundException;
import com.jahedul.productparserservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadProductFile(@RequestParam("file") MultipartFile file) throws IOException {
        productService.uploadProductsFromFile(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("Products uploaded successfully.");
    }

    @GetMapping("/{sku}")
    public ResponseEntity<Product> getProductBySku(@PathVariable String sku) {
        Product product = productService.getProductBySku(sku)
                .orElseThrow(() -> new ProductNotFoundException("Product with SKU: " + sku + " not found"));
        return ResponseEntity.ok(product);
    }

    @GetMapping("")
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.getAllProducts(pageable);
        return ResponseEntity.ok(productPage);
    }
}
