package com.pavikumbhar.microservices.orderservice.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavikumbhar.microservices.orderservice.entity.Order;
import com.pavikumbhar.microservices.orderservice.service.OrderService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class OrderController {

	private OrderService orderService;

	@PostMapping("/orders")
	public Order createOrder(@RequestBody Order order) {
		log.debug("createOrder ");
		return orderService.saveOrder(order);
	}

	@GetMapping("/orders/{id}")
	public Optional<Order> findOrderById(@PathVariable Long id) {
		return orderService.findOrderById(id);
	}

}
