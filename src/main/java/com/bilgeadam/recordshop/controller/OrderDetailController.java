package com.bilgeadam.recordshop.controller;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.bilgeadam.recordshop.entity.OrderDetailEntity;
import com.bilgeadam.recordshop.util.IDatabaseCrud;
import com.bilgeadam.recordshop.util.SingeltonLogger;

public class OrderDetailController implements IDatabaseCrud<OrderDetailEntity> {
	
	private static final Logger logger = SingeltonLogger.getInstance().getLogger(OrderDetailController.class);
	
	@Override
	public boolean create(OrderDetailEntity entity) {
		try {
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			logger.info("ekleme tamamdır" + OrderDetailController.class);
		} catch (Exception e) {
			logger.error("ekleme anında hata meydana geldi !!!!! " + OrderDetailController.class);
			e.printStackTrace();
		}
		return false;
	}
	
	// silmek
	@Override
	public void delete(OrderDetailEntity entity) {
		
		try {
			OrderDetailEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(findEntity);
				session.getTransaction().commit();
				logger.info("Silme Başarılı " + OrderDetailEntity.class);
			}
		} catch (Exception e) {
			logger.error("silme anında hata meydana geldi !!!!! " + OrderDetailController.class);
			e.printStackTrace();
		}
		
	}
	
	// güncellemek
	@Override
	public void update(OrderDetailEntity entity) {
		try {
			OrderDetailEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				// findEntity.setTitle(entity.getTitle());
				// findEntity.setIsnb(entity.getIsnb());
				// findEntity.setAuthor(entity.getAuthor());
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(findEntity);
				session.getTransaction().commit();
				logger.info("Güncelleme Başarılı " + OrderDetailEntity.class);
			}
			
		} catch (Exception e) {
			logger.error("güncelleme anında hata meydana geldi !!!!! " + OrderDetailController.class);
			e.printStackTrace();
		}
	}
	
	// listelemek
	@Override
	public ArrayList<OrderDetailEntity> list() {
		Session session = databaseConnectionHibernate();
		
		String hql = "select str from OrderDetailEntity str where str.id>=:key";
		TypedQuery<OrderDetailEntity> typedQuery = session.createQuery(hql, OrderDetailEntity.class);
		
		long id = 1L;
		typedQuery.setParameter("key", id);
		
		ArrayList<OrderDetailEntity> arrayList = (ArrayList<OrderDetailEntity>) typedQuery.getResultList();
		logger.info("listelendi " + OrderDetailEntity.class);
		return arrayList;
	}
	
	// find
	@Override
	public OrderDetailEntity find(long id) {
		Session session = databaseConnectionHibernate();
		OrderDetailEntity OrderDetailEntity;
		try {
			OrderDetailEntity = session.find(OrderDetailEntity.class, id);
			
			if (OrderDetailEntity != null) {
				System.out.println("bulundu... " + OrderDetailEntity);
				return OrderDetailEntity;
			} else {
				System.out.println("Aradığınız kriterde sonuçlar bulunamadı ...");
				return null;
			}
		} catch (Exception e) {
			logger.error("find anında hata meydana geldi !!!!! " + OrderDetailController.class);
			e.printStackTrace();
		}
		return null;
	}
	
	// tek kayıt donder
	@Override
	public OrderDetailEntity singleResult(long id) {
		
		return IDatabaseCrud.super.singleResult(id);
	}
	
}
