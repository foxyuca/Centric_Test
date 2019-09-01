package com.centric.products.service;

import java.util.Optional;
import java.util.UUID;

import com.centric.products.domain.Product;

public interface ProductService {
	
	public Product createProduct(Product product);
	
	public Product findProductById(UUID id);

}
