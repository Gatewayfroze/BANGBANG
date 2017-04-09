package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 import javafx.application.Application;
 import javafx.event.ActionEvent;
 import javafx.fxml.FXML;
 import javafx.fxml.FXMLLoader;
 import javafx.scene.Parent;
 import javafx.scene.Scene;
 import javafx.scene.layout.AnchorPane;
 import javafx.scene.media.Media;
 import javafx.scene.media.MediaPlayer;
 import javafx.stage.Stage;

 import java.io.File;
 import java.io.IOException;

 /**
 * Created by frong on 4/8/2017.
 */
public class CreditControl {
    @FXML
    private AnchorPane rootPane2;



    @FXML
    public void loadMenu(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("xx.fxml"));

        rootPane2.getChildren().setAll(pane);

    }




}
