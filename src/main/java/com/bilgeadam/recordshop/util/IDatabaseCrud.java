package com.bilgeadam.recordshop.util;

import java.util.ArrayList;

import org.hibernate.Session;

public interface IDatabaseCrud<T> {
	
	public boolean create(T entity);
	
	public void delete(T entity);
	
	public void update(T entity);
	
	default ArrayList<T> list() {
		return null;
	}
	
	default T find(long id) {
		return null;
	}
	
	default T singleResult(long id) {
		return null;
	}
	
	default Session databaseConnectionHibernate() {
		return HibernateUtil.getSessionfactory().openSession();
	}
	
}
