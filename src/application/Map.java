package application;

/**
 * Created by frong on 4/7/2017.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;





public class Map extends Application {
    private Tile[][] board = new Tile[12][10];
    private Pane root = new Pane();


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();


    }

    private Parent createContent() {
        root.setPrefSize(1280, 720);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {

                Tile tile = new Tile(i, j);
                tile.setTranslateX(j * 100);
                tile.setTranslateY(i * 100);
                root.getChildren().add(tile);
                board[j][i] = tile;
            }


        }
        return root;
    }

    private class Tile extends StackPane {
        private Text text = new Text();

        public Tile(int i, int j) {
            Rectangle border = new Rectangle(100, 100);
            if (i == 1 || j == 3 || i == 7 || j == 11) {
                ImageView imgview = new ImageView(new Image("Dirt.png"));
                setAlignment(Pos.CENTER);
                getChildren().addAll(border, imgview);
            }else {
                ImageView imgview = new ImageView(new Image("tile.jpg"));
                setAlignment(Pos.CENTER);
                getChildren().addAll(border, imgview);
            }
        }


        public  void main(String[] args) {
            launch(args);
        }
    }
}

