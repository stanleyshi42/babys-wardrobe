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

import com.cognixia.jump.model.Clothes;
import com.cognixia.jump.repository.ClothesRepository;

@ExtendWith(MockitoExtension.class)
public class ClothesServiceTest {

	@Mock
	private ClothesRepository repo;

	@InjectMocks
	private ClothesService service;

	@Test
	void testGetClothes() throws Exception {
		List<Clothes> clothes = new ArrayList<Clothes>();
		List<String> colors = new ArrayList<String>();
		colors.add("Blue");
		clothes.add(new Clothes("1", "Blue Onsies", "Onsie", "Male", "m3", colors, 12.99));
		colors.remove(0);
		colors.add("Yellow");
		clothes.add(new Clothes("2", "Yellow Onsies", "Onsie", "Unisex", "m6", colors, 14.99));

		when(repo.findAll()).thenReturn(clothes);

		List<Clothes> result = service.findAllClothes();

		if (clothes.size() != result.size()) {
			fail();
		}

		for (int i = 0; i < clothes.size(); i++) {
			Clothes og = clothes.get(i);
			Clothes rt = result.get(i);

			if (!og.equals(rt)) {
				fail();
			}
		}
	}

	@Test
	void testGetClothesById() throws Exception {
		String id = "1";
		List<String> colors = new ArrayList<String>();
		colors.add("Blue");
		Optional<Clothes> clothes = Optional.of(new Clothes(id, "Blue Onsies", "Onsie", "Male", "m3", colors, 12.99));

		when(repo.findById(id)).thenReturn(clothes);
		Clothes result = service.findClothesById(id);

		boolean results = result != null;
		assertTrue(results);
	}

	@Test
	void testCreateClothes() throws Exception {
		List<String> colors = new ArrayList<String>();
		colors.add("Blue");
		Clothes clothes = new Clothes("1", "Blue Onsies", "Onsie", "Male", "m3", colors, 12.99);

		when(repo.insert(Mockito.any(Clothes.class))).thenReturn(clothes);

		Clothes result = service.createClothes(clothes);

		boolean results = result != null;
		assertTrue(results);
	}

	@Test
	void testUpdateClothes() throws Exception {
		String id = "1";
		List<String> colors = new ArrayList<String>();
		colors.add("Blue");
		Clothes clothes = new Clothes(id, "Blue Onsies", "Onsie", "Male", "m3", colors, 12.99);

		when(repo.existsById(id)).thenReturn(true);
		when(repo.save(clothes)).thenReturn(clothes);

		Clothes result = service.updateClothes(clothes);

		boolean results = result != null;
		assertTrue(results);

	}

	@Test
	void testDeleteClothes() throws Exception {
		String id = "1";
		List<String> colors = new ArrayList<String>();
		colors.add("Blue");
		Optional<Clothes> clothes = Optional.of(new Clothes(id, "Blue Onsies", "Onsie", "Male", "m3", colors, 12.99));

		when(repo.findById(id)).thenReturn(clothes);
		doNothing().when(repo).deleteById(id);

		Clothes result = service.deleteClothes(id);

		boolean results = result != null;
		assertTrue(results);
	}

	@Test
	void testFindByAge() throws Exception {
		String age = "3";
		List<String> colors = new ArrayList<String>();
		colors.add("Blue");
		List<Clothes> clothes = new ArrayList<Clothes>();
		clothes.add(new Clothes("1", "Blue Onsies", "Onsie", "Male", age, colors, 12.99));

		when(repo.findByAge(age)).thenReturn(clothes);

		List<Clothes> result = service.findByAge(age);

		boolean results = result != null;
		assertTrue(results);
	}

	@Test
	void testFindByGender() throws Exception {
		String gender = "Male";
		List<String> colors = new ArrayList<String>();
		colors.add("Blue");
		List<Clothes> clothes = new ArrayList<Clothes>();
		clothes.add(new Clothes("1", "Blue Onsies", "Onsie", gender, "3", colors, 12.99));

		when(repo.findByGender(gender)).thenReturn(clothes);

		List<Clothes> result = service.findByGender(gender);

		boolean results = result != null;
		assertTrue(results);
	}

	@Test
	void testFindByType() throws Exception {
		String type = "Onesie";
		List<String> colors = new ArrayList<String>();
		colors.add("Blue");
		List<Clothes> clothes = new ArrayList<Clothes>();
		clothes.add(new Clothes("1", "Blue Onsies", type, "Male", "3", colors, 12.99));

		when(repo.findByType(type)).thenReturn(clothes);

		List<Clothes> result = service.findByType(type);

		boolean results = result != null;
		assertTrue(results);
	}

	@Test
	void testFindByColorContaining() throws Exception {
		String color = "Blue";
		List<String> colors = new ArrayList<String>();
		colors.add("Blue");
		List<Clothes> clothes = new ArrayList<Clothes>();
		clothes.add(new Clothes("1", "Blue Onsies", "Onsie", "Male", "3", colors, 12.99));

		when(repo.findByColorContaining(color)).thenReturn(clothes);

		List<Clothes> result = service.findByColorContaining(color);

		boolean results = result != null;
		assertTrue(results);
	}

}