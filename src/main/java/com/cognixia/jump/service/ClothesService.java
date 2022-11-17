package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Clothes;
import com.cognixia.jump.repository.ClothesRepository;

@Service
public class ClothesService {
	@Autowired
	ClothesRepository repo;

	public Clothes createClothes(Clothes clothes) {
		clothes.setId(null);
		Clothes created = repo.insert(clothes);
		return created;
	}

	public List<Clothes> findAllClothes() {
		return repo.findAll();
	}

	public Clothes findClothesById(String id) throws ResourceNotFoundException {
		Optional<Clothes> found = repo.findById(id);
		if (found.isPresent()) {
			return found.get();
		}
		throw new ResourceNotFoundException("Clothes", id);
	}

	public Clothes updateClothes(Clothes clothes) throws ResourceNotFoundException {
		if (repo.existsById(clothes.getId())) {
			Clothes updated = repo.save(clothes);
			return updated;
		}

		throw new ResourceNotFoundException("Clothes", clothes.getId());
	}

	public Clothes deleteClothes(String id) throws ResourceNotFoundException {
		Clothes toDelete = findClothesById(id);
		repo.deleteById(id);
		return toDelete;
	}

	public List<Clothes> findByAge(String age) {
		return repo.findByAge(age);
	}

	public List<Clothes> findByGender(String gender) {
		return repo.findByGender(gender);
	}

	public List<Clothes> findByType(String type) {
		return repo.findByType(type);
	}

	public List<Clothes> findByColorContaining(String color) {
		return repo.findByColorContaining(color);
	}

}
