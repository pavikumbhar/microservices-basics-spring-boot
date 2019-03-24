package com.pavikumbhar.microservices.catalogservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pavikumbhar.microservices.catalogservice.model.Product;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pavikumbhar
 *
 */
@Service
@Slf4j
public class ProductService {

	@Autowired
	private RestTemplate restTemplate;

	public Product findInventoryByProductCode(String productCode) {
		log.info("Finding inventory for product code : {}", productCode);
		return restTemplate.getForObject("http://localhost:8083//api/inventory/product/" + productCode, Product.class);

	}

	public List<Product> findAllProducts() {
		log.info("Finding inventory for all products ");
		ResponseEntity<List<Product>> t = restTemplate.exchange("http://localhost:8083/api/inventory/products/",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
				});
		List<Product> list = t.getBody();
		return list;
	}

}
