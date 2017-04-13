package application;

/**
 * Created by User on 7/4/2560.
 */
public class Block extends Sprite {
    private int type;
    private int durabillity;
    private int maxDurabillity;
    private int change=0;
    private String originFileName;
    public Block(){

    }

    public Block(int type,int x,int y) {
        String name="block";
        this.type=type;
        super.setPosition(x,y);
        //super.setImage(name);
        if(type==0){
            super.setImage(name+"0.png");
            this.maxDurabillity=8;
        }
        if(type==1){
            super.setImage(name+"1.png");
            this.maxDurabillity=15;
        }
        if(type==2){
            super.setImage(name+"2.png");
            this.maxDurabillity=11;
        }
        if(type==3){
            super.setImage(name+"3.png");
            this.maxDurabillity=22;
        }
        this.originFileName=this.getFileName();
        this.durabillity = maxDurabillity;
    }
    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public void setDurabillity(int durabillity) {
        this.durabillity = durabillity;
    }
    public int getDurabillity() {
        return durabillity;
    }
    public void hit(int damage){
        this.durabillity-=damage;
        if(change==0&&this.durabillity<this.maxDurabillity/2){
            this.setImage("2_"+this.getFileName());
            change=1;
        }
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public int getMaxDurabillity() {
        return maxDurabillity;
    }

    public int getType() {
        return type;
    }
}
