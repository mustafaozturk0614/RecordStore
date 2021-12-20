package com.bilgeadam.recordshop.view;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.bilgeadam.recordshop.controller.AlbumController;
import com.bilgeadam.recordshop.entity.AlbumEntity;
import com.bilgeadam.recordshop.entity.SingerEntity;
import com.bilgeadam.recordshop.util.IDatabaseCrud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class UserProcesController {
	RecordMain recordMain;
	IDatabaseCrud<AlbumEntity> albumCrud = new AlbumController();
	@FXML
	private TableView<AlbumEntity> tblalbum;
	
	@FXML
	private TableView<SingerEntity> tblSingerTableView;
	
	private ObservableList<AlbumEntity> albums = FXCollections.observableArrayList();
	private Stage dialogStage;
	
	private boolean okClicked = false;
	@FXML
	private TableColumn<AlbumEntity, String> columnAlbumName;
	@FXML
	private TableColumn<SingerEntity, String> columnSingerId;
	@FXML
	private TableColumn<SingerEntity, String> columnSingerSurname;
	
	@FXML
	private void initialize() {
		ObservableList<SingerEntity> singers = FXCollections.observableArrayList();
		this.albums = lastTenAlbums();
		tblalbum.setItems(albums);
		for (AlbumEntity albumEntity : albums) {
			singers.add(albumEntity.getSinger());
			
		}
		tblSingerTableView.setItems(singers);
		
		columnAlbumName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		columnSingerId.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnSingerSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
		
	}
	
	public ObservableList<AlbumEntity> getalbum() {
		
		if (this.albums == null) {
			this.albums = FXCollections.observableArrayList();
		}
		return this.albums;
	}
	
	private void İnitAlbums() {
		
	}
	
	public void showPersonDetails() {
		
	}
	
	public ObservableList<AlbumEntity> lastTenAlbums() {
		int j = 0;
		Session session = albumCrud.databaseConnectionHibernate();
		
		String hql = "select str from AlbumEntity str where str.id>=:key order by str.id desc";
		TypedQuery<AlbumEntity> typedQuery = session.createQuery(hql, AlbumEntity.class);
		
		long id = 1L;
		
		typedQuery.setParameter("key", id);
		typedQuery.setMaxResults(10);
		ObservableList<AlbumEntity> albumEntities = this.albums;
		ArrayList<AlbumEntity> arrayList = (ArrayList<AlbumEntity>) typedQuery.getResultList();
		this.albums = FXCollections.observableArrayList(arrayList);
		return this.albums;
		
	}
	
	public void setRecorMain(RecordMain recordMain) {
		this.recordMain = recordMain;
		tblalbum.setItems(this.getAlbums());
	}
	
	public IDatabaseCrud<AlbumEntity> getAlbumCrud() {
		return albumCrud;
	}
	
	public void setAlbumCrud(IDatabaseCrud<AlbumEntity> albumCrud) {
		this.albumCrud = albumCrud;
	}
	
	public RecordMain getRecordMain() {
		return recordMain;
	}
	
	public void setRecordMain(RecordMain recordMain) {
		this.recordMain = recordMain;
	}
	
	public TableView<AlbumEntity> getTblalbum() {
		return tblalbum;
	}
	
	public void setTblalbum(TableView<AlbumEntity> tblalbum) {
		this.tblalbum = tblalbum;
	}
	
	public ObservableList<AlbumEntity> getAlbums() {
		return albums;
	}
	
	public void setAlbums(ObservableList<AlbumEntity> albums) {
		this.albums = albums;
	}
	
	public TableColumn<AlbumEntity, String> getColumnAlbumName() {
		return columnAlbumName;
	}
	
	public void setColumnAlbumName(TableColumn<AlbumEntity, String> columnAlbumName) {
		this.columnAlbumName = columnAlbumName;
	}
	
	public TableColumn<SingerEntity, String> getColumnSingerId() {
		return columnSingerId;
	}
	
	public void setColumnSingerId(TableColumn<SingerEntity, String> columnSingerId) {
		this.columnSingerId = columnSingerId;
	}
	
	public Stage getDialogStage() {
		return dialogStage;
	}
	
	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
		
	}
	
	public boolean isOkClicked() {
		return okClicked;
		
	}
	
}
