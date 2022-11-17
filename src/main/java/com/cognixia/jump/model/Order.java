package com.cognixia.jump.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {

	@Id
	private String id;

	@NotBlank
	private String userId;

	@NotBlank
	private String clothesId;

	@NotBlank
	private List<Purchase> purchaces;

	@NotBlank
	@Range(min = 1)
	private double price;

	public Order() {

	}

	public Order(String id, @NotBlank String userId, @NotBlank String clothesId, @NotBlank List<Purchase> purchaces,
			@NotBlank double price) {
		super();
		this.id = id;
		this.userId = userId;
		this.clothesId = clothesId;
		this.purchaces = purchaces;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getClothesId() {
		return clothesId;
	}

	public void setClothesId(String clothesId) {
		this.clothesId = clothesId;
	}

	public List<Purchase> getPurchaces() {
		return purchaces;
	}

	public void setPurchaces(List<Purchase> purchaces) {
		this.purchaces = purchaces;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", clothesId=" + clothesId + ", purchaces=" + purchaces
				+ ", price=" + price + "]";
	}

}
