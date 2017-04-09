package application;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by User on 4/4/2560.
 */
public class Bullet extends Sprite{
    int playerType;
    int type;
    int direction;
    int damage;
    final static String []nameImage={"bullet_DOWN.png","bullet_RIGHT.png","bullet_LEFT.png","bullet_UP.png"};

    public Bullet() {
    }
    public Bullet(int playerType,int type, int direction, int damage, int speed) {
        this.playerType=playerType;
        this.type = type;
        this.direction = direction;
        this.damage = damage;

        if(direction==0) {
            this.setImage(nameImage[0]);
            this.setVelocity(0,speed);
        }else
        if(direction==1) {
            this.setImage(nameImage[1]);
            this.setVelocity(-speed,0);
        }else
        if(direction==2) {
            this.setImage(nameImage[2]);
            this.setVelocity(+speed,0);
        }else
        if(direction==3) {
            this.setImage(nameImage[3]);
            this.setVelocity(0,-speed);
        }


    }

    public int getType() {
        return type;
    }

    public int getPlayerType() {
        return playerType;
    }
}
