package application;

/**
 * Created by User on 7/4/2560.
 */
public class Weapon extends Sprite {
    private String type;
    private double fireRate;


    public Weapon() {
    }

    public Weapon(String type, double fireRate) {
        this.type = type;
        this.fireRate = fireRate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double fireRate) {
        this.fireRate = fireRate;
    }
}
