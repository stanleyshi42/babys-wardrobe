package com.cognixia.jump.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.DuplicateResourceException;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.model.Purchase;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.OrderRepository;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "order", description = "the API for managing orders")
public class OrderController {

	@Autowired
	OrderRepository repo;
	
	@Autowired
	UserRepository repoU;
	
	@Autowired
	OrderService service;
	
	@Operation( summary = "Get all the users in the user table",
			description = "Gets all the users from the user table in the "
					+ "product_db database. Each user grabbed has an "
					+ "id, username, password, email, role, and enabled for activating account.")
	@GetMapping("/order")
	public List<Order> getOrders(@CurrentSecurityContext(expression="authentication?.credentials") String username) throws ResourceNotFoundException {
		User user = repoU.findByUsername(username).get();
		return service.getOrders(user);
	}
	
	@PostMapping("/order")
	public ResponseEntity<?> addOrder(@RequestParam String clothesId, @RequestParam int qty, @CurrentSecurityContext(expression="authentication?.credentials") String username) throws MethodArgumentNotValidException, ResourceNotFoundException {
		User user = repoU.findByUsername(username).get();
		return service.addOrder(user, clothesId, qty);
	}
	
	@DeleteMapping("/order")
	public ResponseEntity<?> deleteUser(@CurrentSecurityContext(expression="authentication?.credentials") String username, @RequestParam String id) throws ResourceNotFoundException {
		User user = repoU.findByUsername(username).get();
		return service.deleteOrder(user, id);
	}
}
