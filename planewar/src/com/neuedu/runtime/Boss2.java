package com.neuedu.runtime;

import com.neuedu.base.BaseSpirit;
import com.neuedu.base.Draw;
import com.neuedu.base.Move;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.until.DataStore;
import com.neuedu.until.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boss2 extends BaseSpirit implements Draw, Move {
    private List<Image> imageList = new ArrayList<>();

    private boolean up,right;

    private int speed = FrameConstant.GAME_SPEED;

    public int blood = 300;


    private Random random = new Random();



    public Boss2() {
        for (int i = 1; i <9 ; i++) {
            imageList.add(ImageMap.get("bss0"+i));

        }
    }


    int index = 0;
    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(imageList.get(index++/2),getX(),
                getY()-300,imageList.get(1).getWidth(null),
                imageList.get(1).getHeight(null),null);
        if (index>=16){
            index = 0;
        }

        fire();
        drawInfo(g);

    }

    public void drawInfo(Graphics g){
        g.setColor(Color.red);
        g.fillRect(60, 50, (int)((blood / 100.0) * FrameConstant.BLOOD_WIDTH), FrameConstant.BLOOD_HEIGHT);
    }

    @Override
    public void move() {
        if (up){
            setY(getY()-5);
        }else{
            setY(getY()+5);
        }
        if(getY()<=imageList.get(1).getHeight(null)+5){
            up = false;
        }
        if (getY()>FrameConstant.FRAME_HEIGHT/2+imageList.get(1).getHeight(null)/2){
            up = true;
        }
        if (right){
            setX(getX()+5);
        }else{
            setX(getX()-5);
        }
        if (getX()>=FrameConstant.FRAME_WIDTH-imageList.get(1).getWidth(null)){
            right = false;
        }
        if(getX()<=0){
            right= true;
        }



    }

    public void fire(){

        GameFrame gameFrame =  DataStore.get("gameFrame");
        if (random.nextInt(1000)>850){
            gameFrame.bossBulletList2.add(new BossBullet2(
                    getX()+imageList.get(1).getWidth(null)/2-ImageMap.get("bb02").getWidth(null)/2,
                    getY()+imageList.get(1).getHeight(null)/2-ImageMap.get("bb02").getHeight(null)/2-imageList.get(1).getHeight(null)-150,
                    ImageMap.get("bb02")
            ));

        }
    }
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY()-250,imageList.get(1).getWidth(null),imageList.get(1).getHeight(null));
    }




}

