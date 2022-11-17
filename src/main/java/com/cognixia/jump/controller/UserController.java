package com.cognixia.jump.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "user", description = "the API for managing users")
public class UserController {

	@Autowired
	UserRepository repo;

	@Autowired
	UserService service;

	@Autowired
	PasswordEncoder encoder;

	@Operation(summary = "Get all the users in the user table", description = "Gets all the users from the user table in the "
			+ "product_db database. Each user grabbed has an "
			+ "id, username, password, email, role, and enabled for activating account.")
	@GetMapping("/user")
	public List<User> getUsers() throws ResourceNotFoundException {

		return service.getUsers();
	}

	@PostMapping("/user")
	public ResponseEntity<?> addUser(@Valid @RequestBody User user)
			throws MethodArgumentNotValidException, DuplicateResourceException {

		return service.addUser(user);
	}

	@PutMapping("/user")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user)
			throws MethodArgumentNotValidException, DuplicateResourceException {

		return service.updateUser(user);
	}

	@DeleteMapping("/user")
	public ResponseEntity<?> deleteUser(@RequestParam String id) throws ResourceNotFoundException {

		return service.deleteUser(id);
	}
}
