package application;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by User on 4/4/2560.
 */
public class Bullet extends Sprite{
    int playerType;
    int direction;
    int damage;
    final static String []nameImage={"bullet_DOWN","bullet_RIGHT","bullet_LEFT","bullet_UP"};

    public Bullet() {
    }
    public Bullet(int playerType, int direction, int damage, int speed) {
        this.playerType=playerType;
        this.direction = direction;
        this.damage = damage;
        String pre="bullet/",sub[]={"",".png","2.png"};
        if(direction==0) {
            this.setImage(pre+nameImage[0]+sub[playerType]);
            this.setVelocity(0,speed);
        }else
        if(direction==1) {
            this.setImage(pre+nameImage[1]+sub[playerType]);
            this.setVelocity(-speed,0);
        }else
        if(direction==2) {
            this.setImage(pre+nameImage[2]+sub[playerType]);
            this.setVelocity(+speed,0);
        }else
        if(direction==3) {
            this.setImage(pre+nameImage[3]+sub[playerType]);
            this.setVelocity(0,-speed);
        }


    }


    public int getPlayerType() {
        return playerType;
    }
}
