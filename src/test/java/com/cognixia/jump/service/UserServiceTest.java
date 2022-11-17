package com.cognixia.jump.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Address;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private UserRepository repo;

	@Mock
	PasswordEncoder encoder;

	@InjectMocks
	private UserService service;

	@Test
	void testGetUsers() throws ResourceNotFoundException {
		List<User> users = new ArrayList<User>();
		Address address = new Address("1 A Street", "S City", "CA", "12345");
		users.add(new User("1", "user1", "password", "ROLE_USER", true, address));
		users.add(new User("2", "user2", "password", "ROLE_USER", true, address));

		when(repo.count()).thenReturn(2L);
		when(service.getUsers()).thenReturn(users);

		List<User> result = service.getUsers();

		if (users.size() != result.size())
			fail();

		for (int i = 0; i < users.size(); i++) {
			User original = users.get(i);
			User retrieved = result.get(i);

			if (!original.equals(retrieved))
				fail();
		}
	}

	@Test
	void testAddUser() throws Exception {
		Address address = new Address("1 A Street", "S City", "CA", "12345");
		User user = new User("1", "user1", "password", "ROLE_USER", true, address);
		when(repo.save(Mockito.any(User.class))).thenReturn(user);

		ResponseEntity<?> result = service.addUser(user);

		boolean results = result != null;
		assertTrue(results);
	}

	@Test
	void testUpdateUser() throws Exception {
		Address address = new Address("1 A Street", "S City", "CA", "12345");
		User user = new User("1", "user1", "password", "ROLE_USER", true, address);

		when(repo.save(user)).thenReturn(user);

		ResponseEntity<?> result = service.updateUser(user);

		boolean results = result != null;
		assertTrue(results);
	}

	@Test
	void testDeleteUser() throws Exception {
		Address address = new Address("1 A Street", "S City", "CA", "12345");
		User user = new User("1", "user1", "password", "ROLE_USER", true, address);
		Optional<User> optional = Optional.ofNullable(user);

		when(repo.findById(user.getId())).thenReturn(optional);
		doNothing().when(repo).delete(optional.get());

		ResponseEntity<?> result = service.deleteUser(user.getId());

		boolean results = result != null;
		assertTrue(results);
	}
}
