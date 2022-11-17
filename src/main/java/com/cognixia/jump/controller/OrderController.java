package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Clothes;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.model.Purchase;
import com.cognixia.jump.repository.ClothesRepository;
import com.cognixia.jump.repository.OrderRepository;
import com.cognixia.jump.service.ClothesService;
import com.cognixia.jump.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "order", description = "the API for managing orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	ClothesService clothesService;

	@PostMapping("/order")
	public ResponseEntity<?> createOrder(@RequestBody Order order)
			throws MethodArgumentNotValidException, ResourceNotFoundException {
		order.setId(null);
		// Calculate total price for this order
		double price = 0;
		for (Purchase p : order.getPurchases()) {
			Clothes clothes = clothesService.findClothesById(p.getProdId());
			price += clothes.getPrice() * p.getQty();
		}
		order.setPrice(price);

		Order created = orderService.createOrder(order);
		return ResponseEntity.status(201).body(created);
	}

	@Operation(summary = "Get all orders in the order collection", description = "Gets all the orders in the order collection in the "
			+ " database. Each order grabbed has an id, userId, purchases, and price.")
	@GetMapping("/order")
	public List<Order> findAllOrders(
			@CurrentSecurityContext(expression = "authentication?.credentials") String username)
			throws ResourceNotFoundException {
		return orderService.findAllOrders();
	}

	@GetMapping("/order/{id}")
	public ResponseEntity<?> findOrderById(@PathVariable String id) throws ResourceNotFoundException {
		Order found = orderService.findOrderById(id);
		return ResponseEntity.status(200).body(found);
	}

	@PutMapping("/order")
	public ResponseEntity<?> updateOrder(@RequestBody Order order) throws ResourceNotFoundException {
		Order updated = orderService.updateOrder(order);
		return ResponseEntity.status(200).body(updated);
	}

	@GetMapping("/order/userid/{userId}")
	public List<Order> findByUserId(@PathVariable String userId) throws ResourceNotFoundException {
		List<Order> found = orderService.findByUserId(userId);
		return found;
	}
}
