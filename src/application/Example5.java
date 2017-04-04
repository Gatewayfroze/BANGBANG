package application;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.animation.AnimationTimer;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

// Collect the Money Bags!
public class Example5 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	/*private class SpriteAnimation extends Transition{
		private
	}*/
	
	@Override
	public void start(Stage theStage) {
		theStage.setTitle("Collect the Money Bags!");// set title

		Group root = new Group();// create group
		Scene theScene = new Scene(root);// create scene with group inside
		theStage.setScene(theScene);// set the scene to scene

		Canvas canvas = new Canvas(512, 512);// create canvas size 512*512
		root.getChildren().add(canvas);// add canvas to root

		ArrayList<String> input = new ArrayList<String>();

		theScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				if (!input.contains(code))
					input.add(code);
			}
		});

		theScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				input.remove(code);
			}
		});

		GraphicsContext gc = canvas.getGraphicsContext2D();

		Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
		gc.setFont(theFont);
		gc.setFill(Color.GREEN);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);

		Sprite briefcase = new Sprite();
		briefcase.setImage("file:///D:/pokemon.gif");
		briefcase.setPosition(200, 0);

		ArrayList<Sprite> moneybagList = new ArrayList<Sprite>();

		for (int i = 0; i < 10; i++) {
			Sprite moneybag = new Sprite();
			moneybag.setImage("file:///D:/Dirt.png");
			double px = 350 * Math.random() + 50;
			double py = 350 * Math.random() + 50;
			moneybag.setPosition(px, py);
			moneybagList.add(moneybag);
		}

		LongValue lastNanoTime = new LongValue(System.nanoTime());

		IntValue score = new IntValue(0);

		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				// calculate time since last update.
				double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
				lastNanoTime.value = currentNanoTime;

				// game logic

				briefcase.setVelocity(0, 0);
				if (input.contains("LEFT"))
					briefcase.addVelocity(-70, 0);
				if (input.contains("RIGHT"))
					briefcase.addVelocity(70, 0);
				if (input.contains("UP"))
					briefcase.addVelocity(0, -70);
				if (input.contains("DOWN"))
					briefcase.addVelocity(0, 70);

				briefcase.update(elapsedTime);

				// collision detection

				Iterator<Sprite> moneybagIter = moneybagList.iterator();
				while (moneybagIter.hasNext()) {
					Sprite moneybag = moneybagIter.next();
					if (briefcase.intersects(moneybag)) {
						moneybagIter.remove();
						score.value++;
					}
				}

				// render

				gc.clearRect(0, 0, 512, 512);
				briefcase.render(gc);

				for (Sprite moneybag : moneybagList)
					moneybag.render(gc);

				String pointsText = "Cash: $" + (100 * score.value);
				gc.fillText(pointsText, 360, 36);
				gc.strokeText(pointsText, 360, 36);
			}
		}.start();

		theStage.show();
	}
}
