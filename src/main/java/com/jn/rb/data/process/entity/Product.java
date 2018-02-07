package com.jn.rb.data.process.entity;

import javax.persistence.*;

/**
 * Trivial JPA entity for vertx-spring demo
 */
@Entity
@Table(name="PRODUCT")
public class Product {

  @Id
  @Column(name="ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer productId;

  @Column
  private String description;

  public Integer getProductId() {
    return productId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
