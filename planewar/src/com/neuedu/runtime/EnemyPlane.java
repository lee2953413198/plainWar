package com.neuedu.runtime;

import com.neuedu.base.BaseSpirit;
import com.neuedu.base.Draw;
import com.neuedu.base.Move;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.until.DataStore;
import com.neuedu.until.ImageMap;

import java.awt.*;
import java.util.Random;

public class EnemyPlane extends BaseSpirit implements Move, Draw {
    private Image image;
    private Image image1;
    private Image image2;
    private Image image3;

    private int speed = FrameConstant.GAME_SPEED*2;

    private Random random = new Random();

    private int type;

    public int getType() {
        return type;
    }

    public EnemyPlane() {
        this(0,0,1);
    }

    public EnemyPlane(int x, int y, int type) {
        super(x, y);
        this.type=type;
        this.image= ImageMap.get("ep01");
        this.image1= ImageMap.get("ep02");
        this.image2= ImageMap.get("ep03");
        this.image3= ImageMap.get("ep04");
    }

    @Override
    public void draw(Graphics g) {
        if (type==1){
            move();
            g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
            fire();
        }else if (type==2){
            move();
            g.drawImage(image1,getX(),getY(),image1.getWidth(null),image1.getHeight(null),null);
            fire();
        }else if (type==3){
            move();
            g.drawImage(image2,getX(),getY(),image2.getWidth(null),image2.getHeight(null),null);
            fire1();
        }else if (type==4){
            move();
            g.drawImage(image3,getX(),getY(),image3.getWidth(null),image3.getHeight(null),null);

            fire1();
        }



    }
    public void fire(){
        GameFrame gameFrame =  DataStore.get("gameFrame");
        if (random.nextInt(1000)>998){
            gameFrame.enemyBullets.add(new EnemyBullet(
                    getX()+image.getWidth(null)/2-ImageMap.get("eb01").getWidth(null)/2,
                    getY()+image.getHeight(null),
                    ImageMap.get("eb01")));
        }


    }
    public void fire1() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (random.nextInt(1000) > 998) {
            gameFrame.enemyBullets.add(new EnemyBullet(
                    getX() + image.getWidth(null) / 2 - ImageMap.get("eb02").getWidth(null) / 2,
                    getY() + image.getHeight(null),
                    ImageMap.get("eb02")));
        }
    }

    private boolean right = true;
    @Override
    public void move() {
        if (type==1){
            setY(getY()+speed);
        }else if (type==2){
            if (right) {
                setY(getY()+speed);
                setX(getX()+speed);
            }else {
                setY(getY()+speed);
                setX(getX()-speed);
            }
        }else if (type==3){
            if (right) {
                setY(getY()+speed*2);
                setX(getX()-speed*2);
            }else {
                setX(getX()+speed);
            }
        }else if (type==4){
            if (right) {
                setX(getX()+speed);
                setY(getY()+speed);
            }else {
                setX(getX()-speed);
                setY(getY()+1);
            }
        }
        borderTesting();

    }
    public void borderTesting(){
        if (type==1){
            if (getY()>FrameConstant.FRAME_HEIGHT){
                GameFrame gameFrame = DataStore.get("gameFrame");
                gameFrame.enemyPlanes.remove(this);
            }
        }else if (type==2){
            if (getX()+image1.getWidth(null)>=FrameConstant.FRAME_WIDTH){
                right= false;
            }else if (getX()<0){
                right = true;
            }
            if (getY()>FrameConstant.FRAME_HEIGHT) {
                GameFrame gameFrame = DataStore.get("gameFrame");
                gameFrame.enemyPlanes.remove(this);
            }
        }else if (type==3){
            if (getX()<0) {
                right = false;
            }else if (getX()+image1.getWidth(null)>=FrameConstant.FRAME_WIDTH){
                right=true;
            }
            if (getY()>FrameConstant.FRAME_HEIGHT) {
                GameFrame gameFrame = DataStore.get("gameFrame");
                gameFrame.enemyPlanes.remove(this);
            }
        }else if (type==4){
            if (getX()+image1.getWidth(null)>=FrameConstant.FRAME_WIDTH){
                right= false;
            }else if (getX()<0){
                right = true;
            }
            if (getY()>FrameConstant.FRAME_HEIGHT) {
                GameFrame gameFrame = DataStore.get("gameFrame");
                gameFrame.enemyPlanes.remove(this);
            }

        }

    }



    @Override
    public Rectangle getRectangle(){
        if (type==1) {
            return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
        }
        if (type==2){
            return new Rectangle(getX(),getY(),image1.getWidth(null),image1.getHeight(null));
        }
        if (type==3){
            return new Rectangle(getX(),getY(),image2.getWidth(null),image2.getHeight(null));
        }
        if (type==4){
            return new Rectangle(getX(),getY(),image3.getWidth(null),image3.getHeight(null));
        }
        return null;

    }

    public void collisionTesting(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())){
            plane.planeblood-=10;
            gameFrame.enemyPlanes.remove(this);
            if(plane.planeblood==0){
                gameFrame.gameOver = false;
                gameFrame.gameStart = false;
            }

        }
    }

    public static void addEnemyPlane(){
        Random random = new Random();
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (random.nextInt(1000)>989) {
            gameFrame.enemyPlanes.add(new EnemyPlane(
                    random.nextInt(400)+50,
                    random.nextInt(600)-500,
                    1

            ));

        }
    }
    public static void addEnemyPlane1(){
        Random random = new Random();
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (random.nextInt(1000)>987) {
            gameFrame.enemyPlanes.add(new EnemyPlane(
                    random.nextInt(400)+50,
                    random.nextInt(700)-500,
                    2

            ));

        }
    }
    public static void addEnemyPlane2(){
        Random random = new Random();
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (random.nextInt(1000)>987) {
            gameFrame.enemyPlanes.add(new EnemyPlane(
                    random.nextInt(400)+50,
                    random.nextInt(650)-500,
                    3

            ));

        }
    }
    public static void addEnemyPlane3(){
        Random random = new Random();
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (random.nextInt(1000)>987) {
            gameFrame.enemyPlanes.add(new EnemyPlane(
                    random.nextInt(400)+50,
                    random.nextInt(550)-500,
                    4

            ));

        }
    }
}
