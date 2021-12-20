package com.bilgeadam.recordshop.view;

import java.io.IOException;

import com.bilgeadam.recordshop.entity.AlbumEntity;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RecordMain extends Application {
	private Stage primary;
	private AnchorPane rootPane;
	Stage primaStage;
	private ObservableList<AlbumEntity> albumEntities;
	
	@Override
	public void start(Stage primaryStage) {
		initPrimaryStage(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void initPrimaryStage(Stage primaryStage) {
		this.primary = primaryStage;
		this.primary.getIcons().add(new Image(RecordMain.class.getResourceAsStream("images/icon1.png")));
		this.primary.setTitle("Record Shop");
		initRootLayout();
	}
	
	private void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RecordMain.class.getResource("Record.fxml"));
			this.rootPane = (AnchorPane) loader.load();
			Scene scene = new Scene(rootPane, 900, 500);
			scene.getStylesheets().add(RecordMain.class.getResource("application.css").toExternalForm());
			primary.setScene(scene);
			primary.show();
			primary.setResizable(false);
			RecordController controller1 = loader.getController();
			controller1.setRecordMain(this);
			this.primary.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean showAdminLoginDialog() {
		
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RecordMain.class.getResource("admin.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			String css = RecordMain.class.getResource("admin.css").toExternalForm();
			
			Stage dialogStage = new Stage();
			// dialogStage.getIcons().add(new
			// Image(getClass().getResourceAsStream("resources/edit.png")));
			dialogStage.setTitle("Admin Girişi");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primary);
			Scene scene = new Scene(page);
			scene.getStylesheets().add(css);
			dialogStage.setScene(scene);
			
			AdminController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean showUserLoginDialog() {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RecordMain.class.getResource("user.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			// dialogStage.getIcons().add(new
			// Image(getClass().getResourceAsStream("resources/edit.png")));
			dialogStage.setTitle("Kullanıcı Girişi");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primary);
			String css = RecordMain.class.getResource("userlogin.css").toExternalForm();
			Scene scene = new Scene(page);
			scene.getStylesheets().add(css);
			
			dialogStage.setScene(scene);
			
			UserController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean showUserProces() {
		UserProcesController controller = new UserProcesController();
		
		try {
			
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RecordMain.class.getResource("UserProces.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			String css = RecordMain.class.getResource("userlogin.css").toExternalForm();
			Stage dialogStage = new Stage();
			// dialogStage.getIcons().add(new
			// Image(getClass().getResourceAsStream("resources/edit.png")));
			dialogStage.setTitle("İşlemler");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			
			Scene scene = new Scene(page);
			scene.getStylesheets().add(css);
			dialogStage.setScene(scene);
			
			controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setRecorMain(this);
			
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean showAdminProces() {
		
		try {
			
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RecordMain.class.getResource("addAlbum.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			String css = RecordMain.class.getResource("admin.css").toExternalForm();
			
			Stage dialogStage = new Stage();
			// dialogStage.getIcons().add(new
			// Image(getClass().getResourceAsStream("resources/edit.png")));
			dialogStage.setTitle("İşlemler");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			AdminPageController controller = new AdminPageController();
			Scene scene = new Scene(page);
			scene.getStylesheets().add(css);
			dialogStage.setScene(scene);
			
			controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Stage getPrimary() {
		return primary;
	}
	
	public void setPrimary(Stage primary) {
		this.primary = primary;
	}
	
	public AnchorPane getRootPane() {
		return rootPane;
	}
	
	public void setRootPane(AnchorPane rootPane) {
		this.rootPane = rootPane;
	}
	
	public Stage getPrimaStage() {
		return primaStage;
	}
	
	public void setPrimaStage(Stage primaStage) {
		this.primaStage = primaStage;
	}
	
}
