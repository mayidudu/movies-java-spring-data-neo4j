package com.jn.rb.data.process.services;

import com.jn.rb.data.process.entity.Product;
import com.jn.rb.data.process.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Simple Spring service bean to expose the results of a trivial database call
 */
@Service
public class ProductService {

  @Autowired
  private ProductRepository repo;

  public List<Product> getAllProducts() {
    return repo.findAll();
  }
  public void save(Product product){
    repo.save(product);
  }
}
