package com.jahedul.productparserservice.repositories;

import com.jahedul.productparserservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT p.sku FROM Product p")
    List<String> findAllSkus();
}
