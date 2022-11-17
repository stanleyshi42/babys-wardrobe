package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Clothes;
import com.cognixia.jump.service.ClothesService;

@RestController
@RequestMapping("/api")
public class ClothesController {
	@Autowired
	ClothesService service;

	@PostMapping("/clothes")
	public ResponseEntity<?> createClothes(@RequestBody Clothes clothes) {
		clothes.setId(null);
		Clothes created = service.createClothes(clothes);

		return ResponseEntity.status(201).body(created);
	}

	@GetMapping("/clothes")
	public List<Clothes> findAllClothes() {
		return service.findAllClothes();
	}

	@GetMapping("/clothes/{id}")
	public ResponseEntity<?> findClothesById(@PathVariable String id) throws ResourceNotFoundException {
		Clothes found = service.findClothesById(id);
		return ResponseEntity.status(200).body(found);
	}

	@PutMapping("/clothes")
	public ResponseEntity<?> updateClothes(@RequestBody Clothes clothes) throws ResourceNotFoundException {
		Clothes updated = service.updateClothes(clothes);
		return ResponseEntity.status(200).body(updated);
	}

	@DeleteMapping("/clothes/{id}")
	public ResponseEntity<?> deleteClothes(@PathVariable String id) throws ResourceNotFoundException {
		Clothes deleted = service.deleteClothes(id);
		return ResponseEntity.status(200).body(deleted);
	}

	@GetMapping("/clothes/age/{age}")
	public List<Clothes> findByAge(@PathVariable String age) {
		List<Clothes> found = service.findByAge(age);
		return found;
	}
	
	@GetMapping("/clothes/gender/{gender}")
	public List<Clothes> findByGender(@PathVariable String gender) {
		List<Clothes> found = service.findByGender(gender);
		return found;
	}
	
	@GetMapping("/clothes/type/{type}")
	public List<Clothes> findByType(@PathVariable String type) {
		List<Clothes> found = service.findByType(type);
		return found;
	}
	
	@GetMapping("/clothes/color/{color}")
	public List<Clothes> findByColorContaining(@PathVariable String color) {
		List<Clothes> found = service.findByColorContaining(color);
		return found;
	}
}
