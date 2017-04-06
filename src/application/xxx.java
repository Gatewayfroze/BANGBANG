package application;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class xxx  {
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    public static ArrayList<Rectangle> bonuses = new ArrayList<>();
    Image image = new Image("file:\\E:\\JAVA\\cowboy.png");// specify character image
    Image map = new Image("file:\\C:\\Users\\User\\IdeaProjects\\Game\\map.png");
    ImageView imageView = new ImageView(image);// show image
    Character player = new Character(imageView);
    ImagePattern pattern = new ImagePattern(map);

    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
    private Stage stage;

    @FXML
    public Button btnBeginTargeting;
    @FXML
    private Button btnExit;
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
        System.out.println("sdfdsf");


    }
    public Scene Game() {
        //setScene
        Image image3 = new Image("file:\\E:\\JAVA\\interface_playtime.png");// specify character image
        ImageView imageView3 = new ImageView(image3);// show image

        Group root = new Group();
        Scene scene=new Scene(root);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        scene.setFill(pattern);
        //setGame
        Canvas canvas = new Canvas(1280, 720);
        root.getChildren().add(canvas);
        root.getChildren().addAll(player);
        root.getChildren().addAll(imageView3);

        player.setTranslateY(300);
        player.setTranslateX(20);
        ArrayList<Block> blockList = new ArrayList<>();
        ArrayList<Sprite> bullet=new ArrayList<>();

        int []directionOffset={0,180,90,270};
        //generate All block;
        for (int i = 0; i < 6; i++) {
            Block block = new Block();
            block.setImage("file:\\C:\\Users\\User\\IdeaProjects\\Game\\Dirt.png");
            block.setDurabillity(3);
            int px = (int) (800 * Math.random()) + 10;
            int py = (int) (600 * Math.random()) + 10;
            block.setPosition((double) px, (double) py);
            blockList.add(block);
        }
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //render obj
        for (Sprite block : blockList)
            block.render(gc);

        AnimationTimer timer = new AnimationTimer() {
            double lastNanoTime = System.nanoTime() ;
            int count=0;
            @Override
            public void handle(long now) {
                double elapsedTime = (now - lastNanoTime) / 10000000.0;
                lastNanoTime = now;
                count++;
                double Rate=count/10.0;

                if (isPressed(KeyCode.UP) && collision(player, blockList, "UP")) {
                    player.animation.play();
                    player.animation.setOffsetY(directionOffset[3]);
                    player.moveY(-2);
                    player.direction=3;
                } else if (isPressed(KeyCode.DOWN) && collision(player, blockList, "DOWN")) {
                    player.animation.play();
                    player.animation.setOffsetY(directionOffset[0]);
                    player.moveY(2);
                    player.direction=0;
                } else if (isPressed(KeyCode.RIGHT) && collision(player, blockList, "RIGHT")&&player.getTranslateX()<800) {
                    player.animation.play();
                    player.animation.setOffsetY(directionOffset[2]);
                    player.moveX(2);
                    player.direction=2;
                } else if (isPressed(KeyCode.LEFT) && collision(player, blockList, "LEFT")) {
                    player.animation.play();
                    player.animation.setOffsetY(directionOffset[1]);
                    player.moveX(-2);
                    player.direction=1;
                }else if (isPressed(KeyCode.SLASH)&&Rate>=1) {
                    bullet.add(createBullet((player.getTranslateX()+40),player.getTranslateY()+40,player.direction));
                    count=0;
                }
                else {
                    player.animation.stop();
                }
                //if bullet was shoot
                if(bullet.size()!=0) {
                    for (int i = 0; i <bullet.size() ; i++) {
                        bullet.get(i).render(gc);
                        bullet.get(i).update(elapsedTime);
                    }
                    detect(bullet,blockList );
                }
                gc.clearRect(0, 0, 1280,720);
                for (Sprite b : bullet )b.render( gc );
                for (Sprite moneybag : blockList)moneybag.render(gc);
                System.out.println(blockList.size());





            }
        };
        timer.start();
        return scene;
    }
    public Sprite createBullet(double x,double y,int direction){
        Sprite bullet = new Sprite();
        if(direction==0) {
            bullet.setImage("file:\\E:\\JAVA\\bullet_DOWN.png");

            bullet.setVelocity(0,3);
        }else
        if(direction==1) {
            bullet.setImage("file:\\E:\\JAVA\\bullet_RIGHT.png");

            bullet.setVelocity(-3,0);
        }else
        if(direction==2) {
            bullet.setImage("file:\\E:\\JAVA\\bullet_LEFT.png");
            bullet.setVelocity(+3,0);
        }else
        if(direction==3) {
            bullet.setImage("file:\\E:\\JAVA\\bullet_UP.png");
            bullet.setVelocity(0,-3);
        }
        bullet.setPosition(x,y);
        return bullet;
    }
    public boolean collision(Character m, ArrayList<Block> t, String direct) {
        if (direct.equals("LEFT")) {
            for (int i = 0; i < t.size(); i++) {
                if (m.getTranslateX() - 3 < t.get(i).getPositionX() + t.get(i).getWidth()
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
                if (m.getTranslateX() + m.getWidth() + 3 > t.get(i).getPositionX()
                        && m.getTranslateX() < t.get(i).getPositionX()
                        && m.getTranslateY() < t.get(i).getPositionY() + t.get(i).getHeight()
                        && m.getTranslateY() + m.getHeight() > t.get(i).getPositionY()) {
                    return false;
                }

            }
            return true;

        } else if (direct.equals("UP")) {
            for (int i = 0; i < t.size(); i++) {
                if (m.getTranslateY() < t.get(i).getPositionY() + t.get(i).getHeight() + 5
                        && m.getTranslateY() + m.getHeight() > t.get(i).getPositionY() + t.get(i).getHeight()
                        && m.getTranslateX() < t.get(i).getPositionX() + t.get(i).getWidth()
                        && m.getTranslateX() + m.getWidth() > t.get(i).getPositionX()) {
                    return false;
                }

            }
            return true;

        } else if (direct.equals("DOWN")) {
            for (int i = 0; i < t.size(); i++) {
                if (m.getTranslateY() + m.getHeight() + 3 > t.get(i).getPositionY()
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
    public void detect(ArrayList<Sprite> t,ArrayList<Block> m){
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

}