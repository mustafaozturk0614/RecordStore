package com.bilgeadam.recordshop.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	@JoinColumn(name = "album_id")
	private AlbumEntity albumId;
	
	public ProductEntity() {
		
	}
	
	public AlbumEntity getAlbumId() {
		return albumId;
	}
	
	public void setAlbumId(AlbumEntity albumId) {
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
