package com.cognixia.jump.model;

import javax.validation.constraints.NotBlank;

public class Purchase {

	@NotBlank
	private String id;

	@NotBlank
	private int qty;

	public Purchase() {

	}

	public Purchase(@NotBlank String id, @NotBlank int qty) {
		super();
		this.id = id;
		this.qty = qty;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", qty=" + qty + "]";
	}

}
