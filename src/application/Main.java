package application;

import com.sun.org.apache.regexp.internal.RE;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
public class Main extends Application {
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    public static ArrayList<Rectangle> bonuses = new ArrayList<>();



    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    public Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {

        this.stage=primaryStage;
        stage.setScene(menu());
        stage.setTitle("Game");
        stage.show();
    }
    public Scene menu() throws Exception{
        Media hit = new Media(Paths.get("src/sfx/bangbang.wav").toUri().toString());
        AudioClip mediaPlayer = new AudioClip(hit.getSource());
        mediaPlayer.play();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene=new Scene(root);
        return scene;
    }
    public void playSoundWeapon(int type , double volume){
        String [] typegun = {"src/sfx/sniper.wav","src/sfx/shotgun.wav","src/sfx/handgun.wav","src/sfx/machinegun.wav","src/sfx/reload.wav"} ;
        Media hit = new Media(Paths.get(typegun[type]).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(hit.getSource());
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();

    }
    public void playSoundCreateblock(int type , double volume){
        String [] blocktype = {"src/sfx/ice.wav","src/sfx/grass.wav","src/sfx/sand2.mp3","src/sfx/stone.wav","src/sfx/error.wav"} ;
        Media hit = new Media(Paths.get(blocktype[type]).toUri().toString());
        AudioClip mediaPlayer2 = new AudioClip(hit.getSource());
        mediaPlayer2.setVolume(volume);
        mediaPlayer2.play();

    }
    public Scene stageSele(int Sele1,int Sele2){
        Media hit = new Media(Paths.get("src/sfx/stage.wav").toUri().toString());
        AudioClip mediaPlayer = new AudioClip(hit.getSource());
        mediaPlayer.play();
        Group root = new Group();
        Scene scene=new Scene(root);
        Canvas canvas = new Canvas( 1280, 720 );
        root.getChildren().addAll(canvas);
        Button []mapDis =new Button[4];
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ArrayList<Sprite> imageMap =new ArrayList<>();

        scene.setFill(new ImagePattern (new Image("stage.png")));
        int mx=330,my=233,count=0;
        for (int i = 0; i <2 ; i++) {
            for (int j = 0; j <2 ; j++) {
                mapDis[count]=new Button();
                mapDis[count].relocate(mx,my);
                imageMap.add(new Sprite("map"+count+".png",mx,my));
                mapDis[count].setPrefSize(296,167);
                mapDis[count].setOpacity(0);
                root.getChildren().add(mapDis[count]);
                mx+=322;
                count++;
            }
            mx=330;
            my+=193;
        }
        Button enter =new Button();
        enter.setStyle("-fx-background-image: url(\"/startbt.png\");-fx-cursor:hand;-fx-pref-height: 96px;\n" +
                                "    -fx-pref-width: 202px;");


        enter.relocate(1280/3+120,620);
        root.getChildren().addAll(enter);


        for (Sprite e:imageMap)e.render(gc);
        AnimationTimer timer = new AnimationTimer() {
            double lastNanoTime = System.nanoTime() ;
            int map=0;
            @Override
            public void handle(long now) {

//
                mapDis[0].addEventHandler(MouseEvent.MOUSE_PRESSED,new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent e) {
                        mapDis[0].setStyle("-fx-opacity: 0.5;-fx-background-color:green;");
                        mapDis[1].setStyle(null);
                        mapDis[2].setStyle(null);
                        mapDis[3].setStyle(null);


                    }});

                mapDis[1].addEventHandler(MouseEvent.MOUSE_PRESSED,new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent e) {
                        mapDis[1].setStyle("-fx-opacity: 0.5;-fx-background-color:green");
                        mapDis[0].setStyle(null);
                        mapDis[2].setStyle(null);
                        mapDis[3].setStyle(null);

                    }});

                mapDis[2].addEventHandler(MouseEvent.MOUSE_PRESSED,new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent e) {
                        mapDis[2].setStyle("-fx-opacity: 0.5;-fx-background-color:green");
                        mapDis[1].setStyle(null);
                        mapDis[0].setStyle(null);
                        mapDis[3].setStyle(null);

                    }});

                mapDis[3].addEventHandler(MouseEvent.MOUSE_PRESSED,new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent e) {
                        mapDis[3].setStyle("-fx-opacity: 0.5;-fx-background-color:green");
                        mapDis[1].setStyle(null);
                        mapDis[2].setStyle(null);
                        mapDis[0].setStyle(null);

                    }});
                lastNanoTime = now;
                mapDis [0].setOnAction(e -> map=0 );
                mapDis [1].setOnAction(e -> map=1 );
                mapDis [2].setOnAction(e -> map=2 );
                mapDis [3].setOnAction(e -> map=3 );
                enter.setOnAction(e -> {
                    mediaPlayer.stop();
                    //
                    stage.setScene(Game(Sele1,Sele2,map));
                    stage.show();
                                       });
                               }
        };
        timer.start();


        return scene;

    }
    public Scene gameResult(int Result,int type){

        Media hit = new Media(Paths.get("src/sfx/result.mp3").toUri().toString());
        AudioClip mediaPlayer = new AudioClip(hit.getSource());
        mediaPlayer.play();
        Group root = new Group();
        Scene scene=new Scene(root);
        String nResult="";
        Sprite imageResult=new Sprite();
        if(Result==1){
            nResult="charector/L"+(type)+".png";
            imageResult =new Sprite(nResult,640,0);
        }
        if(Result==2){
            nResult="charector/R"+(type)+".png";
            imageResult =new Sprite(nResult,0,0);
            System.out.println(imageResult.getFileName());
        }

        Canvas canvas = new Canvas( 1280, 720 );
        root.getChildren().addAll(canvas);
        scene.setFill(new ImagePattern(new Image("result"+ Result+".png")));
        GraphicsContext gc = canvas.getGraphicsContext2D();
        imageResult.render(gc);

        Button back =new Button("");
        back.setStyle("-fx-background-image: url(\"/backbt.png\");-fx-cursor:hand;-fx-pref-height: 96px;\n" +
                "    -fx-pref-width: 202px;");
        back.relocate(1280/3+120,620);
        root.getChildren().add(back);

        back.setOnAction(e -> {
            try {
                mediaPlayer.stop();
                stage.setScene(menu());
            } catch (Exception ex) {

            }

        });
        return scene;

    }
    public Scene   SelectCha(Stage s){
        Media hit = new Media(Paths.get("src/sfx/TrackM.mp3").toUri().toString());
        AudioClip mediaPlayer = new AudioClip(hit.getSource());
        mediaPlayer.setVolume(0.4);
        mediaPlayer.play();

        Group root = new Group();
        Scene scene=new Scene(root);
        Canvas canvas  = new Canvas( 1280, 720 );
        Canvas canvas2 = new Canvas( 1280, 720 );
        ArrayList<Sprite> imageChar =new ArrayList<>();
        ArrayList<Sprite> imageChar2=new ArrayList<>();
        ArrayList<Sprite> imageDis =new ArrayList<>();
        ArrayList<Sprite> imageDis2=new ArrayList<>();
        ArrayList<Sprite> imageDisBack =new ArrayList<>();
        ArrayList<Sprite> imageDis2Back=new ArrayList<>();
        ArrayList<Sprite> textDis =new ArrayList<>();

        String pre="charector/",name="char",sub=".png";
        String pre2="layout/",name2="ly";
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GraphicsContext ly = canvas2.getGraphicsContext2D();
        root.getChildren().add(canvas);
        Button []charector =new Button[4];
        Button []charector2=new Button[4];
        Button []select    =new Button[2];
        Button enterGame   =new Button();
        enterGame.setStyle("-fx-background-image: url(\"/startbt.png\");-fx-cursor:hand;-fx-pref-height: 96px;\n" +
                "    -fx-pref-width: 202px;");




        scene.setFill(new ImagePattern (new Image("select.png")));
        ImageView m=new ImageView(new Image("charector/base.png"));
        for (int i = 0; i <4 ; i++) {
            textDis.add(new Sprite(pre2+name2+(i)+sub,260,180));

        }
        for (int i = 0; i <4 ; i++) {
            textDis.add(new Sprite(pre2+name2+(i)+sub,685,180));

        }
        for (int i = 0; i <4 ; i++) {
            imageDisBack .add(new Sprite(pre+"L"+(i)+sub,0,0));
            imageDis2Back.add(new Sprite(pre+"R"+(i)+sub,640,0));
        }
        for (int i = 0; i <4 ; i++) {
            imageDis .add(new Sprite(pre+"dis"+name+(i)+sub,350,300));
            imageDis2.add(new Sprite(pre+"dis"+name+(i)+sub,810,300));
        }
        root.getChildren().addAll(m,canvas2);
        int cX=14,cY=194;
        for (int i = 0; i <charector.length ; i++) {

            charector[i]=new Button("char "+i);
            charector[i].relocate(cX,cY);
            imageChar.add(new Sprite(pre+name+(i+1)+sub,cX,cY));
            charector[i].setPrefSize(200,90);
            charector[i].setOpacity(0);
            root.getChildren().add(charector[i]);
            cY+=112;
        }
        cX=1070; cY=194;
        for (int i = 0; i <charector2.length ; i++) {
            charector2[i]=new Button("char1 "+i);
            charector2[i].relocate(cX,cY);
            imageChar2.add(new Sprite(pre+name+(i+1)+"_2"+sub,cX,cY));
            charector2[i].setPrefSize(200,90);
            charector2[i].setOpacity(0);
            root.getChildren().add(charector2[i]);
            cY+=112;
        }
        enterGame.relocate(1280/2-100,600);
        root.getChildren().addAll(enterGame);


        AnimationTimer timer = new AnimationTimer() {
            double lastNanoTime = System.nanoTime() ;
            int select[]={0,0};
            int seleP1=0,seleP2=0;
            int rend=-1;
            @Override
            public void handle(long now) {
                double elapsedTime = (now - lastNanoTime) / 10000000.0;
                lastNanoTime = now;
                charector [0].setOnMouseEntered(event -> rend=0);
                charector [1].setOnMouseEntered(event -> rend=1);
                charector [2].setOnMouseEntered(event -> rend=2);
                charector [3].setOnMouseEntered(event -> rend=3);

                charector2 [0].setOnMouseEntered(event -> rend=4);
                charector2 [1].setOnMouseEntered(event -> rend=5);
                charector2 [2].setOnMouseEntered(event -> rend=6);
                charector2 [3].setOnMouseEntered(event -> rend=7);


                charector [0].setOnAction(e -> {
                    select[0]=0;
                    seleP1=0;
                });
                charector [1].setOnAction(e -> {
                    select[0]=1;
                    seleP1=1;
                });
                charector [2].setOnAction(e -> {
                    select[0]=2;
                    seleP1=2;
                });
                charector [3].setOnAction(e -> {
                    select[0]=3;
                    seleP1=3;
                });
                charector2[0].setOnAction(e -> {
                    select[1]=0;
                    seleP2=0;
                });
                charector2[1].setOnAction(e -> {
                    select[1]=1;
                    seleP2=1;
                });
                charector2[2].setOnAction(e -> {
                    select[1]=2;
                    seleP2=2;
                });
                charector2[3].setOnAction(e -> {
                    select[1]=3;
                    seleP2=3;
                });

                enterGame.setOnAction(e -> {
                    mediaPlayer.stop();
                    stage=s;
                    stage.setScene(stageSele(seleP1,seleP2));
                    stage.show();
                });
                for (int i = 0; i <4 ; i++) {
                    charector[i].setOnMouseExited(event -> rend=-1);
                    charector2[i].setOnMouseExited(event -> rend=-1);
                }

                gc.clearRect(0, 0, 1280,720);
                ly.clearRect(0, 0, 1280,720);
                if(rend>=0)textDis.get(rend).render(ly);
                for (Sprite c:imageChar) {
                    c.render(gc);
                }
                for (Sprite c:imageChar2) {
                    c.render(gc);
                }
                imageDisBack.get(select[0]).render(gc);
                imageDis2Back.get(select[1]).render(gc);
                imageDis.get(select[0]).render(gc);
                imageDis2.get(select[1]).render(gc);
            }
        };
        timer.start();



        return scene;
    }
    public void playsoundBGM(int map , double vol){
        String[]maps = {"src/sfx/iceHell.wav","src/sfx/deepForest.wav","src/sfx/armyGo.wav","src/sfx/stoneValley.wav",};
        Media hit = new Media(Paths.get(maps[map]).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(hit.getSource());
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();

    }
    public Scene   Game(int seleP1,int seleP2,int map) {
        //setScene
        String[]maps = {"src/sfx/iceHell.wav","src/sfx/deepForest.wav","src/sfx/armyGo.wav","src/sfx/stoneValley.wav",};
        Media hit = new Media(Paths.get(maps[map]).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(hit.getSource());
        mediaPlayer.setVolume(0.3);
        mediaPlayer.play();
        int same=0;
        String       nameMap          ="bg"+map+".png";
        Weapon       defaultWeapon    = setDefaultWeapon(seleP1);
        Weapon       defaultWeapon2   = setDefaultWeapon(seleP2);
        if(seleP1==seleP2)same=1;
        Character    player           = setCharector(seleP1,1,defaultWeapon,0);
        Character    player2          = setCharector(seleP2,2,defaultWeapon2,same);

        Sprite       weaponInterFace  = new Sprite   (defaultWeapon .getFileName(),0,300);
        Sprite       weaponInterFace2 = new Sprite   (defaultWeapon2.getFileName(),1180,320);
        Sprite       blockInterFace   = new Sprite   ("block0.png",15,195);
        Sprite       blockInterFace2  = new Sprite   ("block0.png",1190,445);


        Image image3                  = new Image    ("interface_playtime.png");// specify character image
        ImageView    imageView3       = new ImageView(image3);// show image

        Sprite       imageDisplay     = new Sprite("charector/char"+(seleP1+1)+".png",44,14);
        Sprite       imageDisplay2    = new Sprite("charector/char"+(seleP2+1)+"_22.png",1038,607);

        Group root                    = new Group();
        Scene        scene            = new Scene(root);
        //set BG
        scene.setFill(new ImagePattern(new Image(nameMap)));

        //set status player
        player .setPosition(90,590);
        player2.setPosition(1145,65);

        scene.setOnKeyPressed (event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));

        //setGame
        Canvas canvas = new Canvas(1280 , 720);
        Canvas layout = new Canvas(1280 , 720);

        root.getChildren().addAll(canvas,player,player2,imageView3,layout);

        ArrayList<Block> blockList = new ArrayList<>();

        ArrayList<Bullet> bullet=new ArrayList<>();
        ArrayList<Weapon> weaponsList=new ArrayList<>();
        ArrayList<Sprite> viewLife=new ArrayList<>();
        ArrayList<Sprite> viewLife2=new ArrayList<>();
        genMap(blockList,map);


        int x=310;
        for (int i = 0; i <player.getLife() ; i++) {
            viewLife.add(new Sprite("heart.png",x,50));
            if(player.getLife()>3)x+=30;
            else x+=40;
        }
        x=825;
        for (int i = 0; i <player2.getLife() ; i++) {
            viewLife2.add(new Sprite("heart.png",x,635));
            if(player2.getLife()>3)x+=30;
            else x+=40;
        }

        int []directionOffset={0,140,70,210};

        //render obj
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GraphicsContext ly = layout.getGraphicsContext2D();

        //Animation
        AnimationTimer timer = new AnimationTimer() {


            double lastNanoTime = System.nanoTime() ;
            int count=0,count2=0,countWeapon=0,countAll=0,countB1=0,countB2=0;
            int countLimitWeapon=0;
            @Override
            public void handle(long now) {


                double elapsedTime = (now - lastNanoTime) / 10000000.0;
                lastNanoTime = now;
                count++;  count2++; countWeapon++; countAll++; countB1++; countB2++;
                double Rate  =count/10.0,   Rate2 =count2/10.0,RateWeapon=countWeapon/60.0,RateAll=countAll/10.0,
                       RateB1=countB1/60.0 ,RateB2=countB2/60.0;

                if (isPressed(KeyCode.W) && collision(player, blockList, "UP")&&player.getTranslateY()>55) {
                    player.animation.play();
                    player.animation.setOffsetY(directionOffset[3]);
                    player.moveY(-player.getSpeed());
                    player.direction=3;
                } else if (isPressed(KeyCode.S) && collision(player, blockList, "DOWN")&&player.getTranslateY()+68<scene.getHeight()-63) {
                    player.animation.play();
                    player.animation.setOffsetY(directionOffset[0]);
                    player.moveY(player.getSpeed());
                    player.direction=0;
                } else if (isPressed(KeyCode.D) && collision(player, blockList, "RIGHT")&&player.getTranslateX()+57<scene.getWidth()-70) {
                    player.animation.play();
                    player.animation.setOffsetY(directionOffset[2]);
                    player.moveX(player.getSpeed());
                    player.direction=2;
                } else if (isPressed(KeyCode.A) && collision(player, blockList, "LEFT")&&player.getTranslateX()>75) {
                    player.animation.play();
                    player.animation.setOffsetY(directionOffset[1]);
                    player.moveX(-player.getSpeed());
                    player.direction=1;
                } else if (isPressed(KeyCode.R)&&RateB1>1) {
                    if (player.getNumOfBlock()!=0) {
                        Block newBlock = build(player);
                        if (checkRender(blockList, weaponsList, newBlock))
                        playSoundCreateblock(4,1);

                        else{

                            playSoundCreateblock(newBlock.getType(),1);
                            blockList.add(newBlock);
                            player.removeBlock();
                        }
                    }else System.out.println("player 1 don't have block");
                    countB1=0;
                }
                else {
                    player.animation.stop();
                }
                if (isPressed(KeyCode.F)&&Rate>=player.getFireRate()&&player.getBullet()!=0) {
                    playSoundWeapon(player.getTypeWeapon(),0.5);
                    if(player.getTypeWeapon()==1) {
                        createBulletShotGun((player.getTranslateX() + 23), player.getTranslateY() + 30,player,bullet);
                    }else {
                        bullet.add(createBullet((player.getTranslateX() + 23), player.getTranslateY() + 30, player));
                        player.shoot();

                    }
                    count=0;
                }


                //////////////////control player 2

                if (isPressed(KeyCode.UP) && collision(player2, blockList, "UP")&&player2.getTranslateY()>55) {
                    player2.animation.play();
                    player2.animation.setOffsetY(directionOffset[3]);
                    player2.moveY(-player2.getSpeed());
                    player2.direction=3;
                } else if (isPressed(KeyCode.DOWN) && collision(player2, blockList, "DOWN")&&player2.getTranslateY()+68<scene.getHeight()-63) {
                    player2.animation.play();
                    player2.animation.setOffsetY(directionOffset[0]);
                    player2.moveY(player2.getSpeed());
                    player2.direction=0;
                } else if (isPressed(KeyCode.RIGHT) && collision(player2, blockList, "RIGHT")&&player2.getTranslateX()+57<scene.getWidth()-70) {
                    player2.animation.play();
                    player2.animation.setOffsetY(directionOffset[2]);
                    player2.moveX(player2.getSpeed());
                    player2.direction=2;
                } else if (isPressed(KeyCode.LEFT) && collision(player2, blockList, "LEFT")&&player2.getTranslateX()>75) {
                    player2.animation.play();
                    player2.animation.setOffsetY(directionOffset[1]);
                    player2.moveX(-player2.getSpeed());
                    player2.direction=1;
                } else if (isPressed(KeyCode.CONTROL)&&RateB2>1) {
                    if (player2.getNumOfBlock()!=0) {
                        Block newBlock = build(player2);
                        if (checkRender(blockList, weaponsList, newBlock)) {System.out.println("intersect");
                        playSoundCreateblock(4,1);}
                        else{

                            playSoundCreateblock(newBlock.getType(),1);
                            blockList.add(newBlock);
                            player2.removeBlock();
                        }

                    }else System.out.println("don't have block");
                    countB2=0;
                }
                else {
                    player2.animation.stop();
                }
                if (isPressed(KeyCode.SLASH)&&Rate2>=player2.getFireRate()&&player2.getBullet()!=0) {
                    playSoundWeapon(player2.getTypeWeapon(),0.5);
                    if(player2.getTypeWeapon()==1) {
                        createBulletShotGun((player2.getTranslateX() + 23), player2.getTranslateY() + 30,player2,bullet);
                    }else {
                        bullet.add(createBullet((player2.getTranslateX() + 23), player2.getTranslateY() + 30, player2));
                        player2.shoot();
                    }
                    count2=0;
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

                    //detect(bullet,blockList);
                    detect(bullet,blockList,player,player2);

                }

                //this is a random weapon
                if(RateWeapon>7&&countLimitWeapon<=5){
                    Weapon weapon;
                    do {
                        weapon=createWeapon();
                    }while (checkRender(blockList,weaponsList,weapon));
                    weaponsList.add(weapon);
                    countLimitWeapon++;
                    System.out.println(countLimitWeapon);
                    countWeapon=0;
                }

                //get Weapon && Magazine
                if(weaponsList.size()!=0) {
                    for (int i = 0; i <weaponsList.size(); i++) {
                        if (weaponsList.get(i).getType()>=4){
                            if (player.intersects(weaponsList.get(i))) {
                                playSoundWeapon(4,0.4);
                                player.getMag();
                                countLimitWeapon--;
                                weaponsList.remove(i);
                            } else
                            if (player2.intersects(weaponsList.get(i))) {
                                player2.getMag();
                                countLimitWeapon--;
                                weaponsList.remove(i);
                            }
                        }else
                        if (weaponsList.get(i).getType()<4){
                            if (player.intersects(weaponsList.get(i))) {
                                if(player.getTypeWeapon()!=weaponsList.get(i).getType()) {
                                    player.getWeapon(weaponsList.get(i));
                                    ly.clearRect(8, 360, 120, 120);
                                    weaponInterFace.setImage("in" + weaponsList.get(i).getFileName());
                                    System.out.println(weaponsList.get(i).getFileName());
                                }
                                countLimitWeapon--;
                                weaponsList.remove(i);
                            } else
                            if (player2.intersects(weaponsList.get(i))) {
                                playSoundWeapon(4,0.4);
                                if(player2.getTypeWeapon()!=weaponsList.get(i).getType()) {
                                    player2.getWeapon(weaponsList.get(i));
                                    weaponInterFace2.setImage("in" + weaponsList.get(i).getFileName());
                                    ly.clearRect(1425, 390, 120, 120);
                                }
                                countLimitWeapon--;
                                weaponsList.remove(i);
                            }
                        }
                    }
                }

                //default weapon
                if(player.getBullet()<=0&&player.getTypeWeapon()!=defaultWeapon.getType()){
                    player.getWeapon(defaultWeapon );
                    weaponInterFace.setImage(defaultWeapon .getFileName());
                }
                if(player2.getBullet()<=0&&player2.getTypeWeapon()!=defaultWeapon2.getType()){
                    player2.getWeapon(defaultWeapon2);
                    weaponInterFace2.setImage(defaultWeapon2.getFileName());

                }
                //check HP
                if(player.getHp()<=0){
                    player.setLife(player.getLife()-1);
                    if(player.getLife()<=0){
                        mediaPlayer.stop();
                      stage.setScene(gameResult(2,seleP2));
                      stop();
                    }
                    if(player.getLife()>0){
                        viewLife.remove(viewLife.size()-1);
                        player.setHp(25);
                    }
                }
                if(player2.getHp()<=0){
                    player2.setLife(player2.getLife()-1);
                    if(player2.getLife()<=0){
                        mediaPlayer.stop();
                        stage.setScene(gameResult(1,seleP1));
                        stop();

                    }
                    if(player2.getLife()>0){
                        viewLife2.remove(viewLife2.size()-1);
                        player2.setHp(25);
                    }

                }
                gc.clearRect(0, 0, 1280,720);
                ly.clearRect(0, 0, 1280,720);



                //render All
                for (Sprite com : viewLife )com.render( ly );
                for (Sprite com : viewLife2 )com.render( ly );
                for (Weapon w : weaponsList )w.render( gc );
                for (Bullet b : bullet )b.render( gc );
                for (Block moneybag : blockList)moneybag.render(gc);
                imageDisplay .render(ly);
                imageDisplay2.render(ly);
                //check went render block
                if (player .getNumOfBlock()!=0)blockInterFace .setImage(player.getNameFirstBlock());else
                    blockInterFace .setImage("noblock.png");
                if (player2.getNumOfBlock()!=0)blockInterFace2.setImage(player2.getNameFirstBlock());else
                    blockInterFace2.setImage("noblock.png");
                weaponInterFace.render(ly);
                weaponInterFace2.render(ly);
                blockInterFace.render(ly);
                blockInterFace2.render(ly);
                //show status
                Font theFont = Font.font( "Arial Narrow", FontWeight.BOLD, 30 );
                ly.setFont( theFont );
                ly.setFill(Color.WHITE);
                ly.setStroke(Color.BLACK);
                String hp1 = "" +(player.getHp());
                String hp2 = (player2.getHp())+"";
                String bullet=""+player.getBullet(),bullet2=""+player2.getBullet();
                ly.fillText(player .getNumOfBlock()+"", 130, 270 );
                ly.fillText(player2.getNumOfBlock()+"",1140,470);
                ly.fillText( bullet, 113, 385 );
                ly.fillText(bullet2,1140,355);
                ly.fillText( hp2, 935, 700 );
                ly.fillText( hp1, 450, 33 );

            }
        };
        timer.start();
        return scene;
    }
    public Character setCharector(int playerType,int playerNum,Weapon weapon,int same){

        String pre="charector/",name="playchar"+playerType+".png";
        if(same==1)name="2_"+name;

        Image        image = new Image(pre+name);// specify character image
        ImageView    imageView = new ImageView(image);// show image
        Character    player=new Character();
        if(playerType==0){
            //scout
            player =new Character(imageView,playerNum,3,25,6,
                    playerType,3,2,2,10,1);
        }else
        if(playerType==1){
            //old man
            player =new Character(imageView,playerNum,5,25,4,
                    playerType,3,2,2,10,1);
        }else
        if(playerType==2){
            //cow girl
            player =new Character(imageView,playerNum,2,25,8,
                    playerType,3,2,2,10,1);
        }else
        if(playerType==3){
            //agent
            player =new Character(imageView,playerNum,3,25,6,
                    playerType,3,2,2,10,1);
        }
        player.getWeapon(weapon);
        System.out.println(playerNum+" "+player.getTypeWeapon());

        return player;
    }
    public Weapon  setDefaultWeapon(int playerType){
        Weapon weapon=new Weapon();
        weapon =new Weapon(playerType,"inweapon"+playerType+".png");
        return weapon;
    }
    public Bullet  createBullet(double x,double y,Character player){
        Bullet bullet = new Bullet(player.getType(),player.getDirection(),player.getDamage(),player.getSpeedBullet());
        bullet.setPosition(x,y);
        return bullet;
    }

    public void    createBulletShotGun(double x,double y,Character player,ArrayList<Bullet> bb){
        for (int i = 0; i <3 ; i++) {
            Bullet bullet = new Bullet(player.getType(),player.getDirection(),player.getDamage(),player.getSpeedBullet());
            bullet.setPosition(x,y);
            if(i==0){
                if(bullet.getVelocityX()==0)bullet.setVelocityX(1);
                if(bullet.getVelocityY()==0)bullet.setVelocityY(1);
            }
            if(i==2){
                if(bullet.getVelocityX()==0)bullet.setVelocityX(-1);
                if(bullet.getVelocityY()==0)bullet.setVelocityY(-1);
            }
            player.shoot();
            bb.add(bullet);
        }



    }
    public Weapon  createWeapon(){
        Weapon weapon=new Weapon();
        //random 0-2 | 0=handgun ,1=machineGun,2= sniper,3= shotGun
        int type=(int)(10*Math.random());
        int px,py;
        px = (int) (1000 * Math.random()) + 100;
        py = (int) (520 * Math.random()) + 100;
        if (type<4) weapon=new Weapon(type,"weapon"+type+".png");
        else  weapon=new Weapon(type,"mag.png",10);
        weapon.setPosition((double) px, (double) py);
        System.out.println("weapon rand "+type);
        return weapon;
    }
    public Block   build(Character player){
        int bx=0,by=0;
        Block block =new Block();

        if (player.getDirection() == 0) {
            //DOWN
            by = (int) player.getTranslateY() + 75;
            bx = (int) player.getTranslateX();
        }
        if (player.getDirection() == 3) {
            //UP
            by = (int) player.getTranslateY() - 70;
            bx = (int) player.getTranslateX();
        }
        if (player.getDirection() == 1) {
            //LEFT
            bx = (int) player.getTranslateX() - 75;
            by = (int) player.getTranslateY();
        }
        if (player.getDirection() == 2) {
            //RIGHT
            bx = (int) player.getTranslateX() + 57;
            by = (int) player.getTranslateY()-1;
        }

        block=player.buildBlock();
        block.setPosition(bx,by);
        block.setChange(0);
        return block;
    }
    public void    genMap     (ArrayList<Block> blocks,int map){
        int px=73,py=52,count=0,index=0;
        // 15x9 block All 120
        int type=map;
        int [][]bb= new int[][]{{5,12,18,20,21,24,25,32,33,40,41,43,44,47,50,51,53,59,60,66,68,69,72,75,76,78,79,86,87,94,95,98,99,101,107,114},
                                {3,5,7,15,16,18,19,20,22,26,27,28,29,31,37,38,39,49,52,55,57,58,59,60,61,62,64,67,70,80,81,82,88,90,91,92,93,97,99,100,101,103,104,112,114,116},
                                {3,5,9,13,18,24,25,26,28,31,36,37,38,41,42,43,46,47,48,51,53,66,68,71,72,73,76,77,78,81,82,83,88,91,93,94,95,101,106,110,114,116},
                                {7,31,32,33,36,38,41,42,43,47,48,50,51,52,53,54,56,57,64,66,67,68,70,76,82,88,91,93,94,96,98,100,101,103,109,111,112,113,115}};
        int primary=28;


        for (int i = 0; i <bb[map].length; i++) {
            if(primary!=0) {
                blocks.add(new Block(type, px, py));
                primary--;
            }else
                blocks.add(new Block((int)(Math.random()*4), px, py));
        }
        Collections.shuffle(blocks);

        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <15 ; j++) {
                if (index==bb[map].length)break;
                if(count==bb[map][index]){
                    //set type of block is here
                    blocks.get(index).setPosition(px,py);
                    index++;
                }
                count++;
                px+=77;
            }
            py+=77;
            px=75;
        }
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
    public boolean detect     (ArrayList<Bullet> t,ArrayList<Block> m,Character player,Character player2){
        Bullet saveBullet;
        for (int i = 0; i <t.size() ; i++) {
            for (int j = 0; j <m.size() ; j++) {
                if(t.get(i).intersects(m.get(j))){
                    m.get(j).hit(t.get(i).damage);
                    saveBullet=t.get(i);
                    t.remove(i);
                    if(m.get(j).getDurabillity()<=0) {
                        if(saveBullet.getPlayerType()==1){
                            m.get(j).setDurabillity(m.get(j).getMaxDurabillity());
                            player.getBlock(m.get(j));


                        }
                        if(saveBullet.getPlayerType()==2){
                            m.get(j).setDurabillity(m.get(j).getMaxDurabillity());
                            player2.getBlock(m.get(j));

                        }
                        m.remove(j);
                        return true;
                    }
                    break;

                }
            }
        }
        return false;
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
    public static void main   (String[] args) {
        launch(args);
    }
}