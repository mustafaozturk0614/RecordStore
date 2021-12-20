package com.bilgeadam.recordshop.controller;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.bilgeadam.recordshop.entity.CdEntity;
import com.bilgeadam.recordshop.util.IDatabaseCrud;
import com.bilgeadam.recordshop.util.SingeltonLogger;

public class CdController implements IDatabaseCrud<CdEntity> {
	
	private static final Logger logger = SingeltonLogger.getInstance().getLogger(CdController.class);
	
	@Override
	public boolean create(CdEntity entity) {
		try {
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			logger.info("ekleme tamamdır" + CdController.class);
		} catch (Exception e) {
			logger.error("ekleme anında hata meydana geldi !!!!! " + CdController.class);
			e.printStackTrace();
		}
		return false;
	}
	
	// silmek
	@Override
	public void delete(CdEntity entity) {
		
		try {
			CdEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(findEntity);
				session.getTransaction().commit();
				logger.info("Silme Başarılı " + CdEntity.class);
			}
		} catch (Exception e) {
			logger.error("silme anında hata meydana geldi !!!!! " + CdController.class);
			e.printStackTrace();
		}
		
	}
	
	// güncellemek
	@Override
	public void update(CdEntity entity) {
		try {
			CdEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				// findEntity.setTitle(entity.getTitle());
				// findEntity.setIsnb(entity.getIsnb());
				// findEntity.setAuthor(entity.getAuthor());
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(findEntity);
				session.getTransaction().commit();
				logger.info("Güncelleme Başarılı " + CdEntity.class);
			}
			
		} catch (Exception e) {
			logger.error("güncelleme anında hata meydana geldi !!!!! " + CdController.class);
			e.printStackTrace();
		}
	}
	
	// listelemek
	@Override
	public ArrayList<CdEntity> list() {
		Session session = databaseConnectionHibernate();
		
		String hql = "select str from CdEntity str where str.id>=:key";
		TypedQuery<CdEntity> typedQuery = session.createQuery(hql, CdEntity.class);
		
		long id = 1L;
		typedQuery.setParameter("key", id);
		
		ArrayList<CdEntity> arrayList = (ArrayList<CdEntity>) typedQuery.getResultList();
		logger.info("listelendi " + CdEntity.class);
		return arrayList;
	}
	
	// find
	@Override
	public CdEntity find(long id) {
		Session session = databaseConnectionHibernate();
		CdEntity CdEntity;
		try {
			CdEntity = session.find(CdEntity.class, id);
			
			if (CdEntity != null) {
				System.out.println("bulundu... " + CdEntity);
				return CdEntity;
			} else {
				System.out.println("Aradığınız kriterde sonuçlar bulunamadı ...");
				return null;
			}
		} catch (Exception e) {
			logger.error("find anında hata meydana geldi !!!!! " + CdController.class);
			e.printStackTrace();
		}
		return null;
	}
	
	// tek kayıt donder
	@Override
	public CdEntity singleResult(long id) {
		
		return IDatabaseCrud.super.singleResult(id);
	}
	
}
