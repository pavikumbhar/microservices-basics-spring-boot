package com.pavikumbhar.microservices.catalogservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavikumbhar.microservices.catalogservice.model.Product;
import com.pavikumbhar.microservices.catalogservice.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("product/{productCode}")
	public ResponseEntity<Product> findInventoryByProductCode(@PathVariable("productCode") String productCode) {
		log.info("Finding inventory for product code : {}", productCode);
		Product product = productService.findInventoryByProductCode(productCode);
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> findAllProducts() {
		log.info("Finding inventory for all products ");
		List<Product> list = productService.findAllProducts();
		if (list != null) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
		}
	}

}
