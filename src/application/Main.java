package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

        return scene;
    }
    public static void main(String[] args) {
        launch(args);
    }

}
