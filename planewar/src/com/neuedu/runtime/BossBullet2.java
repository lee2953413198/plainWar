package com.neuedu.runtime;

import com.neuedu.base.BaseSpirit;
import com.neuedu.base.Draw;
import com.neuedu.base.Move;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.until.DataStore;
import com.neuedu.until.ImageMap;

import java.awt.*;

public class BossBullet2 extends BaseSpirit implements Draw, Move {

    private Image image;

    private double speed = FrameConstant.GAME_SPEED*4;

    public BossBullet2() {
        this(0,0, ImageMap.get("bb02"));
    }

    public BossBullet2(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);


    }

    @Override
    public void move() {
        speed = speed + Math.PI * 6 / 180;
        setX(getX() + (int)(5 * Math.cos(speed)));
        setY(getY() + (int)speed * 2);
        borderTesting();

    }
    public void borderTesting(){
        if (getY()>FrameConstant.FRAME_HEIGHT){
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.bossBulletList2.remove(this);
        }
    }
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }

    public void collisionTesting(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())){
            plane.planeblood-=10;
            gameFrame.bossBulletList2.remove(this);
            if (plane.planeblood==0){
                gameFrame.gameOver = false;
                gameFrame.gameStart = false;
            }

        }
    }
}

