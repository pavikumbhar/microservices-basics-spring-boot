package com.pavikumbhar.microservices.catalogservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pavikumbhar.microservices.catalogservice.model.Product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pavikumbhar
 *
 */
@Service
@Slf4j
@AllArgsConstructor
public class ProductService {

	private InventoryServiceClient inventoryServiceClient;

	/**
	 * restTemplate.getForObject("http://INVENTORY-SERVICE/api/inventory/product/" + productCode, Product.class)
	 * @param productCode
	 * @return
	 */
	public Product findInventoryByProductCode(String productCode) {
		log.info("Finding inventory for product code : {}", productCode);
		return inventoryServiceClient.findInventoryByProductCode(productCode);

	}

	public List<Product> findAllProducts() {
		log.info("Finding inventory for all products ");
		return inventoryServiceClient.findAllProducts();
	}

}
