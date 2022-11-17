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
	private List<Purchase> purchases;

	@NotBlank
	private double price;

	public Order() {

	}

	public Order(String id, @NotBlank String userId, @NotBlank List<Purchase> purchases, @NotBlank double price) {
		super();
		this.id = id;
		this.userId = userId;
		this.purchases = purchases;
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

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", purchases=" + purchases + ", price=" + price + "]";
	}

}
