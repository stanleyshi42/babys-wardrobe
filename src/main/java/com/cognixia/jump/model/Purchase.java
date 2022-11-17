package com.cognixia.jump.model;

import javax.validation.constraints.NotBlank;

public class Purchase {

	@NotBlank
	private String prodId;

	@NotBlank
	private int qty;

	public Purchase() {

	}

	public Purchase(@NotBlank String prodId, @NotBlank int qty) {
		super();
		this.prodId = prodId;
		this.qty = qty;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Purchase [prodId=" + prodId + ", qty=" + qty + "]";
	}

}
