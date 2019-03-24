package com.pavikumbhar.microservices.orderservice.entity;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author pavikumbhar
 *
 */
@Getter
@Setter
public class Order {
	private Long id;

	private String customerEmail;

	private String customerAddress;
	
	private List<OrderItem> items;

}
