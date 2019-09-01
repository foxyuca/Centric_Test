package com.centric.products.rest;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.centric.products.domain.Product;
import com.centric.products.properties.ProductsProperties;
import com.centric.products.service.ProductService;
import com.centric.products.util.FormatUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductsProperties productsProperties;

	@PostMapping(value = "", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProductResponseEntity> create(@RequestBody Product product) {
		ProductResponseEntity productResponseEntity = new ProductResponseEntity();
		BeanUtils.copyProperties(productService.createProduct(product),productResponseEntity);
		URI location = URI.create(String
				.format(FormatUtil.makeHref(productsProperties.getHost(),
						1, 
						"products",
						productResponseEntity.getId())));
		return ResponseEntity.created(location).body(productResponseEntity);
	}
	
	@ApiOperation(value = "Read product", response = Product.class)
	   @ApiResponses({
	           @ApiResponse(
	                   code = 200,
	                   message = "Product returned successfully",
	                   response = Product.class
	           ),
	           @ApiResponse(
	                   code = 404,
	                   message = "No Product found for the requested ID"
	           ),
	           @ApiResponse(
	                   code = 402,
	                   message = "Failed to read Product"
	           )
	   })
	   @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,
	           path = "/{productId}")
	public ProductResponseEntity read(@PathVariable("productId") final UUID productId) {
		ProductResponseEntity productResponseEntity = new ProductResponseEntity();
		BeanUtils.copyProperties(productService.findProductById(productId), productResponseEntity);
		return productResponseEntity;
	}

}
