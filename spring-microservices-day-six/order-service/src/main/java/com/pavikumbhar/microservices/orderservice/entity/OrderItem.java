package com.pavikumbhar.microservices.orderservice.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author pavikumbhar
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor	
public class OrderItem {
	private Long id;
	private Long productId;
	private int quantity;
	private BigDecimal productPrice;

	public BigDecimal getPrice() {
		return productPrice.multiply(new BigDecimal(quantity));
	}



}
