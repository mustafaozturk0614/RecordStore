package com.bilgeadam.recordshop.controller;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.bilgeadam.recordshop.entity.AlbumEntity;
import com.bilgeadam.recordshop.entity.OrderEntity;
import com.bilgeadam.recordshop.entity.SingerEntity;
import com.bilgeadam.recordshop.util.BAUtils;
import com.bilgeadam.recordshop.util.IDatabaseCrud;
import com.bilgeadam.recordshop.util.SingeltonLogger;

public class AlbumController implements IDatabaseCrud<AlbumEntity> {
	
	private static final Logger logger = SingeltonLogger.getInstance().getLogger(AlbumController.class);
	boolean check = false;
	
	@Override
	public boolean create(AlbumEntity entity) {
		try {
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			logger.info("ekleme tamamdır" + AlbumController.class);
			check = true;
		} catch (Exception e) {
			logger.error("ekleme anında hata meydana geldi !!!!! " + AlbumController.class);
			e.printStackTrace();
			check = false;
		}
		return check;
	}
	
	// silmek
	@Override
	public void delete(AlbumEntity entity) {
		
		try {
			AlbumEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(findEntity);
				session.getTransaction().commit();
				logger.info("Silme Başarılı " + AlbumEntity.class);
			}
		} catch (Exception e) {
			logger.error("silme anında hata meydana geldi !!!!! " + AlbumController.class);
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(AlbumEntity entity) {
		
	}
	
	public boolean update1(AlbumEntity entity) {
		try {
			AlbumEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				findEntity.setName(entity.getName());
				findEntity.setDiscountRate(entity.getDiscountRate());
				findEntity.setGenre(entity.getGenre());
				findEntity.setPrice(entity.getPrice());
				findEntity.setStockAmmount(entity.getStockAmmount());
				findEntity.setSinger(entity.getSinger());
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(findEntity);
				session.getTransaction().commit();
				logger.info("Güncelleme Başarılı " + AlbumEntity.class);
				check = true;
			}
			
		} catch (Exception e) {
			logger.error("güncelleme anında hata meydana geldi !!!!! " + AlbumController.class);
			e.printStackTrace();
			check = false;
		}
		return check;
	}
	
	// listelemek
	@Override
	public ArrayList<AlbumEntity> list() {
		Session session = databaseConnectionHibernate();
		
		String hql = "select str from AlbumEntity str where str.id>=:key";
		TypedQuery<AlbumEntity> typedQuery = session.createQuery(hql, AlbumEntity.class);
		
		long id = 1L;
		typedQuery.setParameter("key", id);
		
		ArrayList<AlbumEntity> arrayList = (ArrayList<AlbumEntity>) typedQuery.getResultList();
		logger.info("listelendi " + AlbumEntity.class);
		return arrayList;
	}
	
	@Override
	public AlbumEntity find(long id) {
		Session session = databaseConnectionHibernate();
		AlbumEntity albumEntity;
		try {
			albumEntity = session.find(AlbumEntity.class, id);
			
			if (albumEntity != null) {
				System.out.println("bulundu... " + albumEntity);
				return albumEntity;
			} else {
				System.out.println("Aradığınız kriterde sonuçlar bulunamadı ...");
				return null;
			}
		} catch (Exception e) {
			logger.error("find anında hata meydana geldi !!!!! " + AlbumController.class);
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public AlbumEntity singleResult(long id) {
		
		return IDatabaseCrud.super.singleResult(id);
	}
	
	public AlbumEntity findName(String name) {
		ArrayList<AlbumEntity> albums = list();
		Session session = databaseConnectionHibernate();
		AlbumEntity albumEntity = null;
		
		for (int i = 0; i < albums.size(); i++) {
			if (albums.get(i).getName().equalsIgnoreCase(name)) {
				
				albumEntity = albums.get(i);
				
				System.out.printf("albüm ismi %s \n", albumEntity.getName());
				System.out.printf("albüm fiyatı %f TL \n", albumEntity.getPrice());
				System.out.printf("albüm türü %s \n", albumEntity.getGenre());
				if (albumEntity.getSinger().getName() != null) {
					System.out.printf("Sanatçı %s \n", albumEntity.getSinger().getName());
				}
				
				System.out.printf("İndirim oranı %s \n", albumEntity.getDiscountRate());
				
			}
			
		}
		
		return albumEntity;
		
	}
	
	public void lastTenAlbums() {
		int i = 1;
		Session session = databaseConnectionHibernate();
		
		String hql = "select str from AlbumEntity str where str.id>=:key order by str.id desc";
		TypedQuery<AlbumEntity> typedQuery = session.createQuery(hql, AlbumEntity.class);
		
		long id = 1L;
		
		typedQuery.setParameter("key", id);
		typedQuery.setMaxResults(10);
		ArrayList<AlbumEntity> arrayList = (ArrayList<AlbumEntity>) typedQuery.getResultList();
		logger.info("listelendi " + AlbumEntity.class);
		for (AlbumEntity albumEntity : arrayList) {
			
			System.out.println(i + "-" + albumEntity.getName() + " " + albumEntity.getSinger().getName() + " "
					+ albumEntity.getSinger().getSurname());
			i++;
		}
		
	}
	
	public void discountAlbum() {
		int i = 1;
		Session session = databaseConnectionHibernate();
		
		String hql = "select str from AlbumEntity str where str.discount_rate>=:key ";
		TypedQuery<AlbumEntity> typedQuery = session.createQuery(hql, AlbumEntity.class);
		
		long id = 1L;
		
		typedQuery.setParameter("key", id);
		typedQuery.setMaxResults(15);
		ArrayList<AlbumEntity> arrayList = (ArrayList<AlbumEntity>) typedQuery.getResultList();
		logger.info("listelendi " + AlbumEntity.class);
		for (AlbumEntity albumEntity : arrayList) {
			
			System.out.println(i + "-" + albumEntity.getName() + " " + albumEntity.getSinger().getName() + " "
					+ albumEntity.getSinger().getSurname());
			i++;
		}
		
	}
	
	public void albumByGenre() {
		int i = 1;
		String genre = BAUtils.readString("Aramak istediğiniz müzik türünü giriniz");
		Session session = databaseConnectionHibernate();
		
		String hql = "select str from AlbumEntity str where str.genre in(:key) ";
		TypedQuery<AlbumEntity> typedQuery = session.createQuery(hql, AlbumEntity.class);
		
		typedQuery.setParameter("key", genre.replace("\"", ""));
		typedQuery.setMaxResults(15);
		ArrayList<AlbumEntity> arrayList = (ArrayList<AlbumEntity>) typedQuery.getResultList();
		logger.info("listelendi " + AlbumEntity.class);
		for (AlbumEntity albumEntity : arrayList) {
			
			System.out.println(i + "-" + albumEntity.getName() + " " + albumEntity.getSinger().getName() + " "
					+ albumEntity.getSinger().getSurname());
			i++;
		}
		
	}
	
	public Long findId() {
		String genre = BAUtils.readString("Aramak istediğiniz sarkıcı ismini giriniz");
		Session session = databaseConnectionHibernate();
		// select a.name from album as a where a.singer_id=(select s.id from singer as s
		// where s.name='Müslüm');
		String hql = "select str from SingerEntity str where str.name=:key";
		TypedQuery<SingerEntity> typedQuery = session.createQuery(hql, SingerEntity.class);
		
		typedQuery.setParameter("key", genre);
		
		ArrayList<SingerEntity> arrayList = (ArrayList<SingerEntity>) typedQuery.getResultList();
		logger.info("listelendi " + SingerEntity.class);
		
		Long id = arrayList.get(0).getId();
		System.out.println(id + "------------------");
		return id;
		
	}
	
	public void albumBySinger(long id) {
		int i = 1;
		Session session = databaseConnectionHibernate();
		String hql1 = "select  a from AlbumEntity as a where singer_id=:key";
		
		TypedQuery<AlbumEntity> typedQuery1 = session.createQuery(hql1, AlbumEntity.class);
		
		typedQuery1.setParameter("key", id);
		
		ArrayList<AlbumEntity> arrayList1 = (ArrayList<AlbumEntity>) typedQuery1.getResultList();
		logger.info("listelendi " + AlbumEntity.class);
		for (AlbumEntity albumEntity : arrayList1) {
			
			System.out.println(i + "-" + albumEntity.getName() + " " + albumEntity.getSinger().getName() + " "
					+ albumEntity.getSinger().getSurname());
			i++;
		}
		
	}
	
	public ArrayList<String> albumByOrder() {
		int i = 1;
		Session session = databaseConnectionHibernate();
		String hql1 = "select  a.name from AlbumEntity  a Join SingerEntity  s  ON s.id = singer_id order by sales_ammount desc";
		
		TypedQuery<String> typedQuery1 = session.createQuery(hql1, String.class);
		
		ArrayList<String> arrayList1 = (ArrayList<String>) typedQuery1.getResultList();
		logger.info("listelendi " + AlbumEntity.class);
		for (String albumEntity : arrayList1) {
			
			System.out.println(i + "-" + albumEntity);
			i++;
			
		}
		return arrayList1;
	}
	
	public void albumByPopularSinger() {
		int i = 1;
		Session session = databaseConnectionHibernate();
		String hql1 = "select  s.name from AlbumEntity  a Join SingerEntity  s  ON s.id = singer_id order by sales_ammount desc";
		
		TypedQuery<String> typedQuery1 = session.createQuery(hql1, String.class);
		ArrayList<String> arrayList2 = albumByOrder();
		ArrayList<String> arrayList1 = (ArrayList<String>) typedQuery1.getResultList();
		logger.info("listelendi " + AlbumEntity.class);
		for (int j = 0; j < arrayList1.size(); j++) {
			System.out.println(i + "- " + arrayList1.get(j) + " - " + arrayList2.get(j));
			i++;
		}
	}
	
	public void addAlbum() {
		AlbumEntity entity = new AlbumEntity();
		
		entity.setName(BAUtils.readString("Albüm ismi Giriniz"));
		
		entity.setPrice(BAUtils.readDouble("Albüm satış fiyatını Giriniz"));
		entity.setDiscountRate(BAUtils.readDouble("Albüm indirim oranını Giriniz"));
		entity.setStockAmmount(BAUtils.readInt("Albüm stok miktarını Giriniz"));
		create(entity);
		
	}
	
	public void deleteAlbum() {
		String name = BAUtils.readString("Silmek istediğiniz Albüm İsmini giriniz");
		
		delete(findName(name));
		
	}
	
	public void sortOrderDate() {
		Session session = databaseConnectionHibernate();
		
		String hql = "select str from OrderEntity str where str.id>=:key order by created_date desc";
		TypedQuery<OrderEntity> typedQuery = session.createQuery(hql, OrderEntity.class);
		
		long id = 1L;
		
		typedQuery.setParameter("key", id);
		
		ArrayList<OrderEntity> arrayList = (ArrayList<OrderEntity>) typedQuery.getResultList();
		logger.info("listelendi " + OrderEntity.class);
		for (OrderEntity orders : arrayList) {
			System.out.println(orders.toString());
		}
		
	}
}
