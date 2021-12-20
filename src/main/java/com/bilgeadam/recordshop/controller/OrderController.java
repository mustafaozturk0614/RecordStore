package com.bilgeadam.recordshop.controller;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.bilgeadam.recordshop.entity.OrderEntity;
import com.bilgeadam.recordshop.util.IDatabaseCrud;
import com.bilgeadam.recordshop.util.SingeltonLogger;

public class OrderController implements IDatabaseCrud<OrderEntity> {
	
	private static final Logger logger = SingeltonLogger.getInstance().getLogger(OrderController.class);
	
	@Override
	public boolean create(OrderEntity entity) {
		try {
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			logger.info("ekleme tamamdır" + OrderController.class);
		} catch (Exception e) {
			logger.error("ekleme anında hata meydana geldi !!!!! " + OrderController.class);
			e.printStackTrace();
		}
		return false;
	}
	
	// silmek
	@Override
	public void delete(OrderEntity entity) {
		
		try {
			OrderEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(findEntity);
				session.getTransaction().commit();
				logger.info("Silme Başarılı " + OrderEntity.class);
			}
		} catch (Exception e) {
			logger.error("silme anında hata meydana geldi !!!!! " + OrderController.class);
			e.printStackTrace();
		}
		
	}
	
	// güncellemek
	@Override
	public void update(OrderEntity entity) {
		try {
			OrderEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				// findEntity.setTitle(entity.getTitle());
				// findEntity.setIsnb(entity.getIsnb());
				// findEntity.setAuthor(entity.getAuthor());
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(findEntity);
				session.getTransaction().commit();
				logger.info("Güncelleme Başarılı " + OrderEntity.class);
			}
			
		} catch (Exception e) {
			logger.error("güncelleme anında hata meydana geldi !!!!! " + OrderController.class);
			e.printStackTrace();
		}
	}
	
	// listelemek
	@Override
	public ArrayList<OrderEntity> list() {
		Session session = databaseConnectionHibernate();
		
		String hql = "select str from OrderEntity str where str.id>=:key";
		TypedQuery<OrderEntity> typedQuery = session.createQuery(hql, OrderEntity.class);
		
		long id = 1L;
		typedQuery.setParameter("key", id);
		
		ArrayList<OrderEntity> arrayList = (ArrayList<OrderEntity>) typedQuery.getResultList();
		logger.info("listelendi " + OrderEntity.class);
		return arrayList;
	}
	
	// find
	@Override
	public OrderEntity find(long id) {
		Session session = databaseConnectionHibernate();
		OrderEntity OrderEntity;
		try {
			OrderEntity = session.find(OrderEntity.class, id);
			
			if (OrderEntity != null) {
				System.out.println("bulundu... " + OrderEntity);
				return OrderEntity;
			} else {
				System.out.println("Aradığınız kriterde sonuçlar bulunamadı ...");
				return null;
			}
		} catch (Exception e) {
			logger.error("find anında hata meydana geldi !!!!! " + OrderController.class);
			e.printStackTrace();
		}
		return null;
	}
	
	// tek kayıt donder
	@Override
	public OrderEntity singleResult(long id) {
		
		return IDatabaseCrud.super.singleResult(id);
	}
	
}
