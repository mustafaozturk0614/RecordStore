package com.bilgeadam.recordshop.dto;

public class AddressDto {
	
	private String city;
	
	private String country;
	
	private String postCode;
	
	public AddressDto() {
		
	}
	
	public AddressDto(String city, String country, String postCode) {
		this.city = city;
		this.country = country;
		this.postCode = postCode;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getPostCode() {
		return postCode;
	}
	
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
}
