package com.pavikumbhar.microservices.inventoryservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author pavikumbhar
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class Product {
	private Long id;
	private String code;
	private String name;
	private String description;
	private double price;
	private boolean inStock = true;
}