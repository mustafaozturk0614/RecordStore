package com.bilgeadam.recordshop.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.bilgeadam.recordshop.controller.AlbumController;
import com.bilgeadam.recordshop.controller.SingerController;
import com.bilgeadam.recordshop.entity.AlbumEntity;
import com.bilgeadam.recordshop.entity.GenreOfAlbum;
import com.bilgeadam.recordshop.entity.SingerEntity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddAlbumController implements Initializable {
	
	private String[] genreStrings = { "ROCK", "POP", "ARABESK", "TSM", "THM", "ÖZGÜN", "İNDİE", "RAP" };
	@FXML
	private Button addBtn;
	
	@FXML
	private Button cancelButton;
	
	@FXML
	private ChoiceBox<String> chociceBox;
	
	@FXML
	private TextField txtDiscount;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtPrice;
	
	@FXML
	private TextField txtSingerName;
	
	@FXML
	private TextField txtSingerSurname;
	
	@FXML
	private TextField txtStock;
	private Stage dialogStage;
	private Parent root;
	private Scene scene;
	
	@FXML
	void addAlbum(ActionEvent event) {
		
		boolean check1 = false;
		boolean check2 = false;
		AlbumEntity entity = new AlbumEntity();
		SingerEntity entity2 = new SingerEntity();
		AlbumController controller = new AlbumController();
		SingerController singerController = new SingerController();
		double price = Double.valueOf(txtPrice.getText());
		entity.setName(txtName.getText());
		String genre = chociceBox.getValue();
		
		entity.setGenre(GenreOfAlbum.valueOf(genre));
		entity.setPrice(price);
		entity.setDiscountRate(Double.valueOf(txtDiscount.getText()));
		entity.setStockAmmount(Long.valueOf(txtStock.getText()));
		
		entity2.setName(txtSingerName.getText());
		entity2.setSurname(txtSingerSurname.getText());
		entity.setSinger(entity2);
		check2 = singerController.create(entity2);
		check1 = controller.create(entity);
		
		if (check1 && check2) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(this.dialogStage);
			alert.setTitle("Ekleme");
			alert.setHeaderText("Ekleme İşlemi Tamamlanmıştır Ana Menüye Dönülüyor");
			alert.showAndWait();
			ButtonType type = alert.getResult();
			System.out.println(type.getText());
			if (type.getText().equalsIgnoreCase("ok")) {
				switchMain(event);
			}
			
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.chociceBox.getItems().addAll(genreStrings);
		
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
}
