package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class xxx implements Initializable {
    @FXML
    public Button btnBeginTargeting;
    @FXML
    private Button btnExit;

    @FXML
    public void buttonClicked(ActionEvent event) throws Exception {

        Stage appStage;
        Parent root2;
        if (event.getSource() == btnBeginTargeting) {
            appStage = (Stage) btnBeginTargeting.getScene().getWindow();
            root2 = FXMLLoader.load(getClass().getResource("Test.fxml"));
            Scene scene = new Scene(root2);
            appStage.setScene(scene);
            appStage.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}