package application;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {


    @FXML
    private AnchorPane rootPane;




    @FXML
    public void loadHowto(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("howto.fxml"));
        rootPane.getChildren().setAll(pane);

    }
    @FXML
    public void loadCredit(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("credit.fxml"));
        rootPane.getChildren().setAll(pane);

    }









}

