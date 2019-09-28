package com.neuedu.runtime;

import com.neuedu.GameStart;
import com.neuedu.base.BaseSpirit;
import com.neuedu.base.Draw;
import com.neuedu.base.Move;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.until.DataStore;
import com.neuedu.until.ImageMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends BaseSpirit implements Move, Draw {
    private Image image;

    private boolean up,down,left,right,fire,fire1,skill;

    private int speed = FrameConstant.GAME_SPEED * 4;

    public int planeblood = 100;

    public int magic = 100;

    public Plane() {
        this((FrameConstant.FRAME_WIDTH-ImageMap.get("my01").getWidth(null))/2,
                FrameConstant.FRAME_HEIGHT-ImageMap.get("my01").getHeight(null),
                ImageMap.get("my01"));

    }

    public Plane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image,getX(),getY(),image.getWidth(null)/2,image.getHeight(null)/2,null);
            fire2();
            fire();
            fire3();
        fire1();
        skill();
        if (fire || fire1){
            index++;
            if (index>10){
                index = 0;
            }
        }
        drawInfo(g);
    }

    public void drawInfo(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(FrameConstant.BLOOD_X, FrameConstant.BLOOD_Y, (int)((planeblood / 100.0) * FrameConstant.BLOOD_WIDTH), FrameConstant.BLOOD_HEIGHT);
        g.setColor(Color.WHITE);
        g.fillRect(FrameConstant.MAGIC_X, FrameConstant.MAGIC_Y, (int)((magic / 100.0) * FrameConstant.MAGIC_WIDTH), FrameConstant.MAGIC_HEIGHT);
    }

    private int index = 0;
    /**
     * 开火方法
     * 判断开关是否打开
     * 创建一个子弹对象放入到gameFrame的子弹集合中
     */
    public void fire(){
        GameFrame gameFrame =  DataStore.get("gameFrame");
        if (fire && index == 0 &&(gameFrame.rank!=2&&gameFrame.rank!=3)){

            gameFrame.bulletList.add(new Bullet(
                    getX()+image.getWidth(null)/3-ImageMap.get("bt01").getWidth(null)/2-8,
                    getY()-ImageMap.get("bt01").getHeight(null),
                    1
            ));
            gameFrame.bulletList.add(new Bullet(
                    getX()+image.getWidth(null)/4-ImageMap.get("bt01").getWidth(null)/2-8,
                    getY()-ImageMap.get("bt01").getHeight(null),
                    1
            ));

        }
    }
    public void fire2(){
        GameFrame gameFrame =  DataStore.get("gameFrame");
       if (fire && index == 0 && gameFrame.rank==2){

            gameFrame.bulletList.add(new Bullet(
                    getX()+image.getWidth(null)/3-ImageMap.get("bt01").getWidth(null)/2-6,
                    getY()-ImageMap.get("bt01").getHeight(null),
                    1
            ));
           gameFrame.bulletList.add(new Bullet(
                   getX()+image.getWidth(null)/4-ImageMap.get("bt01").getWidth(null)/2,
                   getY()-ImageMap.get("bt01").getHeight(null),
                   1
           ));
           gameFrame.bulletList.add(new Bullet(
                   getX()+image.getWidth(null)/5-ImageMap.get("bt01").getWidth(null)/2,
                   getY()-ImageMap.get("bt01").getHeight(null),
                   1
           ));


        }
    }
    public void fire3(){
        GameFrame gameFrame =  DataStore.get("gameFrame");
        if (fire && index == 0 && gameFrame.rank==3){

            gameFrame.bulletList.add(new Bullet(
                    getX()+image.getWidth(null)/3-ImageMap.get("bt01").getWidth(null)/2-6+5,
                    getY()-ImageMap.get("bt01").getHeight(null),
                    1
            ));
            gameFrame.bulletList.add(new Bullet(
                    getX()+image.getWidth(null)/4-ImageMap.get("bt01").getWidth(null)/2+5,
                    getY()-ImageMap.get("bt01").getHeight(null),
                    1
            ));
            gameFrame.bulletList.add(new Bullet(
                    getX()+image.getWidth(null)/5-ImageMap.get("bt01").getWidth(null)/2+6,
                    getY()-ImageMap.get("bt01").getHeight(null),
                    1
            ));
            gameFrame.bulletList.add(new Bullet(
                    getX()+image.getWidth(null)/6-ImageMap.get("bt01").getWidth(null)/2+4,
                    getY()-ImageMap.get("bt01").getHeight(null),
                    1
            ));

        }
    }

    public void fire1(){
        GameFrame gameFrame =  DataStore.get("gameFrame");
        if (fire1 && index == 0 && gameFrame.background.getY()>0){
            gameFrame.bulletList.add(new Bullet(
                    getX()+image.getWidth(null)/4-ImageMap.get("bt02").getWidth(null)/2,
                    getY()-ImageMap.get("bt02").getHeight(null),
                    2
            ));
        }
    }

    public void skill(){
        GameFrame gameFrame =  DataStore.get("gameFrame");
        if (skill && magic ==100){
            gameFrame.bulletList.add(new Bullet(
                    getX()+image.getWidth(null)/4-ImageMap.get("bt03").getWidth(null)/2,
                    getY()-ImageMap.get("bt03").getHeight(null),
                    3
            ));
            magic = 0;

        }

    }
    @Override
    public void move() {
        if (up){
            setY(getY()-speed);
        }
        if (down){
            setY(getY()+speed);
        }
        if (left){
            setX(getX()-speed);
        }
        if (right){
            setX(getX()+speed);
        }
        borderTesting();
    }

    public void borderTesting(){
        if (getX()<0){
            setX(0);
        }
        if (getX()>FrameConstant.FRAME_WIDTH-image.getWidth(null)/2){
            setX(FrameConstant.FRAME_WIDTH-image.getWidth(null)/2);
        }
        if (getY()<30){
            setY(30);
        }
        if (getY()>FrameConstant.FRAME_HEIGHT-image.getHeight(null)/2){
            setY(FrameConstant.FRAME_HEIGHT-image.getHeight(null)/2);
        }
    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode()==KeyEvent.VK_W){
            up = true;

        }
        if (e.getKeyCode()==KeyEvent.VK_S){
            down = true;

        }
        if (e.getKeyCode()==KeyEvent.VK_A){
            left = true;

        }
        if (e.getKeyCode()==KeyEvent.VK_D){
            right = true;

        }
        if (e.getKeyCode()==KeyEvent.VK_J){

            fire=true;

        }
        if (e.getKeyCode()==KeyEvent.VK_K){

            fire1=true;

        }
        if (e.getKeyCode()==KeyEvent.VK_L){

            skill=true;

        }

    }
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode()==KeyEvent.VK_W){
            up = false;

        }
        if (e.getKeyCode()==KeyEvent.VK_S){
            down = false;

        }
        if (e.getKeyCode()==KeyEvent.VK_A){
            left = false;

        }
        if (e.getKeyCode()==KeyEvent.VK_D){
            right =false;

        }
        if (e.getKeyCode()==KeyEvent.VK_J){

            fire = false;

        }
        if (e.getKeyCode()==KeyEvent.VK_K){

            fire1=false;

        }
        if (e.getKeyCode()==KeyEvent.VK_L){

            skill=false;

        }

    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }

    public void collisionTesting(Boss boss){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (boss.getRectangle().intersects(this.getRectangle())){
            planeblood-=10;
            if (planeblood==0){
                gameFrame.gameOver = false;
                gameFrame.gameStart = false;
            }

        }
    }
}

