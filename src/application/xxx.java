package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
public class xxx  {

    @FXML
    public Button btnBeginTargeting;
    @FXML
    private Main main=new Main();

    @FXML
    public void buttonClicked(ActionEvent event) throws Exception {

        Stage appStage;
        Scene scene = main.Game();
        appStage = (Stage) btnBeginTargeting.getScene().getWindow();
        appStage.setScene(scene);
    }
    @FXML
    private AnchorPane rootPane;
    @FXML
    public void loadHowto(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("howto.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    @FXML
    public void loadCredit(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("credit.fxml"));
        rootPane.getChildren().setAll(pane);

    }

}