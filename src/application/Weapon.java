package application;

/**
 * Created by User on 7/4/2560.
 */
public class Weapon extends Sprite {
    private int type;
    private double fireRate;
    private int damage;
    private int speedBullet;
    private int bullet;

    public Weapon() {
    }
    public Weapon(int type,String filename,int bullet){
        super.setImage(filename);
        this.type = type;
        this.bullet=bullet;
    }
    public Weapon(int type,String filename, double fireRate,int speedBullet, int damage) {
        super.setImage(filename);
        super.setFileName(filename);
        this.type = type;
        this.speedBullet=speedBullet;
        this.fireRate = fireRate;
        this.damage = damage;

    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
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

    public int getSpeedBullet() {
        return speedBullet;
    }

    public void setSpeedBullet(int speedBullet) {
        this.speedBullet = speedBullet;
    }

    public int getBullet() {
        return bullet;
    }
}
