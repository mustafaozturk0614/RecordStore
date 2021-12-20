package com.bilgeadam.recordshop.view;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RecordController implements Initializable {
	
	private RecordMain recordMain;
	private Stage stage;
	private Scene scene;
	private AnchorPane root;
	@FXML
	private Button btnuser;
	@FXML
	private Button btnadmin;
	@FXML
	private Button btnplay;
	@FXML
	private Button btnstop;
	@FXML
	private Button btnpause;
	@FXML
	private Button btnnext;
	@FXML
	private Button btnpre;
	@FXML
	private Button btnmute;
	@FXML
	private Slider volumeSlider;
	@FXML
	private ProgressBar songProgressBar;
	@FXML
	private Label songLabel;
	
	private File directory;
	private File[] files;
	
	private Media media;
	
	private MediaPlayer mediaPlayer;
	
	private ArrayList<File> songs;
	private int songNumber;
	
	private boolean running;
	
	private Timer timer;
	
	private TimerTask task;
	
	public RecordController() {
		super();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		songs = new ArrayList<>();
		
		directory = new File("music");
		
		files = directory.listFiles();
		
		if (files != null) {
			for (File file : files) {
				songs.add(file);
			}
		}
		media = new Media(songs.get(songNumber).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		songLabel.setText(songs.get(songNumber).getName());
		
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
				
			}
		});
		songProgressBar.setStyle("-fx-accent:#6A1B9A");
		volumeSlider.setStyle("-fx-accent:#7F40B0");
	}
	
	@FXML
	public void playMedia() {
		beginTimer();
		mediaPlayer.play();
		
	}
	
	@FXML
	public void stopMedia() {
		songProgressBar.setProgress(0);
		cancelTimer();
		mediaPlayer.seek(Duration.seconds(0));
		mediaPlayer.pause();
	}
	
	@FXML
	public void pauseMedia() {
		cancelTimer();
		mediaPlayer.pause();
	}
	
	@FXML
	public void muteMedia() {
		
	}
	
	@FXML
	public void nextMedia() {
		
		if (songNumber < songs.size() - 1) {
			songNumber++;
			mediaPlayer.stop();
			if (running) {
				cancelTimer();
			}
			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			songLabel.setText(songs.get(songNumber).getName());
			playMedia();
			
		} else {
			
			songNumber = 0;
			mediaPlayer.stop();
			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			songLabel.setText(songs.get(songNumber).getName());
			playMedia();
		}
		
	}
	
	@FXML
	public void preMedia() {
		if (songNumber > 0) {
			songNumber--;
			mediaPlayer.stop();
			if (running) {
				cancelTimer();
			}
			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			songLabel.setText(songs.get(songNumber).getName());
			playMedia();
			
		} else {
			
			songNumber = songs.size() - 1;
			mediaPlayer.stop();
			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			songLabel.setText(songs.get(songNumber).getName());
			playMedia();
		}
		
	}
	
	@FXML
	private void loginAdmin(ActionEvent event) {
		boolean okClicked = recordMain.showAdminLoginDialog();
	}
	
	@FXML
	private void loginUser(ActionEvent event) {
		boolean okClicked = recordMain.showUserLoginDialog();
	}
	
	public RecordMain getRecordMain() {
		return recordMain;
	}
	
	public void setRecordMain(RecordMain recordMain) {
		this.recordMain = recordMain;
	}
	
	public void beginTimer() {
		timer = new Timer();
		task = new TimerTask() {
			
			@Override
			public void run() {
				running = true;
				double current = mediaPlayer.getCurrentTime().toSeconds();
				double end = media.getDuration().toSeconds();
				System.out.println(current / end);
				songProgressBar.setProgress(current / end);
				
				if (current / end == 1) {
					cancelTimer();
				}
				
			}
			
		};
		timer.scheduleAtFixedRate(task, 0, 1000);
		
	}
	
	public void cancelTimer() {
		running = false;
		timer.cancel();
		
	}
}