package com.jahedul.productparserservice.repository;

import com.jahedul.productparserservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
