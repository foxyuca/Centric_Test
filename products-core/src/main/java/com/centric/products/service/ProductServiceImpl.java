package com.centric.products.service;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centric.products.domain.Product;
import com.centric.products.exception.NotFoundException;
import com.centric.products.repository.ProductsDao;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductsDao productsDao;
	
	@Override
	public Product createProduct(Product product) {
		product.setId(UUID.randomUUID());
		return productsDao.save(product);
	}

	@Override
	public Product findProductById(UUID id) {
		
		Optional<Product> product = productsDao.findById(id);
		return product.orElseThrow(() -> new NotFoundException(String.format(
	               "Read Failed. Product ID: [%s] not found",
	               id)));
		
	}

}
