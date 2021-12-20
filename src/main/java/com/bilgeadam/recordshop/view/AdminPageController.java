package com.bilgeadam.recordshop.view;

import java.io.IOException;

import com.bilgeadam.recordshop.controller.AlbumController;
import com.bilgeadam.recordshop.controller.SingerController;
import com.bilgeadam.recordshop.entity.AlbumEntity;
import com.bilgeadam.recordshop.entity.GenreOfAlbum;
import com.bilgeadam.recordshop.entity.SingerEntity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminPageController {
	private Stage dialogStage;
	private Scene scene;
	private Parent root;
	@FXML
	private Button addBtn;
	
	@FXML
	private Button delBtn;
	
	@FXML
	private Button sortBtn;
	
	@FXML
	private Button updtBtn;
	@FXML
	private Button okButton;
	
	@FXML
	private Button cancelButton;
	@FXML
	private TextField txtDiscount;
	
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtdName;
	
	@FXML
	private TextField txtPrice;
	
	@FXML
	private TextField txtSingerName;
	
	@FXML
	private TextField txtSingerSurname;
	
	@FXML
	private TextField txtStock;
	@FXML
	private TextField txtGenre;
	
	private boolean okClicked = false;
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	public void switchScene(ActionEvent event) {
		try {
			
			root = FXMLLoader.load(RecordMain.class.getResource("addAlbum.fxml"));
			dialogStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			dialogStage.setScene(scene);
			dialogStage.setWidth(900);
			dialogStage.setHeight(600);
			dialogStage.setResizable(false);
			dialogStage.show();
			
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void switchMain(ActionEvent event) {
		try {
			
			root = FXMLLoader.load(RecordMain.class.getResource("AdminPage.fxml"));
			dialogStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			dialogStage.setScene(scene);
			dialogStage.setWidth(900);
			dialogStage.setHeight(600);
			dialogStage.setResizable(false);
			dialogStage.show();
			
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void switchDelete(ActionEvent event) {
		try {
			
			root = FXMLLoader.load(RecordMain.class.getResource("deleteAlbum.fxml"));
			dialogStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			dialogStage.setScene(scene);
			dialogStage.setWidth(900);
			dialogStage.setHeight(600);
			dialogStage.setResizable(false);
			dialogStage.show();
			
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void switchUpdate(ActionEvent event) {
		try {
			
			root = FXMLLoader.load(RecordMain.class.getResource("updateAlbum.fxml"));
			dialogStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			dialogStage.setScene(scene);
			dialogStage.setWidth(900);
			dialogStage.setHeight(600);
			dialogStage.setResizable(false);
			dialogStage.show();
			
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML
	void deleteAlbum(ActionEvent event) {
		AlbumEntity entity = new AlbumEntity();
		SingerEntity entity2 = new SingerEntity();
		AlbumController controller = new AlbumController();
		SingerController singerController = new SingerController();
		String nameString = txtdName.getText();
		entity = controller.findName(nameString);
		controller.delete(entity);
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(this.dialogStage);
		alert.setTitle("Silme");
		alert.setHeaderText(entity.getSinger().getName() + " adlı şarkıcının" + entity.getName() + " adlı albümünü ");
		alert.setContentText("Silmek İstediğinize Eminmisiniz!!!");
		alert.showAndWait();
		ButtonType type = alert.getResult();
		System.out.println(type.getText());
		if (type.getText().equalsIgnoreCase("ok")) {
			switchMain(event);
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert.initOwner(this.dialogStage);
			alert.setTitle("Bilgilendirme");
			alert.setHeaderText("Silme İşlemi Başarıyla Gerçekleşmiştir");
			alert.setContentText("Ana Menüye Dönülüyor");
			alert.showAndWait();
		}
		
	}
	
	@FXML
	void cancel(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(this.dialogStage);
		alert.setTitle("Ekleme");
		alert.setHeaderText(" Ana Menüye Dönmek istediğinize Eminmisiniz");
		alert.showAndWait();
		ButtonType type = alert.getResult();
		System.out.println(type.getText());
		if (type.getText().equalsIgnoreCase("ok")) {
			switchMain(event);
		}
	}
	
	@FXML
	void sortAlbum(ActionEvent event) {
		
	}
	
	@FXML
	void updateAlbum(ActionEvent event) {
		
		AlbumEntity entity = new AlbumEntity();
		SingerEntity entity2 = new SingerEntity();
		AlbumController controller = new AlbumController();
		SingerController singerController = new SingerController();
		
		entity = controller.findName(txtName.getText());
		String genre = txtGenre.getText();
		
		String singername = txtSingerName.getText();
		String singerSurnameString = txtSingerSurname.getText();
		
		if (genre != null && genre.trim() != "") {
			entity.setGenre(GenreOfAlbum.valueOf(genre));
			
		}
		if (txtDiscount.getText() != null && txtDiscount.getText().trim() != "") {
			double dıscount = Double.parseDouble(txtDiscount.getText());
			entity.setDiscountRate(dıscount);
		}
		if (txtPrice.getText() != null && txtPrice.getText().trim() != "") {
			double price = Double.parseDouble(txtPrice.getText());
			entity.setPrice(price);
		}
		if (txtStock.getText() != null && txtStock.getText().trim() != "") {
			long stock = Long.parseLong(txtStock.getText());
			entity.setStockAmmount(stock);
		}
		if (txtSingerName.getText() != null && txtSingerName.getText().trim() != "") {
			entity.getSinger().setName(singername);
			
		}
		if (txtSingerSurname.getText() != null && txtSingerSurname.getText().trim() != "") {
			entity.getSinger().setSurname(singerSurnameString);
			
		}
		
		boolean check = controller.update1(entity);
		
		if (check) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(this.dialogStage);
			alert.setTitle("Güncelleme");
			alert.setHeaderText("Güncelleme İşlemi Tamamlanmıştır Ana Menüye Dönülüyor");
			alert.showAndWait();
			ButtonType type = alert.getResult();
			System.out.println(type.getText());
			if (type.getText().equalsIgnoreCase("ok")) {
				switchMain(event);
			}
		}
	}
	
}
