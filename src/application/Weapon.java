package application;

/**
 * Created by User on 7/4/2560.
 */
public class Weapon extends Sprite {
    private int type;
    private double fireRate;
    private int damage;

    public Weapon() {
    }
    public Weapon(int type, double fireRate, int damage) {
        this.type = type;
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
}
