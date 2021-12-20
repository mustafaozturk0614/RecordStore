package com.bilgeadam.recordshop.dto;

public class ProductDto {
	
	private long id;
	
	private AlbumDto albumId;
	
	public ProductDto() {
		
	}
	
	public AlbumDto getAlbumId() {
		return albumId;
	}
	
	public void setAlbumId(AlbumDto albumId) {
		this.albumId = albumId;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "ProductEntity [id=" + id + ", albumId=" + albumId + "]";
	}
	
}
