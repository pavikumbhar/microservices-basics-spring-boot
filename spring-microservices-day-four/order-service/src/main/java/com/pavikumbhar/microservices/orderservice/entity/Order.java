package com.pavikumbhar.microservices.orderservice.entity;
import java.util.List;

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
public class Order {
	private Long id;

	private String customerEmail;

	private String customerAddress;
	
	private List<OrderItem> items;
	
	public Order(){
		
	}

}
