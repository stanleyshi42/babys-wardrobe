//package com.cognixia.jump.service;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.fail;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//
//import com.cognixia.jump.exception.ResourceNotFoundException;
//import com.cognixia.jump.model.Address;
//import com.cognixia.jump.model.Clothes;
//import com.cognixia.jump.model.Order;
//import com.cognixia.jump.model.Purchase;
//import com.cognixia.jump.model.User;
//import com.cognixia.jump.repository.ClothesRepository;
//import com.cognixia.jump.repository.OrderRepository;
//
//@ExtendWith(MockitoExtension.class)
//public class OrderServiceTest {
//	@Mock
//	private OrderRepository orderRepo;
//
//	@Mock
//	private ClothesRepository clothesRepo;
//
//	@InjectMocks
//	private OrderService service;
//
//	@Test
//	void testGetOrders() throws ResourceNotFoundException {
//		Address address = new Address("1 A Street", "S City", "CA", "12345");
//		User user = new User("1", "user1", "password", "ROLE_USER", true, address);
//
//		List<Order> orders = new ArrayList<>();
//		List<Purchase> purchases = new ArrayList<>();
//		purchases.add(new Purchase("1", 1));
//		purchases.add(new Purchase("2", 1));
//		orders.add(new Order("1", "1", "1", purchases, 19.99));
//		orders.add(new Order("2", "2", "2", purchases, 19.99));
//
//		when(orderRepo.findByUserId(user.getId())).thenReturn(orders);
//		when(orderRepo.findAll()).thenReturn(orders);
//
//		List<Order> result = service.getOrders(user);
//
//		if (orders.size() != result.size()) {
//			fail();
//		}
//
//		for (int i = 0; i < orders.size(); i++) {
//			Order original = orders.get(i);
//			Order retrieved = result.get(i);
//
//			if (!original.equals(retrieved)) {
//				fail();
//			}
//		}
//	}
//
//	// TODO
//	@Test
//	void testAddOrder() throws MethodArgumentNotValidException, ResourceNotFoundException {
//		Address address = new Address("1 A Street", "S City", "CA", "12345");
//		User user = new User("1", "user1", "password", "ROLE_USER", true, address);
//
//		String clothesId = "5";
//		List<String> colors = new ArrayList<String>();
//		colors.add("Blue");
//		Optional<Clothes> clothes = Optional
//				.of(new Clothes(clothesId, "Blue Onesie", "Onesie", "Male", "3M", colors, 9.99));
//
//		int qty = 1;
//		List<Purchase> purchases = new ArrayList<>();
//		purchases.add(new Purchase(clothesId, qty));
//		Order order = new Order(null, user.getId(), clothesId, purchases, 9.99);
//
//		when(clothesRepo.findById(clothesId)).thenReturn(clothes);
//		when(orderRepo.save(order)).thenReturn(order);
//
//		ResponseEntity<?> result = service.addOrder(user, clothesId, qty);
//
//		boolean results = result != null;
//		assertTrue(results);
//	}
//
//	@Test
//	void testDeleteOrder() throws Exception {
//		Address address = new Address("1 A Street", "S City", "CA", "12345");
//		User user = new User("1", "user1", "password", "ROLE_USER", true, address);
//
//		String clothesId = "5";
//
//		String orderId = "1";
//		int qty = 1;
//		List<Purchase> purchases = new ArrayList<>();
//		purchases.add(new Purchase(clothesId, qty));
//		Optional<Order> order = Optional.of(new Order("1", user.getId(), clothesId, purchases, 9.99));
//
//		when(orderRepo.findById(orderId)).thenReturn(order);
//		doNothing().when(orderRepo).deleteById(orderId);
//
//		ResponseEntity<?> result = service.deleteOrder(user, orderId);
//
//		boolean results = result != null;
//		assertTrue(results);
//	}
//}
