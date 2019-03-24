package com.pavikumbhar.microservices.inventoryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavikumbhar.microservices.inventoryservice.entity.Product;
import com.pavikumbhar.microservices.inventoryservice.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

	@Autowired
	private ProductService productService;

	@GetMapping("product/{code}")
	public Product productByCode(@PathVariable String code) {
		log.info("Finding product by code :" + code);
		return productService.findProductByCode(code)
				.orElseThrow(() -> new RuntimeException("Product with code [" + code + "] doesn't exist"));
	}

	@GetMapping("/products")
	public List<Product> findAllProducts() {
		log.info("Finding inventory for all products ");
		return productService.findAllProducts();
	}

}
