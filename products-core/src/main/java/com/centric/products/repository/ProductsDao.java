package com.centric.products.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.centric.products.domain.Product;

@Repository
public interface ProductsDao extends CrudRepository<Product, UUID> {

}
