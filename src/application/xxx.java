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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;


public class xxx  {
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    public static ArrayList<Rectangle> bonuses = new ArrayList<>();
    Image        image = new Image("cowboy.png");// specify character image
    Image        map = new Image("BG1.png");
    Image        image2 =new Image("oldman.png");

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
    public Scene Game() {
        //setScene
        Image image3 = new Image("file:\\E:\\JAVA\\interface_playtime.png");// specify character image
        ImageView imageView3 = new ImageView(image3);// show image

        Group root = new Group();
        Scene scene=new Scene(root);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        //set BG
        scene.setFill(pattern);
        //setGame
        Canvas canvas = new Canvas(1548 , 871);

        root.getChildren().addAll(canvas,player,player2,imageView3);



        ArrayList<Block> blockList = new ArrayList<>();
        ArrayList<Bullet> bullet=new ArrayList<>();
        ArrayList<Weapon> weaponsList=new ArrayList<>();

        int []directionOffset={0,180,90,270};

        //set status player
        player.setTranslateY(300);
        player.setTranslateX(20);
        player.setBullet(10);
        player2.setTranslateX(500);
        player2.setTranslateY(500);
        player2.setBullet(10);


        //generate All block;
        int xx=100;
        for (int i = 0; i < 6; i++) {
            Block block = new Block();
            block.setImage("file:\\C:\\Users\\User\\IdeaProjects\\Game\\Dirt.png");
            block.setDurabillity(3);
            xx+=block.getHeight();
            int px =400;
            int py = xx;
            block.setPosition((double) px, (double) py);
            blockList.add(block);
        }
        xx=400;
        for (int i = 0; i < 6; i++) {
            Block block = new Block();
            block.setImage("file:\\C:\\Users\\User\\IdeaProjects\\Game\\Dirt.png");
            block.setDurabillity(3);
            xx+=block.getWidth();
            int px =xx;
            int py = 300;
            block.setPosition((double) px, (double) py);
            blockList.add(block);
        }
        //render obj
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (Sprite block : blockList)
            block.render(gc);
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

                if (isPressed(KeyCode.W) && collision(player, blockList, "UP")&&player.getTranslateY()>0) {
                    player.animation.play();
                    player.animation.setOffsetY(directionOffset[3]);
                    player.moveY(-2);
                    player.direction=3;
                } else if (isPressed(KeyCode.S) && collision(player, blockList, "DOWN")&&player.getTranslateY()+90<scene.getHeight()) {
                    player.animation.play();
                    player.animation.setOffsetY(directionOffset[0]);
                    player.moveY(2);
                    player.direction=0;
                } else if (isPressed(KeyCode.D) && collision(player, blockList, "RIGHT")&&player.getTranslateX()+90<scene.getWidth()) {
                    player.animation.play();
                    player.animation.setOffsetY(directionOffset[2]);
                    player.moveX(2);
                    player.direction=2;
                } else if (isPressed(KeyCode.A) && collision(player, blockList, "LEFT")&&player.getTranslateX()>0) {
                    player.animation.play();
                    player.animation.setOffsetY(directionOffset[1]);
                    player.moveX(-2);
                    player.direction=1;
                }else if (isPressed(KeyCode.F)&&Rate>=4/*&&player.getBullet()!=0*/) {
                    bullet.add(createBullet((player.getTranslateX()+40),player.getTranslateY()+40,player.direction,1));
                    player.shoot();
                    count=0;

                }else if (isPressed(KeyCode.R)&&RateAll%4==0/*&&player2.getBullet()!=0*/) {
                    Block newBlock=build(player);
                    if(checkRender(blockList,newBlock)) System.out.println("intersect");  else
                        blockList.add(newBlock);
                }
                else {
                    player.animation.stop();
                }
                //////////////////control player 2

                if (isPressed(KeyCode.UP) && collision(player2, blockList, "UP")&&player2.getTranslateY()>0) {
                    player2.animation.play();
                    player2.animation.setOffsetY(directionOffset[3]);
                    player2.moveY(-2);
                    player2.direction=3;
                } else if (isPressed(KeyCode.DOWN) && collision(player2, blockList, "DOWN")&&player2.getTranslateY()+90<scene.getHeight()) {
                    player2.animation.play();
                    player2.animation.setOffsetY(directionOffset[0]);
                    player2.moveY(2);
                    player2.direction=0;
                } else if (isPressed(KeyCode.RIGHT) && collision(player2, blockList, "RIGHT")&&player2.getTranslateX()+90<scene.getWidth()) {
                    player2.animation.play();
                    player2.animation.setOffsetY(directionOffset[2]);
                    player2.moveX(2);
                    player2.direction=2;
                } else if (isPressed(KeyCode.LEFT) && collision(player2, blockList, "LEFT")&&player2.getTranslateX()>0) {
                    player2.animation.play();
                    player2.animation.setOffsetY(directionOffset[1]);
                    player2.moveX(-2);
                    player2.direction=1;
                }else if (isPressed(KeyCode.SLASH)&&Rate2>=4/*&&player2.getBullet()!=0*/) {
                    bullet.add(createBullet((player2.getTranslateX()+40),player2.getTranslateY()+40,player2.direction,2));
                    count2=0;
                    player2.shoot();
                }else if (isPressed(KeyCode.CONTROL)&&RateAll%4==0/*&&player2.getBullet()!=0*/) {
                    Block newBlock=build(player2);
                    if(checkRender(blockList,newBlock)) System.out.println("intersect");  else
                        blockList.add(newBlock);
                }
                else {
                    player2.animation.stop();
                }

                //this is a random weapon

        /*  if(RateWeapon>10){
                    Weapon weapon=new Weapon(1,2,3);
                    weapon.setImage("weapon1.png");
                    int px,py;
                    do {
                        px = (int) (800 * Math.random()) + 10;
                        py = (int) (600 * Math.random()) + 10;
                        weapon.setPosition((double) px, (double) py);

                    }while (checkRender(blockList,weaponsList,weapon));
                    weaponsList.add(weapon);
                    countWeapon=0;

                }*/
                //if bullet was shoot
                if(bullet.size()!=0) {
                    for (int i = 0; i <bullet.size() ; i++) {
                        bullet.get(i).render(gc);
                        bullet.get(i).update(elapsedTime);
                        //bullet of player 1
                        if(bullet.get(i).getPositionX()>scene.getWidth()||bullet.get(i).getPositionX()<0||
                           bullet.get(i).getPositionY()<0||bullet.get(i).getPositionY()>scene.getHeight()){
                            bullet.remove(i);
                        }else
                        if(bullet.get(i).intersects(player2)&&bullet.get(i).getType()==1) {
                            bullet.remove(i);
                            System.out.println("1-------hit!!!");
                        }else
                        if(bullet.get(i).intersects(player)&&bullet.get(i).getType()==2) {
                            bullet.remove(i);
                            System.out.println("2-------hit!!!");
                        }else{
                            for (int j = 0; j <bullet.size() ; j++) {
                                if(bullet.get(i).intersects(bullet.get(j))&&bullet.get(i).type!=bullet.get(j).type) {
                                    if(i>j) {
                                        bullet.remove(j);
                                        bullet.remove(i-1);
                                    }else {bullet.remove(j);
                                    bullet.remove(i);}
                                    break;
                                }
                            }
                        }

                    }

                    detect(bullet,blockList );
                }
                gc.clearRect(0, 0, 1548,871);
                for (Weapon w : weaponsList )w.render( gc );
                for (Sprite b : bullet )b.render( gc );
                for (Sprite moneybag : blockList)moneybag.render(gc);






            }
        };
        timer.start();
        return scene;
    }
    public Bullet createBullet(double x,double y,int direction,int type){
        Bullet bullet = new Bullet(type,direction,1,3);
        bullet.setPosition(x,y);
        return bullet;
    }
    public Block build(Character player){
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
    public boolean collision(Character m, ArrayList<Block> t, String direct) {
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
    public void detect(ArrayList<Bullet> t,ArrayList<Block> m){
        for (int i = 0; i <t.size() ; i++) {
            for (int j = 0; j <m.size() ; j++) {
                if(t.get(i).intersects(m.get(j))){
                    t.remove(i);
                    m.get(j).hti(1);
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
    public boolean checkRender(ArrayList<Block> blockList,Block block){
        for (int j = 0; j <blockList.size() ; j++) {
            if(block.intersects(blockList.get(j))) return true;
        }
         return false;
    }

}