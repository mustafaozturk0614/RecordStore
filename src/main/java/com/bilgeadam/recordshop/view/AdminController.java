package com.bilgeadam.recordshop.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminController {
	
	private Stage dialogStage;
	private Scene scene;
	private Parent root;
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField psfpasword;
	@FXML
	private Label lblusername;
	@FXML
	private Label lblpassword;
	@FXML
	private Button okButton;
	
	@FXML
	private Button cancelButton;
	
	private boolean okClicked = false;
	
	@FXML
	private void initialize() {
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	public boolean adminLogin() {
		String username = txtUsername.getText();
		String password = psfpasword.getText();
		if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("qwerty")) {
			
			System.out.println("Giriş Başarılı");
			this.okClicked = true;
			
		}
		return this.okClicked;
	}
	
	public void switchScene() {
		try {
			this.okClicked = adminLogin();
			if (okClicked) {
				root = FXMLLoader.load(RecordMain.class.getResource("AdminPage.fxml"));
				dialogStage.getScene().setRoot(root);
				dialogStage.setWidth(900);
				dialogStage.setHeight(600);
				dialogStage.setResizable(false);
				
			} else {
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
