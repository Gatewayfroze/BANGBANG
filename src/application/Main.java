package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage=primaryStage;
        stage.setScene(SelectCharector());
        stage.setTitle("Game");
        stage.show();
    }
//พ่องมึงดิสัส
    public Scene SelectCharector() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("xx.fxml"));
        Scene scene=new Scene(root);
        Image cursor = new Image("cursor.png");
        scene.setCursor(new ImageCursor(cursor,
                cursor.getWidth() / 2,
                cursor.getHeight() /2));
        String musicFile = "src/bgm.mp3";     // For example

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        mediaPlayer.setVolume(1);

        return scene;
    }
    public static void main(String[] args) {
        launch(args);
    }

}
