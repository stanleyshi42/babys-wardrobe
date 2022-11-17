package com.cognixia.jump.model;

import javax.validation.constraints.NotBlank;

public class Purchase {

	@NotBlank
	private String prod_id;
	
	@NotBlank
	private int qty;
	
	public Purchase() {
		
	}

	public Purchase(@NotBlank String prod_id, @NotBlank int qty) {
		super();
		this.prod_id = prod_id;
		this.qty = qty;
	}

	public String getProd_id() {
		return prod_id;
	}

	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	@Override
	public String toString() {
		return "Purchaces [prod_id=" + prod_id + ", qty=" + qty + "]";
	}
	
}
