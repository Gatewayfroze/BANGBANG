package application;

/**
 * Created by frong on 4/9/2017.
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by frong on 4/8/2017.
 */
public class howtoControl {

    @FXML
    private AnchorPane rootPane2;

    @FXML
    public void loadMenu(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("sample.fxml"));
        rootPane2.getChildren().setAll(pane);

    }


}
