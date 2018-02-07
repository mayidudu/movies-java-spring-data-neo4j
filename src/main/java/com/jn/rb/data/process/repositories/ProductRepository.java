package com.jn.rb.data.process.repositories;

import com.jn.rb.data.process.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository to connect our service bean to data
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
