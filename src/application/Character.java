package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Character extends Pane {
	ImageView imageView;
	int count = 3;
	int columns = 4;
	int offsetX = 0;
	int offsetY = 0;
	int width = 90;
	int height = 90;

	//status of player
	int score = 0;
	int armor=100;
	int life=3;
	int speed=2;
	int bullet;
	int direction;

	//direction 0 down,1 left, 2 right, 3 up

	Rectangle removeRect = null;

	SpriteAnimation animation;

	public Character(ImageView imageView) {
		this.imageView = imageView;
		this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		animation = new SpriteAnimation(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width,
				height);
		getChildren().addAll(imageView);
	}

	public void moveX(int x) {
		boolean right = x > 0 ? true : false;
		for (int i = 0; i < Math.abs(x); i++) {
			if (right)
				this.setTranslateX(this.getTranslateX() + 2);
			else
				this.setTranslateX(this.getTranslateX() - 2);
		}
	}

	public void moveY(int y) {
		boolean down = y > 0 ? true : false;
		for (int i = 0; i < Math.abs(y); i++) {
			if (down)
				this.setTranslateY(this.getTranslateY() + 2);
			else
				this.setTranslateY(this.getTranslateY() - 2);
		}
	}
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.getTranslateX(),this.getTranslateY(), width, height);
	}

	public boolean intersects(Character s) {
		return s.getBoundary().intersects(this.getBoundary());
	}

	public int getDirection() {
		return direction;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getBullet() {
		return bullet;
	}

	public void setBullet(int bullet) {
		this.bullet = bullet;
	}
	public void shoot(){
		this.bullet--;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
}
