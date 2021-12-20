package com.bilgeadam.recordshop.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private double amount;
	@Column(name = "unit_price")
	private double unitPrice;
	@Column(name = "line_total")
	private double lineTotal;
	@ManyToOne
	private OrderEntity orders;
	@OneToOne
	@JoinColumn(name = "album_id")
	private AlbumEntity album;
	
	public OrderDetailEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double getUnitPrice() {
		
		return unitPrice;
	}
	
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public double getLineTotal() {
		
		return lineTotal;
	}
	
	public void setLineTotal(double lineTotal) {
		this.lineTotal = lineTotal;
	}
	
	public OrderEntity getOrders() {
		return orders;
	}
	
	public AlbumEntity getAlbum() {
		return album;
	}
	
	public void setAlbum(AlbumEntity album) {
		this.album = album;
		this.unitPrice = album.getPrice();
		this.lineTotal = this.amount * this.unitPrice;
	}
	
	public void setOrders(OrderEntity orders) {
		double line = 0;
		List<OrderDetailEntity> or = orders.getOrderDetail();
		for (int i = 0; i < or.size(); i++) {
			line += or.get(i).getLineTotal();
		}
		
		orders.setTotalPrice(line);
		this.orders = orders;
	}
	
	@Override
	public String toString() {
		return "OrderDetailEntity [id=" + id + ", amount=" + amount + ", unitPrice=" + unitPrice + ", lineTotal="
				+ lineTotal + ", orders=" + orders + "]";
	}
	
}
