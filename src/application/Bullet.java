package application;

/**
 * Created by User on 4/4/2560.
 */
public class Bullet extends Sprite{
    int type;
    int direction;
    int damage;

    public Bullet() {
    }
    public Bullet(int type, int direction, int damage, int speed) {
        this.type = type;
        this.direction = direction;
        this.damage = damage;
        if(direction==0) {
            this.setImage("bullet_DOWN.png");
            this.setVelocity(0,speed);
        }else
        if(direction==1) {
            this.setImage("file:\\E:\\JAVA\\bullet_RIGHT.png");
            this.setVelocity(-speed,0);
        }else
        if(direction==2) {
            this.setImage("file:\\E:\\JAVA\\bullet_LEFT.png");
            this.setVelocity(+speed,0);
        }else
        if(direction==3) {
            this.setImage("file:\\E:\\JAVA\\bullet_UP.png");
            this.setVelocity(0,-speed);
        }


    }
    public int getType() {
        return type;
    }
}
