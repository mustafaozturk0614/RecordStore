package com.bilgeadam.recordshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vinly")
public class VinlyEntity extends ProductEntity {
	
	@Column(name = "speed")
	private int speed;
	@Column(name = "diameter")
	private int diameter;
	
	public VinlyEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public VinlyEntity(int speed, int diameter) {
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
