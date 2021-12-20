package com.bilgeadam.recordshop.dto;

public class UserDto {
	
	private long id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private AddressDto adress;
	
	public UserDto() {
		
	}
	
	public UserDto(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public AddressDto getAdress() {
		return adress;
	}
	
	public void setAdress(AddressDto adress) {
		this.adress = adress;
	}
	
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", adress=" + adress + "]";
	}
	
}
