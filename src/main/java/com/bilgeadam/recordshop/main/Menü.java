package com.bilgeadam.recordshop.main;

import java.util.ArrayList;
import java.util.HashMap;

import com.bilgeadam.recordshop.controller.AlbumController;
import com.bilgeadam.recordshop.controller.OrderController;
import com.bilgeadam.recordshop.controller.OrderDetailController;
import com.bilgeadam.recordshop.controller.UserEntityController;
import com.bilgeadam.recordshop.entity.AlbumEntity;
import com.bilgeadam.recordshop.entity.OrderDetailEntity;
import com.bilgeadam.recordshop.entity.OrderEntity;
import com.bilgeadam.recordshop.entity.UserEntity;
import com.bilgeadam.recordshop.util.BAUtils;

public class Menü {
	static UserEntity userEntity = new UserEntity();
	static AlbumController controller = new AlbumController();
	
	public static void loginMenu() {
		
		do {
			HashMap<Integer, String> menu = new HashMap<>();
			
			menu.put(1, "Admin Girişi");
			menu.put(2, "Kullanıcı Girişi");
			
			switch (BAUtils.menu(menu)) {
				case 1:
					loginAdmin();
					break;
				case 2:
					loginUser();
					break;
				
				default:
					break;
			}
		} while (BAUtils.wantToEnd("çıkmak için q", "q"));
		
	}
	
	private static void loginUser() {
		UserEntityController userController = new UserEntityController();
		ArrayList<UserEntity> users = userController.list();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equalsIgnoreCase(BAUtils.readString("Kullanıcı adını giriniz"))
					&& users.get(i).getPassword().equalsIgnoreCase(BAUtils.readString("Şifrenizi giriniz"))) {
				userEntity = users.get(i);
				System.out.println("Giriş işleminiz başarılı Ana menüye Yönlendiriliyorsunuz...");
				try {
					Thread.sleep(1000);
					MainMenü();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				System.out.println("Kullanıcı adınız ve şifreiniz yanlış");
			}
		}
		
	}
	
	private static void adminMenu() {
		HashMap<Integer, String> menu = new HashMap<>();
		
		menu.put(1, "Album Ekleme");
		menu.put(2, "Album Silme");
		menu.put(3, "Sipariş Tarihine Göre Sıralama");
		
		switch (BAUtils.menu(menu)) {
			case 1:
				controller.addAlbum();
				
				break;
			case 2:
				
				controller.deleteAlbum();
				break;
			
			case 3:
				
				controller.sortOrderDate();
				break;
			
			default:
				break;
		}
	}
	
	private static void loginAdmin() {
		
		if (BAUtils.readString("Admin isminizi giriniz").equalsIgnoreCase("admin")
				&& BAUtils.readString("Şifreinizi Giriniz").equalsIgnoreCase("qwerty")) {
			
			System.out.println("Giriş Başarılı");
			adminMenu();
			
		}
		
	}
	
	public static void MainMenü() {
		
		HashMap<Integer, String> menu = new HashMap<>();
		
		menu.put(1, "Son Eklenen 10 Albüm");
		menu.put(2, "İndirimdeki 15 Albüm");
		menu.put(3, "Türe Göre Albümler");
		menu.put(4, "Sanatçıya Göre Albümler");
		menu.put(5, "En Çok Şipariş Edilen Ürünler");
		menu.put(6, "En çok Satış Yapan Şarkıcı");
		menu.put(7, "Albüm Detay Sayfası");
		menu.put(8, "Şipariş Sayfası");
		
		switch (BAUtils.menu(menu)) {
			case 1:
				
				controller.lastTenAlbums();
				break;
			case 2:
				
				controller.discountAlbum();
				break;
			case 3:
				controller.albumByGenre();
				break;
			case 4:
				
				controller.albumBySinger(controller.findId());
				break;
			case 5:
				controller.albumByOrder();
				break;
			case 6:
				controller.albumByPopularSinger();
				break;
			
			case 7:
				recordDetailPage();
				break;
			
			default:
				break;
		}
		
	}
	
	public static void recordDetailPage() {
		
		AlbumEntity entity = controller.findName(BAUtils.readString("lütfen albüm ismi giriniz"));
		HashMap<Integer, String> menu = new HashMap<>();
		
		menu.put(1, "Albüm Türü Detayları");
		menu.put(2, "Sanatçı Detayları");
		
		switch (BAUtils.menu(menu)) {
			case 1:
				entity.getGenre();
				
				break;
			case 2:
				System.out.printf("Sanatçının:\n adı: %s\n soyadı: %s\n sanatçı hakkında :&s\n",
						entity.getSinger().getName(), entity.getSinger().getSurname(), entity.getSinger().getAbout());
				
				break;
			
			default:
				break;
		}
		
	}
	
	public static void orderPage() {
		int i = 1;
		HashMap<Integer, String> menu = new HashMap<>();
		
		ArrayList<AlbumEntity> albums = controller.list();
		AlbumEntity albumEntity1 = new AlbumEntity();
		ArrayList<AlbumEntity> myOrders = new ArrayList<>();
		
		listProduct(albums);
		
		menu.put(1, "Sepete Ekle");
		menu.put(2, "Sepetten sil");
		
		switch (BAUtils.menu(menu)) {
			case 1:
				addBox(controller, myOrders);
				break;
			case 2:
				
				break;
			
			default:
				break;
		}
	}
	
	private static void addBox(AlbumController controller, ArrayList<AlbumEntity> myOrders) {
		AlbumEntity albumEntity1;
		long salesNumber = BAUtils.readInt("lütfen Satış almak istediğini ürünün satış Numarasını giriniz");
		int ammount = BAUtils.readInt("Lütfen satın almak istediğiniz ürün miktarını giriniz");
		albumEntity1 = controller.find(salesNumber);
		albumEntity1.setStockAmmount(albumEntity1.getStockAmmount() - ammount);
		albumEntity1.setSalesAmmount(albumEntity1.getSalesAmmount() + ammount);
		OrderEntity orderEntity = new OrderEntity();
		OrderController orderController = new OrderController();
		OrderDetailController orderDetailController = new OrderDetailController();
		OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
		
		orderDetailEntity.setAlbum(albumEntity1);
		orderDetailEntity.setAmount(ammount);
		orderEntity.setUser(userEntity);
		orderEntity.getOrderDetail().add(orderDetailEntity);
		myOrders.add(albumEntity1);
		orderController.create(orderEntity);
		orderDetailController.create(orderDetailEntity);
	}
	
	private static void listProduct(ArrayList<AlbumEntity> albums) {
		for (AlbumEntity albumEntity : albums) {
			if (albumEntity.getStockAmmount() > 2) {
				System.out.println("satış no: " + albumEntity.getId() + "-  " + albumEntity.getName().toUpperCase()
						+ "-  " + albumEntity.getSinger().getName() + "-  " + albumEntity.getSinger().getSurname()
						+ "- " + albumEntity.getPrice() + " stok miktarı: " + albumEntity.getStockAmmount()
						+ " indirim oranı " + albumEntity.getDiscountRate());
				
			}
			
		}
	}
	
	public static void main(String[] args) {
		orderPage();
	}
}
