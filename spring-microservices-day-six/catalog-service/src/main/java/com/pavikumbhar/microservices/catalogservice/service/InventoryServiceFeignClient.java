package com.pavikumbhar.microservices.catalogservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pavikumbhar.microservices.catalogservice.model.Product;

@FeignClient(name = "inventory-service")
public interface InventoryServiceFeignClient {
	
    @GetMapping("/api/inventory/product/{productCode}")
    Product findInventoryByProductCode( @PathVariable("productCode") String productCode);

    @GetMapping("/api/inventory/products/")
    List<Product> findAllProducts();

}