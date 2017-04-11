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
	int width = 57;
	int height = 70;

	//status of player
	private int type;

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
	public Character(){}
	public Character(ImageView imageView,int type,int life, int hp, int speed, int typeWeapon, int speedBullet, double fireRate, int damage, int bullet, int direction) {
		this.type = type;

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
		this.stackBlock=new ArrayList<>();
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
		public void getMag(){
		int bullet=0;

		if(this.getTypeWeapon()==0){bullet=5;}
		if(this.getTypeWeapon()==1){bullet=12;}
		if(this.getTypeWeapon()==2) {bullet=10;}
		if(this.getTypeWeapon()==3){bullet=20;}
			this.bullet+=bullet;
	}
	public void getBlock(Block block){
			stackBlock.add(block);
	}
	public String getNameFirstBlock(){
		return this.stackBlock.get(0).getFileName();
	}
	public Block buildBlock(){
		Block block=this.stackBlock.get(0);
		return block;
	}
	public void removeBlock(){
		this.stackBlock.remove(0);
	}
	public int getNumOfBlock(){
		return this.stackBlock.size();
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public void setPosition(int x,int y){
		super.setTranslateX(x);
		super.setTranslateY(y);
	}
//special method
	public void shoot(){
		this.bullet--;
	}
	public void hit(int damage){this.hp-=damage;
	}
	public void getWeapon(Weapon weapon){
	this.setTypeWeapon(weapon.getType());
	this.setSpeedBullet(weapon.getSpeedBullet());
	this.setFireRate(weapon.getFireRate());
	this.setDamage(weapon.getDamage());
	// 1 machineGun 2 sniper

	if(weapon.getType()==0){this.setBullet(5);}else
	if(weapon.getType()==1){this.setBullet(21);}else
	if(weapon.getType()==2){this.setBullet(10);}else
	if(weapon.getType()==3){this.setBullet(30);}
	}

}
