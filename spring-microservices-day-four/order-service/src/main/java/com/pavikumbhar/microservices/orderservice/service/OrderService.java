package com.pavikumbhar.microservices.orderservice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.pavikumbhar.microservices.orderservice.entity.Order;
import com.pavikumbhar.microservices.orderservice.entity.OrderItem;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class OrderService {

	private static final AtomicLong counter = new AtomicLong();
	private static List<Order> orders;

	static {
		orders = populateDummyorders();
	}

	private static List<Order> populateDummyorders() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(counter.incrementAndGet(), "pavikumbhar", "pune", Arrays.asList(new OrderItem(1L,2L,3,BigDecimal.valueOf(5.0)))));
		orders.add(new Order(counter.incrementAndGet(), "shanaya", "Pune",Arrays.asList(new OrderItem(1L,2L,3,BigDecimal.valueOf(5.0)))));
		return orders;
	}

	public List<Order> findAllProducts() {
		return orders;
	}


	public Order saveOrder(Order order) {
		order.setId(counter.incrementAndGet());
		orders.add(order);
		return order;
	}

	public Optional<Order> findOrderById( Long id) {
		log.info("Finding order by id : {}",  id);
		return orders.stream().filter(order -> order.getId()==id).findFirst();
	}
	

}
