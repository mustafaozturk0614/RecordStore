package com.bilgeadam.recordshop.controller;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.bilgeadam.recordshop.entity.VinlyEntity;
import com.bilgeadam.recordshop.util.IDatabaseCrud;
import com.bilgeadam.recordshop.util.SingeltonLogger;

public class VinlyController implements IDatabaseCrud<VinlyEntity> {
	
	private static final Logger logger = SingeltonLogger.getInstance().getLogger(VinlyController.class);
	
	@Override
	public boolean create(VinlyEntity entity) {
		try {
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			logger.info("ekleme tamamdır" + VinlyController.class);
		} catch (Exception e) {
			logger.error("ekleme anında hata meydana geldi !!!!! " + VinlyController.class);
			e.printStackTrace();
		}
		return false;
	}
	
	// silmek
	@Override
	public void delete(VinlyEntity entity) {
		
		try {
			VinlyEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(findEntity);
				session.getTransaction().commit();
				logger.info("Silme Başarılı " + VinlyEntity.class);
			}
		} catch (Exception e) {
			logger.error("silme anında hata meydana geldi !!!!! " + VinlyController.class);
			e.printStackTrace();
		}
		
	}
	
	// güncellemek
	@Override
	public void update(VinlyEntity entity) {
		try {
			VinlyEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				// findEntity.setTitle(entity.getTitle());
				// findEntity.setIsnb(entity.getIsnb());
				// findEntity.setAuthor(entity.getAuthor());
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(findEntity);
				session.getTransaction().commit();
				logger.info("Güncelleme Başarılı " + VinlyEntity.class);
			}
			
		} catch (Exception e) {
			logger.error("güncelleme anında hata meydana geldi !!!!! " + VinlyController.class);
			e.printStackTrace();
		}
	}
	
	// listelemek
	@Override
	public ArrayList<VinlyEntity> list() {
		Session session = databaseConnectionHibernate();
		
		String hql = "select str from VinlyEntity str where str.id>=:key";
		TypedQuery<VinlyEntity> typedQuery = session.createQuery(hql, VinlyEntity.class);
		
		long id = 1L;
		typedQuery.setParameter("key", id);
		
		ArrayList<VinlyEntity> arrayList = (ArrayList<VinlyEntity>) typedQuery.getResultList();
		logger.info("listelendi " + VinlyEntity.class);
		return arrayList;
	}
	
	// find
	@Override
	public VinlyEntity find(long id) {
		Session session = databaseConnectionHibernate();
		VinlyEntity VinlyEntity;
		try {
			VinlyEntity = session.find(VinlyEntity.class, id);
			
			if (VinlyEntity != null) {
				System.out.println("bulundu... " + VinlyEntity);
				return VinlyEntity;
			} else {
				System.out.println("Aradığınız kriterde sonuçlar bulunamadı ...");
				return null;
			}
		} catch (Exception e) {
			logger.error("find anında hata meydana geldi !!!!! " + VinlyController.class);
			e.printStackTrace();
		}
		return null;
	}
	
	// tek kayıt donder
	@Override
	public VinlyEntity singleResult(long id) {
		
		return IDatabaseCrud.super.singleResult(id);
	}
	
}
