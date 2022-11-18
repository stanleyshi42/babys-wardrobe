package com.cognixia.jump.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Clothes;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.model.Purchase;
import com.cognixia.jump.repository.ClothesRepository;
import com.cognixia.jump.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	ClothesService clothesService;

	@Autowired
	ClothesRepository clothesRepo;


	public List<Order> findAllOrders() {
		return orderRepo.findAll();
	}

	public Order findOrderById(String id) throws ResourceNotFoundException {
		Optional<Order> found = orderRepo.findById(id);
		if (found.isPresent()) {
			return found.get();
		}
		throw new ResourceNotFoundException("Order", id);
	}

	public Order createOrder(Order order) throws ResourceNotFoundException {
		order.setId(null);
		// Calculate total price for this order
		double price = 0;
		for (Purchase p : order.getPurchases()) {
			Clothes clothes = clothesService.findClothesById(p.getId());
			price += clothes.getPrice() * p.getQty();
		}
		order.setPrice(price);
		Order created = orderRepo.insert(order);
		return created;
	}
		
	public Order updateOrder(Order order) throws ResourceNotFoundException {
		if (orderRepo.existsById(order.getId())) {
			Order updated = orderRepo.save(order);
			return updated;
		}


		throw new ResourceNotFoundException("Order", order.getId());
	}

	public Order deleteOrder(String id) throws ResourceNotFoundException {
		Order toDelete = findOrderById(id);
		orderRepo.deleteById(id);
		return toDelete;
	}

	public List<Order> findByUserId(String userId) throws ResourceNotFoundException {
		return orderRepo.findByUserId(userId);
	}

}
