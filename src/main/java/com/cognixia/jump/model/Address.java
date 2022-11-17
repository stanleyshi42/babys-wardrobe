package com.cognixia.jump.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class Address {

	@NotBlank
	private String street;
	
	@NotBlank
	private String city;
	
	@Pattern(regexp = "[A-Z]{2}$", message = "Must be 2 capital Letters")
	private String state;
	
	@Pattern(regexp = "[0-9]{5}$", message = "Must be 5 digits")
	private String zip;

	public Address() {
		
	}
	
	public Address(@NotBlank String street, @NotBlank String city, @NotBlank String state, @NotBlank String zip) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip + "]";
	}
	
}
