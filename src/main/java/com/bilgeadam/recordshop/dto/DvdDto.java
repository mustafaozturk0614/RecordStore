package com.bilgeadam.recordshop.dto;

public class DvdDto extends ProductDto {
	
	private String imageQuality;
	
	public DvdDto(String imageQuality) {
		super();
		this.imageQuality = imageQuality;
	}
	
	public DvdDto() {
		
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
