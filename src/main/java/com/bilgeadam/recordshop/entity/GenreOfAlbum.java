package com.bilgeadam.recordshop.entity;

public enum GenreOfAlbum {
	
	ROCK(1, "rock"), POP(2, "pop"), ARABESK(3, "arabsek"), TSM(4, "tsm"), THM(5, "thm"), ÖZGÜN(6, "özgün"),
	İNDİE(7, "indie"), RAP(8, "rap");
	
	private String name;
	private int id;
	
	GenreOfAlbum(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public GenreOfAlbum getGenreOfAlbum(String name) {
		return GenreOfAlbum.valueOf(name);
		
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
}
