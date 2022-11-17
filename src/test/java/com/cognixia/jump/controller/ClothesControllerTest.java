package com.cognixia.jump.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.Clothes;
import com.cognixia.jump.repository.ClothesRepository;
import com.cognixia.jump.service.ClothesService;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClothesController.class)
public class ClothesControllerTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	ClothesService service;

	@MockBean
	ClothesRepository repo;

	@MockBean
	PasswordEncoder encoder;

	@MockBean
	MyUserDetailsService myUserDetailsService;

	@MockBean
	JwtUtil jwtUtil;

	@InjectMocks
	ClothesController controller;

	// TODO 403 error
//	@Test
//	@WithMockUser(username = "admin", roles = { "ADMIN" })
//	public void testCreateClothes() throws Exception {
//		String uri = "/api/clothes";
//
//		List<String> colors = new ArrayList<String>();
//		colors.add("Blue");
//		Clothes clothes = new Clothes("1", "Blue Onesie", "Onesie", "Male", "m3", colors, 12.99, "url");
//
//		when(service.createClothes(Mockito.any(Clothes.class))).thenReturn(clothes);
//
//		mockMvc.perform(post(uri).content(asJsonString(clothes)).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andDo(print()).andExpect(status().isCreated());
//	}
	
	@Test
	@WithMockUser(value = "spring")
	public void testFindAllClothes() throws Exception {
		String uri = "/api/clothes";

		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(value = "spring")
	public void testFindClothesById() throws Exception {
		String uri = "/api/clothes/{id}";

		String id = "1";
		List<String> colors = new ArrayList<String>();
		colors.add("Blue");
		Clothes clothes = new Clothes(id, "Blue Onsies", "Onsie", "Male", "m3", colors, 12.99, "url");

		when(service.findClothesById(id)).thenReturn(clothes);

		mockMvc.perform(get(uri, id)).andDo(print()).andExpect(status().isOk());

		verify(service, times(1)).findClothesById(id);
		verifyNoMoreInteractions(service);
	}

	// TODO 403 error
//	@Test
//	@WithMockUser(username = "admin", password = "password", roles = { "ADMIN" })
//	public void testUpdateClothes() throws Exception {
//		String uri = "/api/clothes";
//
//		String id = "1";
//		List<String> colors = new ArrayList<String>();
//		colors.add("Blue");
//		Clothes clothes = new Clothes(id, "Blue Onsies", "Onsie", "Male", "m3", colors, 12.99, "url");
//
//		when(service.findClothesById(id)).thenReturn(clothes);
//
//		mockMvc.perform(post(uri, id)).andDo(print()).andExpect(status().isOk());
//
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
