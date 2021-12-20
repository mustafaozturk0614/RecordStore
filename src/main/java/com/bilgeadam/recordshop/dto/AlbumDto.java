package com.bilgeadam.recordshop.dto;

import com.bilgeadam.recordshop.entity.SingerEntity;

import javafx.beans.property.SimpleStringProperty;

public class AlbumDto {
	
	private SimpleStringProperty id;
	
	private SimpleStringProperty name;
	
	private String nameString;
	
	private SimpleStringProperty price;
	
	private SimpleStringProperty discountRate;
	
	private SimpleStringProperty singer;
	
	private SimpleStringProperty genre;
	private SimpleStringProperty stockAmmount;
	
	private String genreString;
	
	private long stockAmmountLong;
	
	private long idLong;
	
	private double priceDouble;
	
	private double discountRateDouble;
	
	private SingerEntity singerDto;
	
	public AlbumDto(String nameString, String genreString, long stockAmmountLong, long idLong, double priceDouble,
			double discountRateDouble, SingerEntity singerDto) {
		super();
		this.nameString = nameString;
		this.genreString = genreString;
		this.stockAmmountLong = stockAmmountLong;
		this.idLong = idLong;
		this.priceDouble = priceDouble;
		this.discountRateDouble = discountRateDouble;
		this.singerDto = singerDto;
	}
	
	public String getGenreString() {
		return genreString;
	}
	
	public void setGenreString(String genreString) {
		this.genreString = genreString;
	}
	
	public long getStockAmmountLong() {
		return stockAmmountLong;
	}
	
	public void setStockAmmountLong(long stockAmmountLong) {
		this.stockAmmountLong = stockAmmountLong;
	}
	
	public long getIdLong() {
		return idLong;
	}
	
	public void setIdLong(long idLong) {
		this.idLong = idLong;
	}
	
	public double getPriceDouble() {
		return priceDouble;
	}
	
	public void setPriceDouble(double priceDouble) {
		this.priceDouble = priceDouble;
	}
	
	public double getDiscountRateDouble() {
		return discountRateDouble;
	}
	
	public void setDiscountRateDouble(double discountRateDouble) {
		this.discountRateDouble = discountRateDouble;
	}
	
	public SingerEntity getSingerDto() {
		return singerDto;
	}
	
	public void setSingerDto(SingerEntity singerDto) {
		this.singerDto = singerDto;
	}
	
	public String getNameString() {
		return nameString;
	}
	
	public AlbumDto() {
		
	}
	
	public AlbumDto(String id, String name, String price, String discountRate, String singer, String genre,
			String stockAmmount) {
		super();
		
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.price = new SimpleStringProperty(price);
		this.discountRate = new SimpleStringProperty(discountRate);
		this.singer = new SimpleStringProperty(singer);
		this.genre = new SimpleStringProperty(genre);
		this.stockAmmount = new SimpleStringProperty(stockAmmount);
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setNameString(String name) {
		this.nameString = name;
	}
	
	public String getGenre() {
		return genre.get();
	}
	
	public void setGenre(String genre) {
		this.genreString = genre;
	}
	
	public String getStockAmount() {
		return stockAmmount.get();
	}
	
	public void setStockAmount(long stockAmmount) {
		this.stockAmmountLong = stockAmmount;
	}
	
	public String getId() {
		return id.get();
	}
	
	public void setId(long id) {
		this.idLong = id;
	}
	
	public String getPrice() {
		return price.get();
	}
	
	public void setPrice(double price) {
		this.priceDouble = price;
	}
	
	public String getDiscountRate() {
		return discountRate.get();
	}
	
	public void setDiscountRate(double discountRate) {
		this.discountRateDouble = discountRate;
	}
	
	public String getSinger() {
		return singer.get();
	}
	
	public void setSinger(SingerEntity singerEntity) {
		this.singerDto = singerEntity;
	}
	
	@Override
	public String toString() {
		return "AlbumDto [id=" + id + ", name=" + name + ", price=" + price + ", discountRate=" + discountRate
				+ ", singer=" + singer + ", genre=" + genre + ", stockAmmount=" + stockAmmount + "]";
	}
	
}
