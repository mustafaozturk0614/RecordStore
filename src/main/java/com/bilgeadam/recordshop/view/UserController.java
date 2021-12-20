package com.bilgeadam.recordshop.view;

import java.util.ArrayList;

import com.bilgeadam.recordshop.controller.AlbumController;
import com.bilgeadam.recordshop.controller.UserEntityController;
import com.bilgeadam.recordshop.entity.AlbumEntity;
import com.bilgeadam.recordshop.entity.UserEntity;
import com.bilgeadam.recordshop.util.IDatabaseCrud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
	
}
