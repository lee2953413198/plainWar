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

public class Prop extends BaseSpirit implements Draw, Move {

    private List<Image> imageList = new ArrayList<>();

    private int speed = FrameConstant.GAME_SPEED;

    public Prop() {

        for (int i = 1; i < 5; i++) {
            imageList.add(ImageMap.get("pp0"+i));
        }

    }

    int index = 0;
    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(imageList.get(index++/5),getX(),getY(),imageList.get(1).getWidth(null),
                imageList.get(1).getHeight(null),null);
        if (index>=20){
            index = 0;
        }

    }

    boolean right = true;
    @Override
    public void move() {
        if (right) {
            setY(getY()+speed);
            setX(getX()+speed);
        }else {
            setY(getY()+speed);
            setX(getX()-speed*2);
        }

        borderTesting();
    }
    public void borderTesting(){
        if (getX()+imageList.get(1).getWidth(null)>=FrameConstant.FRAME_WIDTH){
            right= false;
        }else if (getX()<0){
            right = true;
        }
        if (getY()>FrameConstant.FRAME_HEIGHT){
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.props.remove(this);
        }
    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), imageList.get(1).getWidth(null), imageList.get(1).getHeight(null));
    }

    public void collisionTesting(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            if (plane.planeblood<100){
                plane.planeblood += 10;
            }

            gameFrame.props.remove(this);
        }

        }
    public static void addProp(){
        Random random = new Random();
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (random.nextInt(1000)>997) {
            gameFrame.props.add(new Prop());

        }
    }
}
