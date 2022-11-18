package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Clothes;

@Repository
public interface ClothesRepository extends MongoRepository<Clothes, String> {

	List<Clothes> findByAge(String age);

	@Query("{ 'name': { $regex: '^?0$', $options: 'i' } }")
	List<Clothes> findByName(String name);
	
	@Query("{ 'gender': { $regex: '^?0$', $options: 'i' } }")
	List<Clothes> findByGender(String gender);

	@Query("{ 'type': { $regex: '^?0$', $options: 'i' } }")
	List<Clothes> findByType(String type);

	@Query("{ 'color': { $regex: '^?0$', $options: 'i' } }")
	List<Clothes> findByColorContaining(String color);

	List<Clothes> findByPriceBetween(int from, int to);
}
