package com.bilgeadam.recordshop.controller;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.bilgeadam.recordshop.entity.DvdEntity;
import com.bilgeadam.recordshop.util.IDatabaseCrud;
import com.bilgeadam.recordshop.util.SingeltonLogger;

public class DvdController implements IDatabaseCrud<DvdEntity> {
	
	private static final Logger logger = SingeltonLogger.getInstance().getLogger(DvdController.class);
	
	@Override
	public boolean create(DvdEntity entity) {
		try {
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			logger.info("ekleme tamamdır" + DvdController.class);
		} catch (Exception e) {
			logger.error("ekleme anında hata meydana geldi !!!!! " + DvdController.class);
			e.printStackTrace();
		}
		return false;
	}
	
	// silmek
	@Override
	public void delete(DvdEntity entity) {
		
		try {
			DvdEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(findEntity);
				session.getTransaction().commit();
				logger.info("Silme Başarılı " + DvdEntity.class);
			}
		} catch (Exception e) {
			logger.error("silme anında hata meydana geldi !!!!! " + DvdController.class);
			e.printStackTrace();
		}
		
	}
	
	// güncellemek
	@Override
	public void update(DvdEntity entity) {
		try {
			DvdEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				// findEntity.setTitle(entity.getTitle());
				// findEntity.setIsnb(entity.getIsnb());
				// findEntity.setAuthor(entity.getAuthor());
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(findEntity);
				session.getTransaction().commit();
				logger.info("Güncelleme Başarılı " + DvdEntity.class);
			}
			
		} catch (Exception e) {
			logger.error("güncelleme anında hata meydana geldi !!!!! " + DvdController.class);
			e.printStackTrace();
		}
	}
	
	// listelemek
	@Override
	public ArrayList<DvdEntity> list() {
		Session session = databaseConnectionHibernate();
		
		String hql = "select str from DvdEntity str where str.id>=:key";
		TypedQuery<DvdEntity> typedQuery = session.createQuery(hql, DvdEntity.class);
		
		long id = 1L;
		typedQuery.setParameter("key", id);
		
		ArrayList<DvdEntity> arrayList = (ArrayList<DvdEntity>) typedQuery.getResultList();
		logger.info("listelendi " + DvdEntity.class);
		return arrayList;
	}
	
	// find
	@Override
	public DvdEntity find(long id) {
		Session session = databaseConnectionHibernate();
		DvdEntity DvdEntity;
		try {
			DvdEntity = session.find(DvdEntity.class, id);
			
			if (DvdEntity != null) {
				System.out.println("bulundu... " + DvdEntity);
				return DvdEntity;
			} else {
				System.out.println("Aradığınız kriterde sonuçlar bulunamadı ...");
				return null;
			}
		} catch (Exception e) {
			logger.error("find anında hata meydana geldi !!!!! " + DvdController.class);
			e.printStackTrace();
		}
		return null;
	}
	
	// tek kayıt donder
	@Override
	public DvdEntity singleResult(long id) {
		
		return IDatabaseCrud.super.singleResult(id);
	}
	
}
