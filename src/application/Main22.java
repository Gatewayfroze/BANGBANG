package application;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main22 extends Application {
	private HashMap<KeyCode, Boolean> keys = new HashMap<>();

	Image image = new Image("cha.png");// specify
										// character
										// image
	ImageView imageView = new ImageView(image);// show image
	Character player = new Character(imageView);
	Image map = new Image("map.png");
	ImageView bg = new ImageView(map);
	StackPane pane = new StackPane(bg);
	Image image2 = new Image("cha2.png");// specify
											// character
											// image
	ImageView imageView2 = new ImageView(image2);// show image
	Character player2 = new Character(imageView2);

	ImagePattern pattern = new ImagePattern(map);

	public boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	@Override
	public void start(Stage primaryStage) {
		Group root = new Group();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		Canvas canvas = new Canvas(900, 600);

		root.getChildren().add(canvas);
		root.getChildren().addAll(player, player2);

		scene.setFill(pattern);

		player.setTranslateY(300);
		player.setTranslateX(20);
		player2.setTranslateX(800);
		player2.setTranslateY(300);

		ArrayList<Sprite> rockList = new ArrayList<Sprite>();

		for (int i = 0; i < 10; i++) {
			Sprite rock = new Sprite();
			rock.setImage("rock.png");
			int px = (int) (800 * Math.random()) + 10;
			int py = (int) (600 * Math.random()) + 10;
			rock.setPosition((double) px, (double) py);
			rockList.add(rock);
		}
		GraphicsContext gc = canvas.getGraphicsContext2D();
		for (Sprite rock : rockList)
			rock.render(gc);

		scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));

		AnimationTimer timer = new AnimationTimer() {
			ArrayList<Sprite> bullet = new ArrayList<Sprite>();
			double lastNanoTime = System.nanoTime();
			boolean bulletPress = false;
			int currentBX = 0;

			@Override
			public void handle(long now) {
				int bulletlimit = 5;

				int currentBy = 0;
				 bullet.add(createBullet(player.getTranslateX(),
				 player.getTranslateY()));

				double elapsedTime = (now - lastNanoTime) / 10000000.0;

				lastNanoTime = now;

				if (isPressed(KeyCode.UP) && collision(player, rockList, "UP")) {
					player.animation.play();
					player.animation.setOffsetY(192);
					player.moveY(-2);
				} else if (isPressed(KeyCode.DOWN) && collision(player, rockList, "DOWN")) {
					player.animation.play();
					player.animation.setOffsetY(0);
					player.moveY(2);
				} else if (isPressed(KeyCode.RIGHT) && collision(player, rockList, "RIGHT")) {
					player.animation.play();
					player.animation.setOffsetY(128);
					player.moveX(2);
				} else if (isPressed(KeyCode.LEFT) && collision(player, rockList, "LEFT")) {
					player.animation.play();
					player.animation.setOffsetY(64);
					player.moveX(-2);
				} else {
					player.animation.stop();
				}
				if (isPressed(KeyCode.SPACE) && bulletPress == false) {
					long start=System.currentTimeMillis();
					bullet.add(createBullet(player.getTranslateX(), player.getTranslateY()));
					bullet.get(0).setPosition(player.getTranslateX(), player.getTranslateY());
					currentBX = (int) bullet.get(0).getPositionX();
					System.out.println("current " + currentBX);
					// bullet.get(0).setVelocity(0, 0);
					
					long stop=System.currentTimeMillis();
					
					
					

				}

				if (bullet.size() > 0) {

					
					int a = (int) bullet.get(0).getPositionX();
					System.out.println(bullet.size());
					System.out.println("cc= " + currentBX);

					/*
					 * if (a - currentBX > 800) bulletPress = false;
					 */

					bullet.get(0).render(gc);
					if (bullet.get(0).getPositionX() < 800) {
						bullet.get(0).setVelocity(25, 0);
						//bulletPress=true;
						System.out.println(bullet.get(0).getPositionX());
						if(bullet.get(0).getPositionX() > 700){
							bulletPress=false;
						}
						

					} else
						bullet.get(0).setVelocity(0, 0);
					
					// bullet.remove(0);

					bullet.get(0).update(elapsedTime);
					if (bullet.get(0).intersects(rockList.get(0))) {
						rockList.remove(0);
						bullet.remove(0);
					}
					if (bullet.size() > 3) {
						bullet.remove(0);
					}
				}

				if (isPressed(KeyCode.W) && collision(player2, rockList, "UP")) {
					player2.animation.play();
					player2.animation.setOffsetY(192);
					player2.moveY(-2);

				} else if (isPressed(KeyCode.S) && collision(player2, rockList, "DOWN")) {
					player2.animation.play();
					player2.animation.setOffsetY(0);
					player2.moveY(2);

				} else if (isPressed(KeyCode.D) && collision(player2, rockList, "RIGHT")) {
					player2.animation.play();
					player2.animation.setOffsetY(128);
					player2.moveX(2);

				} else if (isPressed(KeyCode.A) && collision(player2, rockList, "LEFT")) {
					player2.animation.play();
					player2.animation.setOffsetY(64);
					player2.moveX(-2);
				} else {
					player2.animation.stop();
				}

				gc.clearRect(0, 0, 800, 600);

				for (Sprite b : bullet)
					b.render(gc);
				for (Sprite bullet : rockList)
					bullet.render(gc);

			}
		};
		timer.start();
		primaryStage.setTitle("Game");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void setBackground(Background background) {
		// TODO Auto-generated method stub

	}

	public Sprite createBullet(double x, double y) {
		Sprite bullet = new Sprite();
		bullet.setImage("icon_bullet.png");
		double px = x;
		double py = y;
		bullet.setPosition((double) px, (double) py);

		return bullet;
	}

	public boolean collision(Character m, ArrayList<Sprite> t, String direct) {
		if (direct.equals("LEFT")) {
			for (int i = 0; i < t.size(); i++) {
				if (m.getTranslateX() - 3 < t.get(i).getPositionX() + t.get(i).getWidth()
						&& m.getTranslateX() + m.getWidth() > t.get(i).getPositionX() + t.get(i).getWidth()
						&& m.getTranslateY() < t.get(i).getPositionY() + t.get(i).getHeight()
						&& m.getTranslateY() + m.getHeight() > t.get(i).getPositionY()) {
					return false;
				}

			}
			return true;

		}
		if (direct.equals("RIGHT")) {
			for (int i = 0; i < t.size(); i++) {
				if (m.getTranslateX() + m.getWidth() + 3 > t.get(i).getPositionX()
						&& m.getTranslateX() < t.get(i).getPositionX()
						&& m.getTranslateY() < t.get(i).getPositionY() + t.get(i).getHeight()
						&& m.getTranslateY() + m.getHeight() > t.get(i).getPositionY()) {
					return false;
				}

			}
			return true;

		} else if (direct.equals("UP")) {
			for (int i = 0; i < t.size(); i++) {
				if (m.getTranslateY() < t.get(i).getPositionY() + t.get(i).getHeight() + 5
						&& m.getTranslateY() + m.getHeight() > t.get(i).getPositionY() + t.get(i).getHeight()
						&& m.getTranslateX() < t.get(i).getPositionX() + t.get(i).getWidth()
						&& m.getTranslateX() + m.getWidth() > t.get(i).getPositionX()) {
					return false;
				}

			}
			return true;

		} else if (direct.equals("DOWN")) {
			for (int i = 0; i < t.size(); i++) {
				if (m.getTranslateY() + m.getHeight() + 3 > t.get(i).getPositionY()
						&& m.getTranslateY() < t.get(i).getPositionY()
						&& m.getTranslateX() < t.get(i).getPositionX() + t.get(i).getWidth()
						&& m.getTranslateX() + m.getWidth() > t.get(i).getPositionX()) {
					return false;
				}

			}
			return true;

		}

		return true;

	}

	public static void main(String[] args) {
		launch(args);
	}

}