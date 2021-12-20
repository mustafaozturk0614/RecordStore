package com.bilgeadam.recordshop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "singer")
public class SingerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String surname;
	
	private String about;
	
	public SingerEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public SingerEntity(long id, String name, String surname, String about) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.about = about;
	}
	
	@Override
	public String toString() {
		return "SingerEntity [id=" + id + ", name=" + name + ", surname=" + surname + ", about=" + about + "]";
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getAbout() {
		return about;
	}
	
	public void setAbout(String about) {
		this.about = about;
	}
	
}
