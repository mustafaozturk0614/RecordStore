package com.bilgeadam.recordshop.controller;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.bilgeadam.recordshop.entity.SingerEntity;
import com.bilgeadam.recordshop.util.IDatabaseCrud;
import com.bilgeadam.recordshop.util.SingeltonLogger;

public class SingerController implements IDatabaseCrud<SingerEntity> {
	
	private static final Logger logger = SingeltonLogger.getInstance().getLogger(SingerEntity.class);
	boolean check = false;
	
	@Override
	public boolean create(SingerEntity entity) {
		try {
			Session session = databaseConnectionHibernate();
			
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			logger.info("ekleme tamamdır" + SingerEntity.class);
			check = true;
		} catch (Exception e) {
			logger.error("ekleme anında hata meydana geldi !!!!! " + SingerEntity.class);
			e.printStackTrace();
			check = false;
		}
		return check;
	}
	
	// silmek
	@Override
	public void delete(SingerEntity entity) {
		
		try {
			SingerEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(findEntity);
				session.getTransaction().commit();
				logger.info("Silme Başarılı " + SingerEntity.class);
			}
		} catch (Exception e) {
			logger.error("silme anında hata meydana geldi !!!!! " + SingerEntity.class);
			e.printStackTrace();
		}
		
	}
	
	// güncellemek
	@Override
	public void update(SingerEntity entity) {
		try {
			SingerEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				// findEntity.setTitle(entity.getTitle());
				// findEntity.setIsnb(entity.getIsnb());
				// findEntity.setAuthor(entity.getAuthor());
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(findEntity);
				session.getTransaction().commit();
				logger.info("Güncelleme Başarılı " + SingerEntity.class);
			}
			
		} catch (Exception e) {
			logger.error("güncelleme anında hata meydana geldi !!!!! " + SingerEntity.class);
			e.printStackTrace();
		}
	}
	
	// listelemek
	@Override
	public ArrayList<SingerEntity> list() {
		Session session = databaseConnectionHibernate();
		
		String hql = "select str from SingerEntity str where str.id>=:key";
		TypedQuery<SingerEntity> typedQuery = session.createQuery(hql, SingerEntity.class);
		
		long id = 1L;
		typedQuery.setParameter("key", id);
		
		ArrayList<SingerEntity> arrayList = (ArrayList<SingerEntity>) typedQuery.getResultList();
		logger.info("listelendi " + SingerEntity.class);
		for (SingerEntity singerEntity : arrayList) {
			System.out.println(singerEntity.toString());
		}
		return arrayList;
	}
	
	// find
	@Override
	public SingerEntity find(long id) {
		Session session = databaseConnectionHibernate();
		SingerEntity SingerEntity;
		try {
			SingerEntity = session.find(SingerEntity.class, id);
			
			if (SingerEntity != null) {
				System.out.println("bulundu... " + SingerEntity);
				return SingerEntity;
			} else {
				System.out.println("Aradığınız kriterde sonuçlar bulunamadı ...");
				return null;
			}
		} catch (Exception e) {
			logger.error("find anında hata meydana geldi !!!!! " + SingerEntity.class);
			e.printStackTrace();
		}
		return null;
	}
	
	// tek kayıt donder
	@Override
	public SingerEntity singleResult(long id) {
		
		return IDatabaseCrud.super.singleResult(id);
	}
	
	public static void main(String[] args) {
		SingerController singerController = new SingerController();
		singerController.list();
	}
}
