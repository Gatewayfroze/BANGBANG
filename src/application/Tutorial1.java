package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Tutorial1 extends Application {

	public void start(Stage theStage) {

		theStage.setTitle("2D Graphic");
		Group root = new Group();
		Scene theScene = new Scene(root);
		theStage.setScene(theScene);

		Canvas canvas = new Canvas(800, 600);
		root.getChildren().add(canvas);

		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.AQUAMARINE);
		gc.setStroke(Color.BROWN);
		gc.setLineWidth(1);

		Font thisfont = Font.font("Bauhaus 93 Regular",FontWeight.SEMI_BOLD,50);
		gc.setFont(thisfont);
		gc.fillText("Hello World", 50, 50);
		gc.strokeText("Hello World", 50, 50);

		theStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
