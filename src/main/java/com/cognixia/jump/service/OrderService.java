package com.cognixia.jump.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Clothes;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.model.Purchase;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.ClothesRepository;
import com.cognixia.jump.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepo;

	@Autowired
	ClothesRepository clothesRepo;

	public List<Order> getOrders(User user) throws ResourceNotFoundException {

		if (orderRepo.findByUserId(user.getId()).isEmpty()) {
			throw new ResourceNotFoundException("No Orders were found");
		}
		return orderRepo.findAll();
	}

	public ResponseEntity<?> addOrder(User user, String clothesId, int qty)
			throws MethodArgumentNotValidException, ResourceNotFoundException {

		if (clothesRepo.findById(clothesId).isEmpty()) {
			throw new ResourceNotFoundException("Clothing", clothesId);
		}

		Order created = new Order();
		Order entry = new Order();

		entry = newEntry(user, clothesId, qty);

		created = orderRepo.save(entry);

		return ResponseEntity.status(201).body(created);
	}

	private Order newEntry(User user, String clothesId, int qty) {

		Clothes clothing = clothesRepo.findById(clothesId).get();
		Order entry = new Order();
		Purchase purchase = new Purchase(clothesId, qty);
		List<Purchase> purchases = new ArrayList();
		purchases.add(purchase);

		double price = 0;

		price = clothing.getPrice() * qty;

		entry.setId(null);
		entry.setUserId(user.getId());
		entry.setClothesId(clothesId);
		entry.setPurchaces(purchases);
		entry.setPrice(price);

		return entry;
	}

	public ResponseEntity<?> deleteOrder(User user, String id) throws ResourceNotFoundException {

		Order found = orderRepo.findById(id).get();

		if (found == null) {
			throw new ResourceNotFoundException("Order", id);
		}
		orderRepo.deleteById(found.getId());

		return ResponseEntity.status(201).body(found);
	}
}
