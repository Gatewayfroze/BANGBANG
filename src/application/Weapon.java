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
    public Weapon(int type,String filename) {
        super.setImage(filename);
        super.setFileName(filename);
        this.type = type;
        switch (type){
            case 0:{
                this.fireRate=12;
                this.speedBullet=25;
                this.damage=100;
                break;
            }
            case 1:{
                this.fireRate=10;
                this.speedBullet=8;
                this.damage=3;
                break;
            }
            case 2:{
                this.fireRate=5;
                this.speedBullet=15;
                this.damage=5;
                break;
            }
            case 3:{
                this.fireRate=0.5;
                this.speedBullet=7;
                this.damage=1;
                break;
            }
        }


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
