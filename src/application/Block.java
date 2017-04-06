package application;

/**
 * Created by User on 7/4/2560.
 */
public class Block extends Sprite {
    private int durabillity;
    public Block(){

    }

    public void setDurabillity(int durabillity) {
        this.durabillity = durabillity;
    }
    public void hti(int damage){
        this.durabillity-=damage;
    }
    public int getDurabillity() {
        return durabillity;
    }
}
