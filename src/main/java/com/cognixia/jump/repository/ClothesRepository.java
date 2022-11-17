package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Clothes;

@Repository
public interface ClothesRepository extends MongoRepository<Clothes, String> {

	List<Clothes> findByAge(String age);

	List<Clothes> findByGender(String gender);

	List<Clothes> findByType(String type);

	List<Clothes> findByColorContaining(String color);

	List<Clothes> findByPriceBetween(int from, int to);
}
