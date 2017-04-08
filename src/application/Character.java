package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Character extends Pane {
	ImageView imageView;
	int count = 3;
	int columns = 4;
	int offsetX = 0;
	int offsetY = 0;
	int width = 90;
	int height = 90;

	//status of player
	private int type;
	private int armor;
	private int life;
	private int hp;
	private int speed;
	//type Weapon 0 handGun,1 machineGun,2 sniper
	private int typeWeapon;
	private int speedBullet;
	private double fireRate;
	private int damage;
	ArrayList<Block> stackBlock;
	private int bullet;
	 int direction;
	//direction 0 down,1 left, 2 right, 3 up
	Rectangle removeRect = null;
	SpriteAnimation animation;

	public Character(ImageView imageView,int type, int armor, int life, int hp, int speed, int typeWeapon, int speedBullet, double fireRate, int damage, int bullet, int direction) {
		this.type = type;
		this.armor = armor;
		this.life = life;
		this.hp = hp;
		this.speed = speed;
		this.typeWeapon = typeWeapon;
		this.speedBullet = speedBullet;
		this.fireRate = fireRate;
		this.damage = damage;
		this.bullet = bullet;
		this.direction = direction;
		this.imageView = imageView;
		this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		animation = new SpriteAnimation(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width,
				height);
		getChildren().addAll(imageView);
	}

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
				this.setTranslateX(this.getTranslateX() + 0.5);
			else
				this.setTranslateX(this.getTranslateX() - 0.5);
		}
	}

	public void moveY(int y) {
		boolean down = y > 0 ? true : false;
		for (int i = 0; i < Math.abs(y); i++) {
			if (down)
				this.setTranslateY(this.getTranslateY() + 0.5);
			else
				this.setTranslateY(this.getTranslateY() - 0.5);
		}
	}
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.getTranslateX(),this.getTranslateY(), width, height);
	}

	public boolean intersects(Character s) {
		return s.getBoundary().intersects(this.getBoundary());

	}
	public boolean intersects(Weapon s) {
		return s.getBoundary().intersects(this.getBoundary());
	}

	public int getDirection() {
		return direction;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getTypeWeapon() {
		return typeWeapon;
	}

	public void setTypeWeapon(int typeWeapon) {
		this.typeWeapon = typeWeapon;
	}

	public int getSpeedBullet() {
		return speedBullet;
	}

	public void setSpeedBullet(int speedBullet) {
		this.speedBullet = speedBullet;
	}

	public double getFireRate() {
		return fireRate;
	}

	public void setFireRate(double fireRate) {
		this.fireRate = fireRate;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
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
	public void getMag(int bullet){
		this.bullet+=bullet;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	public void setPosition(int x,int y){
		super.setTranslateX(x);
		super.setTranslateY(y);
	}

}
