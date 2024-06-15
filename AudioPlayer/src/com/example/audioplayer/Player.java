package com.example.audioplayer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Player extends Application {
    private MediaPlayer mediaPlayer;
    private DoublyLinkedList songList = new DoublyLinkedList();
    private boolean playing = false;
    private Slider mediaProgressSlider;
    private GridPane gridPane;
    private double totalSeconds = 0;
    private MediaPlayer backgroundMediaPlayer;
    private Button playButton;
    private Slider volumeSlider;
    private boolean resumecontrol = false;

    @Override
    public void start(Stage primaryStage) {
        // Create UI components
        mediaProgressSlider = new Slider();
        mediaProgressSlider.getStyleClass().add("media-progress-slider");

        Button returnbutton = new Button();
        returnbutton.getStyleClass().add("return-button");

        Button listButton = new Button("View All Songs");
        listButton.getStyleClass().add("list-button");

        playButton = new Button();
        playButton.getStyleClass().add("image-button");

        Button prevButton = new Button();
        prevButton.getStyleClass().add("image-button-prev");

        Button nextButton = new Button();
        nextButton.getStyleClass().add("image-button-next");

        Button addButton = new Button("Add Song");
        addButton.getStyleClass().add("addsong-button");

        Label volumeLabel = new Label("Volume:");
        volumeLabel.getStyleClass().add("label");

        volumeSlider = new Slider(0, 100, 50);
        volumeSlider.getStyleClass().add("slider");

        // GridPane layout to arrange components
        gridPane = new GridPane();
        gridPane.getStyleClass().add("grid-pane");
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Set column constraints
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(col1, col1, col1, col1, col1);

        gridPane.add(playButton, 0, 1);
        gridPane.add(prevButton, 1, 1);
        gridPane.add(nextButton, 2, 1);
        gridPane.add(addButton, 3, 1);
        gridPane.add(volumeLabel, 0, 2);
        gridPane.add(volumeSlider, 1, 2, 3, 1);
        gridPane.add(mediaProgressSlider, 0, 3, 4, 1);
        gridPane.add(listButton, 0, 4, 4, 1);

        gridPane.setPrefSize(600, 160);

        // Set up video background (not changed)
        URL resource = getClass().getResource("videos/MusicBackground.mp4");
        if (resource == null) {
            System.out.println("Video file not found!");
            return;
        }

        String videoPath = resource.toExternalForm();
        Media media = new Media(videoPath);
        backgroundMediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(backgroundMediaPlayer);

        backgroundMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // Combine the video and UI components
        StackPane root = new StackPane();
        root.getChildren().addAll(mediaView, gridPane);

        // Event handlers for buttons (not changed)
        playButton.setOnAction(e -> {
            if (playing) {
                backgroundMediaPlayer.pause();
                if (mediaPlayer != null) {
                    mediaPlayer.pause();
                }
                playButton.getStyleClass().removeAll("image-button-pressed");
                playButton.getStyleClass().add("image-button");
                playing = false;
            } else if (mediaPlayer != null) {
                backgroundMediaPlayer.play();
                mediaPlayer.play();
                mediaPlayer.setVolume(volumeSlider.getValue()/100);                
                mediaPlayer.seek(Duration.seconds(mediaProgressSlider.getValue()));
                playButton.getStyleClass().removeAll("image-button");
                playButton.getStyleClass().add("image-button-pressed");
                playing = true;
            }
        });

        prevButton.setOnAction(e -> {
            Song prevSong = songList.getPrevSong();
            if (prevSong != null) {
                playNewMedia(prevSong.getFilePath());
                playButton.getStyleClass().removeAll("image-button");
                playButton.getStyleClass().add("image-button-pressed");
                playing = true;
                mediaPlayer.setVolume(volumeSlider.getValue() / 100);
                backgroundMediaPlayer.play();
            }
        });

        nextButton.setOnAction(e -> {
            Song nextSong = songList.getNextSong();
            if (nextSong != null) {
                playNewMedia(nextSong.getFilePath());
                playButton.getStyleClass().removeAll("image-button");
                playButton.getStyleClass().add("image-button-pressed");
                playing = true;
                mediaPlayer.setVolume(volumeSlider.getValue() / 100);
                backgroundMediaPlayer.play();
            }
        });

        addButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.aac")
            );
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            boolean exist = false;

            try {
                for (Song songs : songList.getAllSongs()) {
                    if (songs.getFilePath().equals(selectedFile.getAbsolutePath())) {
                        exist = true;

                    }
                }

            } catch (NullPointerException a) {

            }
            if (selectedFile != null && !exist) {
                songList.addSong(new Song(selectedFile.getPath(), selectedFile.getName()));
                playNewMedia(selectedFile.getPath());
                playButton.getStyleClass().removeAll("image-button");
                playButton.getStyleClass().add("image-button-pressed");
                backgroundMediaPlayer.play();
                playing = true;
            }
        });

        List<Button> SongsButtons = new ArrayList<>();
        Label emptyListLabel = new Label("Song List Is Empty");
        emptyListLabel.getStyleClass().add("empty-list-message");

        // Event handler for listButton (updated)
        listButton.setOnAction(e -> {
            gridPane.getChildren().clear();
            gridPane.add(returnbutton, 0, 0);

            // Check if songList is empty
            if (songList.getCurrentSong() == null) {
                SongsButtons.clear();
                gridPane.add(emptyListLabel, 1, 5, 2, 1);
            } else {
                List<Song> allSongs = songList.getAllSongs();
                int row = 4; // Start adding songs from row 4

                for (Song song : allSongs) {
                    Button songButton = new Button(song.getTitle());
                    songButton.getStyleClass().add("list-button");
                    gridPane.add(songButton, 0, row++, 4, 1);
                    SongsButtons.add(songButton);
                    songButton.setOnAction(event -> {
                        playButton.getStyleClass().removeAll("image-button");
                        playButton.getStyleClass().add("image-button-pressed");
                        playing = true;
                        songList.setCurrentSong(song); // Set the current song
                        backgroundMediaPlayer.play();
                        playNewMedia(song.getFilePath());
                    });
                }
            }
        });

        returnbutton.setOnAction(e -> {
            gridPane.getChildren().clear();
            gridPane.add(playButton, 0, 1);
            gridPane.add(prevButton, 1, 1);
            gridPane.add(nextButton, 2, 1);
            gridPane.add(addButton, 3, 1);
            gridPane.add(volumeLabel, 0, 2);
            gridPane.add(volumeSlider, 1, 2, 3, 1);
            gridPane.add(mediaProgressSlider, 0, 3, 4, 1);
            gridPane.add(listButton, 0, 4, 4, 1);
        });

        // Event handler for volumeSlider (not changed)
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(newValue.doubleValue() / 100);
            }
        });

        // Create and set the scene with a smaller size
        Scene scene = new Scene(root, 600, 350); // Adjusted size
        scene.getStylesheets().add("file:/C:/Users/marqu/eclipse-workspace/AuidoPlayer/css/style.css");
        primaryStage.setTitle("Music Player");
        primaryStage.setScene(scene);
        primaryStage.show();
        if (playing) {
            backgroundMediaPlayer.play();
        } else {
            backgroundMediaPlayer.pause();
        }
    }

    private void playNewMedia(String mediaPath) {
        File mediaFile = new File(mediaPath);
        if (!mediaFile.exists()) {
            System.err.println("File not found: " + mediaPath);
            return;
        }

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }

        Media media = new Media(mediaFile.toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        // Set the volume to the current slider value
        mediaPlayer.setVolume(volumeSlider.getValue() / 100);

        mediaPlayer.play();

        mediaPlayer.setOnReady(() -> {
            Duration totalDuration = mediaPlayer.getTotalDuration();
            totalSeconds = totalDuration.toSeconds();

            // Set the maximum value of the slider
            mediaProgressSlider.setMax(totalSeconds);
            mediaProgressSlider.setMaxWidth(totalSeconds);
           
            
        });

        mediaPlayer.setOnEndOfMedia(() -> {
            backgroundMediaPlayer.pause();
            playButton.getStyleClass().removeAll("image-button-pressed");
            playButton.getStyleClass().add("image-button");
            playing = false;
        });

        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            mediaProgressSlider.setValue(newValue.toSeconds());
            mediaPlayer.setVolume(volumeSlider.getValue()/100);
            
        });

        // Listener for final slider value change
        mediaProgressSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (mediaProgressSlider.isValueChanging()) {
                // Seek to the calculated time
            	mediaPlayer.setVolume(volumeSlider.getValue() / 100);
            	playButton.getStyleClass().removeAll("button-pressed");
            	playButton.getStyleClass().add("image-button-pressed");
            	backgroundMediaPlayer.play();
                mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
            }
        });

        mediaPlayer.setOnError(() -> {
            System.err.println("Media error: " + mediaPlayer.getError());
        });
    }

    private String formatDuration(Duration duration) {
        int minutes = (int) duration.toMinutes();
        int seconds = (int) duration.toSeconds() % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    @Override
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
