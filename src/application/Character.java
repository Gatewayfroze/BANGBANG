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
	int width = 64;
	int height = 64;
	int score = 0;
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
//			isBonuseEat();
		}
	}
	

	public void moveY(int y) {
		boolean down = y > 0 ? true : false;
		for (int i = 0; i < Math.abs(y); i++) {
			if (down)
				this.setTranslateY(this.getTranslateY() + 2);
			else
				this.setTranslateY(this.getTranslateY() - 2);
//			isBonuseEat();
		}
	}
	
	public int getOffsetY() {
		return offsetY;
	}
	public int getOffsetX() {
		return offsetX;
	}
//	public double getWidths() {
//		return width;
//	}
//	public double getHeights() {
//		return height;
//	}
//	


}
