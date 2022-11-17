package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.cognixia.jump.exception.DuplicateResourceException;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repo;

	@Autowired
	PasswordEncoder encoder;

	public List<User> getUsers() throws ResourceNotFoundException {

		if (repo.count() == 0) {
			throw new ResourceNotFoundException("No Users were found");
		}
		return repo.findAll();
	}

	public ResponseEntity<?> addUser(User user) throws DuplicateResourceException, MethodArgumentNotValidException {

		if (repo.findByUsername(user.getUsername()).isPresent()) {
			throw new DuplicateResourceException("User", user.getId());
		}

		user.setId(null);
		user.setPassword(encoder.encode(user.getPassword()));
		User created = repo.save(user);

		return ResponseEntity.status(201).body(created);
	}

	public ResponseEntity<?> updateUser(@Valid User user)
			throws DuplicateResourceException, MethodArgumentNotValidException {

		if (repo.findById(user.getId()).isPresent()) {
			throw new DuplicateResourceException("User", user.getId());
		}

		user.setPassword(encoder.encode(user.getPassword()));
		User updated = repo.save(user);

		return ResponseEntity.status(201).body(updated);
	}

	public ResponseEntity<?> deleteUser(String id) throws ResourceNotFoundException {

		Optional<User> found = repo.findById(id);

		if (!found.isPresent()) {
			throw new ResourceNotFoundException("User", id);
		}

		repo.delete(found.get());
		return ResponseEntity.status(201).body(found.get());
	}

}
