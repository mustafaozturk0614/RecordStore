package com.bilgeadam.recordshop.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bilgeadam.recordshop.entity.AlbumEntity;
import com.bilgeadam.recordshop.entity.CdEntity;
import com.bilgeadam.recordshop.entity.DvdEntity;
import com.bilgeadam.recordshop.entity.OrderDetailEntity;
import com.bilgeadam.recordshop.entity.OrderEntity;
import com.bilgeadam.recordshop.entity.ProductEntity;
import com.bilgeadam.recordshop.entity.SingerEntity;
import com.bilgeadam.recordshop.entity.UserEntity;
import com.bilgeadam.recordshop.entity.VinlyEntity;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = sessionFactoryHibernate();
	
	// method
	private static SessionFactory sessionFactoryHibernate() {
		try {
			// instance
			Configuration configuration = new Configuration();
			
			// entity classlarımızı buraya ekleyeceğiz
			configuration.addAnnotatedClass(ProductEntity.class);
			configuration.addAnnotatedClass(AlbumEntity.class);
			
			configuration.addAnnotatedClass(CdEntity.class);
			configuration.addAnnotatedClass(VinlyEntity.class);
			configuration.addAnnotatedClass(DvdEntity.class);
			configuration.addAnnotatedClass(SingerEntity.class);
			configuration.addAnnotatedClass(UserEntity.class);
			configuration.addAnnotatedClass(OrderEntity.class);
			configuration.addAnnotatedClass(OrderDetailEntity.class);
			SessionFactory factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			return factory;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// dış dünyada bununla bu classa erişim sağlayabileceğim.
	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}
}
