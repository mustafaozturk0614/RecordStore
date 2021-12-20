package com.bilgeadam.recordshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dvd")
public class DvdEntity extends ProductEntity {
	@Column(name = "image_quality")
	private String imageQuality;
	
	public DvdEntity(String imageQuality) {
		super();
		this.imageQuality = imageQuality;
	}
	
	public DvdEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public String getImageQuality() {
		return imageQuality;
	}
	
	public void setImageQuality(String imageQuality) {
		this.imageQuality = imageQuality;
	}
	
	@Override
	public String toString() {
		return "DvdEntity [imageQuality=" + imageQuality + "]";
	}
	
}
