package com.bilgeadam.recordshop.controller;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.bilgeadam.recordshop.entity.UserEntity;
import com.bilgeadam.recordshop.util.IDatabaseCrud;
import com.bilgeadam.recordshop.util.SingeltonLogger;

public class UserEntityController implements IDatabaseCrud<UserEntity> {
	
	private static final Logger logger = SingeltonLogger.getInstance().getLogger(UserEntityController.class);
	
	@Override
	public boolean create(UserEntity entity) {
		try {
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			logger.info("ekleme tamamdır" + UserEntityController.class);
		} catch (Exception e) {
			logger.error("ekleme anında hata meydana geldi !!!!! " + UserEntityController.class);
			e.printStackTrace();
		}
		return false;
	}
	
	// silmek
	@Override
	public void delete(UserEntity entity) {
		
		try {
			UserEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(findEntity);
				session.getTransaction().commit();
				logger.info("Silme Başarılı " + UserEntity.class);
			}
		} catch (Exception e) {
			logger.error("silme anında hata meydana geldi !!!!! " + UserEntityController.class);
			e.printStackTrace();
		}
		
	}
	
	// güncellemek
	@Override
	public void update(UserEntity entity) {
		try {
			UserEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				// findEntity.setTitle(entity.getTitle());
				// findEntity.setIsnb(entity.getIsnb());
				// findEntity.setAuthor(entity.getAuthor());
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(findEntity);
				session.getTransaction().commit();
				logger.info("Güncelleme Başarılı " + UserEntity.class);
			}
			
		} catch (Exception e) {
			logger.error("güncelleme anında hata meydana geldi !!!!! " + UserEntityController.class);
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<UserEntity> list() {
		Session session = databaseConnectionHibernate();
		
		String hql = "select str from UserEntity str where str.id>=:key";
		TypedQuery<UserEntity> typedQuery = session.createQuery(hql, UserEntity.class);
		
		long id = 1L;
		typedQuery.setParameter("key", id);
		
		ArrayList<UserEntity> arrayList = (ArrayList<UserEntity>) typedQuery.getResultList();
		logger.info("listelendi " + UserEntity.class);
		session.close();
		return arrayList;
	}
	
	@Override
	public UserEntity find(long id) {
		Session session = databaseConnectionHibernate();
		UserEntity UserEntity;
		try {
			UserEntity = session.find(UserEntity.class, id);
			
			if (UserEntity != null) {
				System.out.println("bulundu... " + UserEntity);
				return UserEntity;
			} else {
				System.out.println("Aradığınız kriterde sonuçlar bulunamadı ...");
				return null;
			}
		} catch (Exception e) {
			logger.error("find anında hata meydana geldi !!!!! " + UserEntityController.class);
			e.printStackTrace();
		}
		return null;
	}
	
	// tek kayıt donder
	@Override
	public UserEntity singleResult(long id) {
		
		return IDatabaseCrud.super.singleResult(id);
	}
	
}
