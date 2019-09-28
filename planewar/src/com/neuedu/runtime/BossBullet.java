package com.neuedu.runtime;

import com.neuedu.base.BaseSpirit;
import com.neuedu.base.Draw;
import com.neuedu.base.Move;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.until.DataStore;
import com.neuedu.until.ImageMap;

import java.awt.*;

public class BossBullet extends BaseSpirit implements Draw, Move {

    private Image image;

    private int angel = 30;

    private int speed = FrameConstant.GAME_SPEED*4;

    public BossBullet() {
        this(0,0, ImageMap.get("bb01"),30);
    }

    public BossBullet(int x, int y, Image image,int angel) {
        super(x, y);
        this.image = image;
        this.angel=angel;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);


    }

    @Override
    public void move() {
        setX(getX()+(int)(20*Math.sin(Math.toRadians(angel))));
        setY(getY()+(int)(20*Math.cos(Math.toRadians(angel))));

        borderTesting();

    }
    public void borderTesting(){
        if (getY()>FrameConstant.FRAME_HEIGHT){
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.bossBulletList.remove(this);
        }
    }
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }

    public void collisionTesting(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())){
            plane.planeblood-=10;
            gameFrame.bossBulletList.remove(this);
            if (plane.planeblood==0){
                gameFrame.gameOver = false;
                gameFrame.gameStart = false;
            }

        }
    }
}
