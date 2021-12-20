package com.bilgeadam.recordshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "album")
public class AlbumEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private double price;
	@Column(name = "discount_rate")
	private double discountRate;
	@OneToOne
	@JoinColumn(name = "singer_id")
	private SingerEntity singer;
	@Column(name = "stock_ammount")
	private long stockAmmount;
	@Column(name = "sales_ammount")
	private long salesAmmount;
	@Enumerated(value = EnumType.STRING)
	private GenreOfAlbum genre;
	
	public AlbumEntity() {
		super();
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public GenreOfAlbum getGenre() {
		return genre;
	}
	
	public void setGenre(GenreOfAlbum genre) {
		this.genre = genre;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getDiscountRate() {
		return discountRate;
	}
	
	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}
	
	public SingerEntity getSinger() {
		return singer;
	}
	
	public String getSingerName() {
		return singer.getName();
	}
	
	public void setSinger(SingerEntity singer) {
		this.singer = singer;
	}
	
	public long getStockAmmount() {
		return stockAmmount;
	}
	
	public void setStockAmmount(long stockAmmount) {
		this.stockAmmount = stockAmmount;
	}
	
	public long getSalesAmmount() {
		return salesAmmount;
	}
	
	public void setSalesAmmount(long salesAmmount) {
		this.salesAmmount = salesAmmount;
	}
	
	@Override
	public String toString() {
		return "AlbumEntity [id=" + id + ", name=" + name + ", price=" + price + ", discountRate=" + discountRate
				+ ", singer=" + singer + ", stockAmmount=" + stockAmmount + ", salesAmmount=" + salesAmmount
				+ ", genre=" + genre + "]";
	}
}
