package com.bilgeadam.recordshop.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.bilgeadam.recordshop.controller.AlbumController;
import com.bilgeadam.recordshop.controller.UserEntityController;
import com.bilgeadam.recordshop.entity.AlbumEntity;
import com.bilgeadam.recordshop.entity.UserEntity;
import com.bilgeadam.recordshop.util.IDatabaseCrud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserController {
	private RecordMain recordMain;
	private Stage dialogStage;
	private Scene scene;
	private Parent root;
	private ObservableList<AlbumEntity> albums;
	IDatabaseCrud<AlbumEntity> albumCrud = new AlbumController();
	
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField psfpasword;
	@FXML
	private Label lblusername;
	@FXML
	private Label lblpassword;
	@FXML
	private Button logİnbtn;
	@FXML
	private Button signİnbtn;
	
	private boolean okClicked = false;
	
	public ObservableList<AlbumEntity> getAlbums() {
		if (this.albums == null) {
			this.albums = FXCollections.observableArrayList();
		}
		return this.albums;
	}
	
	@FXML
	private void initialize() {
		
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	public void lastTenAlbums() {
		int i = 1;
		
		Session session = albumCrud.databaseConnectionHibernate();
		
		String hql = "select str from AlbumEntity str where str.id>=:key order by str.id desc";
		TypedQuery<AlbumEntity> typedQuery = session.createQuery(hql, AlbumEntity.class);
		
		long id = 1L;
		
		typedQuery.setParameter("key", id);
		typedQuery.setMaxResults(10);
		ObservableList<AlbumEntity> albumEntities = this.getAlbums();
		ArrayList<AlbumEntity> arrayList = (ArrayList<AlbumEntity>) typedQuery.getResultList();
		System.out.println("listelendi " + AlbumEntity.class);
		for (AlbumEntity albumEntity : arrayList) {
			this.albums.add(albumEntity);
			
			System.out.println(i + "-" + albumEntity.getName() + " " + albumEntity.getSinger().getName() + " "
					+ albumEntity.getSinger().getSurname());
			i++;
		}
		
	}
	
	public boolean userLogin() {
		String username = txtUsername.getText();
		String password = psfpasword.getText();
		UserEntityController userController = new UserEntityController();
		UserEntity userEntity = new UserEntity();
		ArrayList<UserEntity> users = userController.list();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equalsIgnoreCase(username)
					&& users.get(i).getPassword().equalsIgnoreCase(password)) {
				userEntity = users.get(i);
				this.okClicked = true;
				System.out.println("Giriş işleminiz başarılı Ana menüye Yönlendiriliyorsunuz...");
				try {
					Thread.sleep(1000);
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
			} else {
				System.out.println("Kullanıcı adınız ve şifreiniz yanlış");
			}
		}
		
		return this.okClicked;
	}
	
	@FXML
	private void loginUser(ActionEvent event) {
		recordMain = new RecordMain();
		this.okClicked = userLogin();
		if (okClicked) {
			boolean okClicked = recordMain.showUserProces();
		}
	}
	
	public void switchScene() {
		this.okClicked = userLogin();
		if (okClicked) {
			recordMain = new RecordMain();
			try {
				root = FXMLLoader.load(RecordMain.class.getResource("UserProces.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			UserProcesController userProcesController = new UserProcesController();
			this.lastTenAlbums();
			userProcesController.setRecorMain(recordMain);
			dialogStage.getScene().setRoot(root);
			dialogStage.setWidth(900);
			dialogStage.setHeight(600);
			dialogStage.setResizable(false);
			
		} else {
			
		}
		
	}
	
}
