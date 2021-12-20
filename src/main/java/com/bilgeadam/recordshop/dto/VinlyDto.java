package com.bilgeadam.recordshop.dto;

public class VinlyDto extends ProductDto {
	
	private int speed;
	
	private int diameter;
	
	public VinlyDto() {
		
	}
	
	public VinlyDto(int speed, int diameter) {
		super();
		this.speed = speed;
		this.diameter = diameter;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getDiameter() {
		return diameter;
	}
	
	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}
	
	@Override
	public String toString() {
		return "VinlyEntity [speed=" + speed + ", diameter=" + diameter + "]";
	}
	
}
