package application;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class xxx  {
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    public static ArrayList<Rectangle> bonuses = new ArrayList<>();
    Image        image = new Image("scout_fix.png");// specify character image
    Image        map = new Image("BG1.png");
    Image        image2 =new Image("oldchar_fix2.png");

    ImageView    imageView = new ImageView(image);// show image
    ImageView    imageView2= new ImageView(image2);
    Character    player = new Character(imageView);
    Character    player2= new Character(imageView2);
    ImagePattern pattern = new ImagePattern(map);

    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
    private Stage stage;

    @FXML
    public Button btnBeginTargeting;
    @FXML

    private Main main=new Main();

    @FXML
    public void buttonClicked(ActionEvent event) throws Exception {

        Stage appStage;
        Parent root2;
//        if (event.getSource() == btnBeginTargeting) {
//            appStage = (Stage) btnBeginTargeting.getScene().getWindow();
//            root2 = FXMLLoader.load(getClass().getResource("Test.fxml"));
//            Scene scene = new Scene(root2);
//            appStage.setScene(scene);
//            appStage.show();
//        }

        Scene scene = Game();
        appStage = (Stage) btnBeginTargeting.getScene().getWindow();
        appStage.setScene(scene);

    }
    @FXML
    private AnchorPane rootPane;




    @FXML
    public void loadHowto(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("howto.fxml"));
        rootPane.getChildren().setAll(pane);

    }
    @FXML
    public void loadCredit(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("credit.fxml"));
        rootPane.getChildren().setAll(pane);

    }
    public Scene Game() {
        //setScene

        ImageView    imageView        = new ImageView(image);// show image
        ImageView    imageView2       = new ImageView(image2);
        Sprite       weaponInterFace  = new Sprite("inWeapon.png",0,300);
        Sprite       weaponInterFace2 = new Sprite("inWeapon.png",1425,390);
        Character    player           = new Character(imageView,1,100,3,25,10,
                                            0,3,2,2,10,1);
        Character    player2          = new Character(imageView2,2,100,3,25,10,
                                            0,3,2,2,10,1);
        Weapon       defaultWeapon    = new Weapon(0,"inweapon.png",2,3,2);
        Image        image3           = new Image("interface_new.png");// specify character image
        ImageView    imageView3       = new ImageView(image3);// show image
        Group        root             = new Group();
        Scene        scene            = new Scene(root);

        scene.setOnKeyPressed (event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        //set BG
        scene.setFill(pattern);
        //setGame
        Canvas canvas = new Canvas(1280 , 720);
        Canvas layout = new Canvas(1280 , 720);

        root.getChildren().addAll(canvas,player,player2,imageView3,layout);

        ArrayList<Block> blockList = new ArrayList<>();
        ArrayList<Bullet> bullet=new ArrayList<>();
        ArrayList<Weapon> weaponsList=new ArrayList<>();
        ArrayList<Sprite> viewLife=new ArrayList<>();
        ArrayList<Sprite> viewLife2=new ArrayList<>();
        int x=375;
        for (int i = 0; i <player.getLife() ; i++) {
            viewLife.add(new Sprite("heart.png",x,65));
            x+=50;
        }
        x=1020;
        for (int i = 0; i <player2.getLife() ; i++) {
            viewLife2.add(new Sprite("heart.png",x,770));
            x+=50;
        }

        int []directionOffset={0,140,70,210};

        //set status player
        player.setPosition(105,600);
        player2.setPosition(1200,80);


        //render obj
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GraphicsContext ly = layout.getGraphicsContext2D();

        //Animation
        AnimationTimer timer = new AnimationTimer() {
            double lastNanoTime = System.nanoTime() ;
            int count=0,count2=0,countWeapon=0,countAll=0;

            @Override
            public void handle(long now) {
                double elapsedTime = (now - lastNanoTime) / 10000000.0;
                lastNanoTime = now;
                count++;  count2++; countWeapon++; countAll++;
                double Rate=count/10.0,Rate2=count2/10.0,RateWeapon=countWeapon/10.0,RateAll=countAll/10.0;

                if (isPressed(KeyCode.W) && collision(player, blockList, "UP")&&player.getTranslateY()>63) {
                    player.animation.play();
                    player.animation.setOffsetY(directionOffset[3]);
                    player.moveY(-player.getSpeed());
                    player.direction=3;
                } else if (isPressed(KeyCode.S) && collision(player, blockList, "DOWN")&&player.getTranslateY()+70<scene.getHeight()-63) {
                    player.animation.play();
                    player.animation.setOffsetY(directionOffset[0]);
                    player.moveY(player.getSpeed());
                    player.direction=0;
                } else if (isPressed(KeyCode.D) && collision(player, blockList, "RIGHT")&&player.getTranslateX()+57<scene.getWidth()-80) {
                    player.animation.play();
                    player.animation.setOffsetY(directionOffset[2]);
                    player.moveX(player.getSpeed());
                    player.direction=2;
                } else if (isPressed(KeyCode.A) && collision(player, blockList, "LEFT")&&player.getTranslateX()>63) {
                    player.animation.play();
                    player.animation.setOffsetY(directionOffset[1]);
                    player.moveX(-player.getSpeed());
                    player.direction=1;
                }else if (isPressed(KeyCode.F)&&Rate>=player.getFireRate()&&player.getBullet()!=0) {
                    bullet.add(createBullet((player.getTranslateX()+40),player.getTranslateY()+40,player.direction,player));
                    player.shoot();
                    count=0;

                }else if (isPressed(KeyCode.R)&&RateAll%4==0) {
                    Block newBlock=build(player);
                    if(checkRender(blockList,weaponsList,newBlock)) System.out.println("intersect");  else
                        blockList.add(newBlock);
                }
                else {
                    player.animation.stop();
                }
                //////////////////control player 2

                if (isPressed(KeyCode.UP) && collision(player2, blockList, "UP")&&player2.getTranslateY()>63) {
                    player2.animation.play();
                    player2.animation.setOffsetY(directionOffset[3]);
                    player2.moveY(-player2.getSpeed());
                    player2.direction=3;
                } else if (isPressed(KeyCode.DOWN) && collision(player2, blockList, "DOWN")&&player2.getTranslateY()+70<scene.getHeight()-63) {
                    player2.animation.play();
                    player2.animation.setOffsetY(directionOffset[0]);
                    player2.moveY(player2.getSpeed());
                    player2.direction=0;
                } else if (isPressed(KeyCode.RIGHT) && collision(player2, blockList, "RIGHT")&&player2.getTranslateX()+57   <scene.getWidth()-80) {
                    player2.animation.play();
                    player2.animation.setOffsetY(directionOffset[2]);
                    player2.moveX(player2.getSpeed());
                    player2.direction=2;
                } else if (isPressed(KeyCode.LEFT) && collision(player2, blockList, "LEFT")&&player2.getTranslateX()>63) {
                    player2.animation.play();
                    player2.animation.setOffsetY(directionOffset[1]);
                    player2.moveX(-player2.getSpeed());
                    player2.direction=1;
                }else if (isPressed(KeyCode.SLASH)&&Rate2>=player2.getFireRate()&&player2.getBullet()!=0) {
                    bullet.add(createBullet((player2.getTranslateX()+40),player2.getTranslateY()+40,player2.direction,player2));
                    count2=0;
                    player2.shoot();
                }else if (isPressed(KeyCode.CONTROL)&&RateAll%4==0) {
                    Block newBlock=build(player2);
                    if(checkRender(blockList,weaponsList,newBlock)) System.out.println("intersect");  else
                        blockList.add(newBlock);
                }
                else {
                    player2.animation.stop();
                }

                //this is a random weapon

                 if(RateWeapon>50){
                    Weapon weapon=new Weapon(0,"mag.png",10);
                    int px,py;
                    do {
                        weapon=createWeapon();
                    }while (checkRender(blockList,weaponsList,weapon));
                    weaponsList.add(weapon);
                    countWeapon=0;
                }
                //get Magazine
                if(weaponsList.size()!=0) {
                    for (int i = 0; i <weaponsList.size(); i++) {
                        if (weaponsList.get(i).getType()==0){
                            if (player.intersects(weaponsList.get(i))) {
                                player.getMag();
                                weaponsList.remove(i);
                            } else
                            if (player2.intersects(weaponsList.get(i))) {
                                player2.getMag();
                                weaponsList.remove(i);
                            }
                        }else
                        if (weaponsList.get(i).getType()!=0){
                            if (player.intersects(weaponsList.get(i))) {
                                player.getWeapon(weaponsList.get(i));
                                ly.clearRect(8,360,120,120);
                                weaponInterFace.setImage("in"+weaponsList.get(i).getFileName());
                                System.out.println(weaponsList.get(i).getFileName());
                                weaponsList.remove(i);
                            } else
                            if (player2.intersects(weaponsList.get(i))) {
                                player2.getWeapon(weaponsList.get(i));
                                weaponInterFace2.setImage("in"+weaponsList.get(i).getFileName());
                                ly.clearRect(1425,390,120,120);
                                weaponsList.remove(i);
                            }
                        }
                    }
                }
                //if bullet was shoot
                if(bullet.size()!=0) {
                    for (int i = 0; i <bullet.size() ; i++) {
                        bullet.get(i).render(gc);
                        bullet.get(i).update(elapsedTime);
                        //bullet out of border
                        if(bullet.get(i).getPositionX()>scene.getWidth()||bullet.get(i).getPositionX()<0||
                           bullet.get(i).getPositionY()<0||bullet.get(i).getPositionY()>scene.getHeight()){
                            bullet.remove(i);
                        }else
                        //bullet hitplayer2
                        if(bullet.get(i).intersects(player2)&&bullet.get(i).getPlayerType()==1) {
                            player2.hit(bullet.get(i).damage);
                            bullet.remove(i);
                            System.out.println("HP Player2 "+player2.getHp());
                        }else
                        //bullet hitplayer1
                        if(bullet.get(i).intersects(player)&&bullet.get(i).getPlayerType()==2) {
                            player.hit(bullet.get(i).damage);
                            bullet.remove(i);
                            System.out.println("HP Player1 "+player.getHp());
                        }else{
                        //bullet hit bullet
                            for (int j = 0; j <bullet.size() ; j++) {
                                if(bullet.get(i).intersects(bullet.get(j))&&bullet.get(i).getPlayerType()!=bullet.get(j).getPlayerType()) {
                                    int oldD=bullet.get(i).damage;
                                    bullet.get(i).damage-=bullet.get(j).damage;
                                    bullet.get(j).damage-=oldD;
                                    if(bullet.get(i).damage<=0&&bullet.get(j).damage<=0) {
                                        if (i > j) {
                                            bullet.remove(i);
                                            bullet.remove(j);

                                        } else {
                                            bullet.remove(j);
                                            bullet.remove(i);
                                        }
                                    }else
                                    if(bullet.get(i).damage<=0)bullet.remove(i); else
                                    if(bullet.get(j).damage<=0)bullet.remove(j);
                                    break;
                                }
                            }
                        }
                    }

                    detect(bullet,blockList);
                }
                //default weapon
                if(player.getBullet()<=0&&player.getTypeWeapon()!=0){
                    player.getWeapon(defaultWeapon);
                    ly.clearRect(8,360,120,120);
                    weaponInterFace.setImage("inweapon.png");
                 }
                if(player2.getBullet()<=0&&player2.getTypeWeapon()!=0){
                    player2.getWeapon(defaultWeapon);
                    ly.clearRect(1425,390,120,120);
                    weaponInterFace2.setImage("inweapon.png");

                }
                //check HP
                if(player.getHp()<=0){player.setLife(player.getLife()-1);
                    System.out.println("life1"+player.getLife());
                    if(player.getLife()>=0)viewLife.remove(viewLife.size()-1);
                    player.setHp(25);
                 }
                if(player2.getHp()<=0){
                    player2.setLife(player2.getLife()-1);
                    System.out.println("life2"+player2.getLife());
                    if(player2.getLife()>=0)viewLife2.remove(viewLife2.size()-1);
                    player2.setHp(25);
                }
                gc.clearRect(0, 0, 1280,720);
                ly.clearRect(0, 0, 1280,720);
                //render All
                for (Sprite com : viewLife )com.render( ly );
                for (Sprite com : viewLife2 )com.render( ly );
                for (Weapon w : weaponsList )w.render( gc );
                for (Bullet b : bullet )b.render( gc );
                for (Block moneybag : blockList)moneybag.render(gc);
                weaponInterFace.render(ly);
                weaponInterFace2.render(ly);

                //show status
                Font theFont = Font.font( "Arial Narrow", FontWeight.BOLD, 30 );
                ly.setFont( theFont );
                ly.setStroke(Color.BLACK);
                ly.setFill(Color.WHITE);
                String hp1 = "HP Player1: " + (player.getHp());
                String hp2 = (player2.getHp())+" :Player2 HP ";
                String bullet=""+player.getBullet(),bullet2=""+player2.getBullet();
                ly.fillText( bullet, 130, 460 );
                ly.fillText(bullet2,1380,420);
                ly.fillText( hp2, 950, 840 );
                ly.fillText( hp1, 360, 36 );

            }
        };
        timer.start();
        return scene;
    }
    public Bullet  createBullet(double x,double y,int direction,Character player){
        Bullet bullet = new Bullet(player.getType(),1,direction,player.getDamage(),player.getSpeedBullet());
        bullet.setPosition(x,y);
        return bullet;
    }
    public Weapon  createWeapon(){
        Weapon weapon=new Weapon();
        //random 0-2 | 0=bullet ,1=machineGun,2= sniper
        int type=(int)(3*Math.random());
        int px,py;
        px = (int) (1200 * Math.random()) + 100;
        py = (int) (650 * Math.random()) + 100;
        switch (type){
            case 0:{
                weapon=new Weapon(0,"mag.png",10);
                break;
            }
            case 1:{
                weapon=new Weapon(1,"weapon1.png",0.5,3,2);
                break;
            }
            case 2:{
                weapon=new Weapon(2,"weapon2.png",7,10,10);
                break;
            }
            case 3:{
                break;
            }

        }
        weapon.setPosition((double) px, (double) py);
        System.out.println("weapon rand "+type);
        return weapon;
    }
    public Block   build(Character player){
        int bx=0,by=0;
        Block block =new Block();

            if (player.getDirection() == 0) {
                //DOWN
                by = (int) player.getTranslateY() + 90;
                bx = (int) player.getTranslateX();
            }
            if (player.getDirection() == 3) {
                //UP
                by = (int) player.getTranslateY() - 100;
                bx = (int) player.getTranslateX();
            }
            if (player.getDirection() == 1) {
                //LEFT
                bx = (int) player.getTranslateX() - 100;
                by = (int) player.getTranslateY();
            }
            if (player.getDirection() == 2) {
                //RIGHT
                bx = (int) player.getTranslateX() + 90;
                by = (int) player.getTranslateY();
            }

            block=new Block(bx,by,3,"block.png");

            return block;
    }
    public boolean collision  (Character m,ArrayList<Block> t, String direct) {
        if (direct.equals("LEFT")) {
            for (int i = 0; i < t.size(); i++) {
                if (m.getTranslateX() - 3.5 < t.get(i).getPositionX() + t.get(i).getWidth()
                        && m.getTranslateX() + m.getWidth() > t.get(i).getPositionX() + t.get(i).getWidth()
                        && m.getTranslateY() < t.get(i).getPositionY() + t.get(i).getHeight()
                        && m.getTranslateY() + m.getHeight() > t.get(i).getPositionY()) {
                    return false;
                }

            }
            return true;

        }
        if (direct.equals("RIGHT")) {
            for (int i = 0; i < t.size(); i++) {
                if (m.getTranslateX() + m.getWidth() + 3.1 > t.get(i).getPositionX()
                        && m.getTranslateX() < t.get(i).getPositionX()
                        && m.getTranslateY() < t.get(i).getPositionY() + t.get(i).getHeight()
                        && m.getTranslateY() + m.getHeight() > t.get(i).getPositionY()) {
                    return false;
                }

            }
            return true;

        } else if (direct.equals("UP")) {
            for (int i = 0; i < t.size(); i++) {
                if (m.getTranslateY() < t.get(i).getPositionY() + t.get(i).getHeight() + 5.1
                        && m.getTranslateY() + m.getHeight() > t.get(i).getPositionY() + t.get(i).getHeight()
                        && m.getTranslateX() < t.get(i).getPositionX() + t.get(i).getWidth()
                        && m.getTranslateX() + m.getWidth() > t.get(i).getPositionX()) {
                    return false;
                }

            }
            return true;

        } else if (direct.equals("DOWN")) {
            for (int i = 0; i < t.size(); i++) {
                if (m.getTranslateY() + m.getHeight() + 3.2 > t.get(i).getPositionY()
                        && m.getTranslateY() < t.get(i).getPositionY()
                        && m.getTranslateX() < t.get(i).getPositionX() + t.get(i).getWidth()
                        && m.getTranslateX() + m.getWidth() > t.get(i).getPositionX()) {
                    return false;
                }
            }
            return true;

        }

        return true;

    }
    public void    detect     (ArrayList<Bullet> t,ArrayList<Block> m){
        for (int i = 0; i <t.size() ; i++) {
            for (int j = 0; j <m.size() ; j++) {
                if(t.get(i).intersects(m.get(j))){
                    m.get(j).hit(t.get(i).damage);
                    t.remove(i);
                    if(m.get(j).getDurabillity()<=0)
                    m.remove(j);
                    break;
                }
            }
        }
    }
    public boolean checkRender(ArrayList<Block> block,ArrayList<Weapon> weaponslist,Weapon weapon){
        for (int j = 0; j <block.size() ; j++) {
            if(weapon.intersects(block.get(j))) return true;
        }
        for (int j = 0; j <weaponslist.size() ; j++) {
            if(weapon.intersects(weaponslist.get(j))) return true;
        }

        return false;
    }
    public boolean checkRender(ArrayList<Block> blockList,ArrayList<Weapon> weaponArrayList,Block block){
        for (int j = 0; j <blockList.size() ; j++) {
            if(block.intersects(blockList.get(j))) return true;
        }
        for (int j = 0; j <weaponArrayList.size() ; j++) {
            if(block.intersects(weaponArrayList.get(j))) return true;
        }
         return false;
    }
}