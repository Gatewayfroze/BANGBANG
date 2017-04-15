package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
	private Image image;
	private double positionX;
	private double positionY;
	private double velocityX;
	private double velocityY;
	private double width;
	private double height;
	private String fileName;


	public Sprite() {
		positionX = 0;
		positionY = 0;
		velocityX = 0;
		velocityY = 0;
	}
	public Sprite(String filename,int x,int y) {
		this.fileName=filename;
		this.setImage(filename);

		this.setPosition(x,y);

	}

	public void setImage(Image i) {
		image = i;
		width = i.getWidth();
		height = i.getHeight();
	}

	public void setImage(String filename) {
		this.fileName=filename;
		Image i = new Image(filename);
		setImage(i);
	}
	public void setPosition(double x, double y) {
		positionX = x;
		positionY = y;
	}

	public void   setVelocity(double x, double y) {
		velocityX = x;
		velocityY = y;
	}
	public double getPositionX() {
		return positionX;
	}
	public double getPositionY() {
		return positionY;
	}
	public double getVelocityX() {return velocityX; }
	public double getVelocityY() {return velocityY;}
	public void   setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	public void   setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	public double getHeight() {
		return height;
	}
	public double getWidth() {
		return width;
	}
	public void   setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileName() {
		return fileName;
	}
	public void   update(double time) {
		positionX += velocityX * time;
		positionY += velocityY * time;
	}
	public void   render(GraphicsContext gc) {
		gc.drawImage(image, positionX, positionY);

	}
	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX, positionY, width, height);
	}
	public boolean intersects(Sprite s) {
		return s.getBoundary().intersects(this.getBoundary());
	}
	public boolean intersects(Character s) {
		return s.getBoundary().intersects(this.getBoundary());
	}
	public String toString() {
		return " Position: [" + positionX + "," + positionY + "]" + " Velocity: [" + velocityX + "," + velocityY + "]";
	}
}
