package application;

/**
 * Created by User on 7/4/2560.
 */
public class Block extends Sprite {
    private int type;
    private int durabillity;
    private int maxDurabillity;
    public Block(){

    }

    public Block(int type,int x,int y) {
        String name="block";
        this.type=type;
        super.setPosition(x,y);
        //super.setImage(name);
        if(type==0){
            super.setImage(name+"0.png");
            this.maxDurabillity=3;
        }
        if(type==1){
            super.setImage(name+"1.png");
            this.maxDurabillity=7;
        }
        if(type==2){
            super.setImage(name+"2.png");
            this.maxDurabillity=5;
        }
        if(type==3){
            super.setImage(name+"3.png");
            this.maxDurabillity=10;
        }
        this.durabillity = maxDurabillity;
    }

    public void setDurabillity(int durabillity) {
        this.durabillity = durabillity;
    }
    public int getDurabillity() {
        return durabillity;
    }
    public void hit(int damage){
        this.durabillity-=damage;
    }
    public int getMaxDurabillity() {
        return maxDurabillity;
    }

    public int getType() {
        return type;
    }
}
