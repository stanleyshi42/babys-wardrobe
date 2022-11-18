package com.cognixia.jump.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.service.UserService;
//import com.cognixia.jump.service.UserService;
import com.cognixia.jump.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	MockMvc mockMvc;

	@MockBean
	UserService service;

	@MockBean
	UserRepository repo;

	@MockBean
	PasswordEncoder encoder;

	@MockBean
	MyUserDetailsService myUserDetailsService;

	@MockBean
	JwtUtil jwtUtil;

	@InjectMocks
	UserController controller;
	
	@Test
	@WithMockUser(username = "admin", roles = "ADMIN")
	public void testFindUsers() throws Exception {
		String uri = "/api/user";

		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk());
	}
	
//	@Test
//	@WithMockUser(username = "admin", roles = "ADMIN")
//	public void testAddUser() throws Exception {
//		String uri = "/api/user";
//
//		User users = new User("1", "admin", "password", "ROLE_ADMIN", true, null);
//
//		when(repo.save(Mockito.any(User.class))).thenReturn(users);
//
//		mockMvc.perform(post(uri).content(asJsonString(users)).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andDo(print()).andExpect(status().isCreated());
//
//	}
	
//	@Test
//	@WithMockUser(username = "admin", roles = "ADMIN")
//	public void testUpdateUser() throws Exception {
//		String uri = "/api/user";
//
//		String id = "1";
//		User users = new User(id, "user", "password", "ROLE_USER", true, null);
//
//		when(service.updateUser(users)).thenReturn(null);
//
//		mockMvc.perform(put(uri).content(asJsonString(users)).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andDo(print()).andExpect(status().isCreated());
//	}
//	
//	@Test
//	@WithMockUser(username = "admin", roles = "ADMIN")
//	public void testDeleteUser() throws Exception {
//		String uri = "/api/user";
//
//		String id = "1";
//		User user = new User(id, "user", "password", "ROLE_USER", true, null);
//		
//		when(service.deleteUser(id)).thenReturn(null);
//
//		mockMvc.perform(delete(uri).content(id).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andDo(print()).andExpect(status().isCreated());
//	}
	
	// converts any object to a JSON string
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
