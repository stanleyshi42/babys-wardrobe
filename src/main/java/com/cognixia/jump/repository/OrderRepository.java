package com.cognixia.jump.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Clothes;
import com.cognixia.jump.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

	public List<Order> findByUserId(String userId);
	
//	public Order findByUserIdAndClothesId(String userId, String ClothesId);
	
	public Optional<Order> findClothesById(String prodId);

	public Order findByUserIdAndClothesId(String id, String clothesId);
}
