package com.neuedu.runtime;

import com.neuedu.base.BaseSpirit;
import com.neuedu.base.Draw;
import com.neuedu.base.Move;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.until.DataStore;
import com.neuedu.until.ImageMap;

import java.awt.*;

public class EnemyBullet extends BaseSpirit implements Move, Draw {
    private Image image;

    private int speed = FrameConstant.GAME_SPEED*4;
    public EnemyBullet() {
        this(0,0,ImageMap.get("eb01"));

    }

    public EnemyBullet(int x, int y, Image image) {
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

        setY(getY()+speed);
        borderTesting();

    }
    public void borderTesting(){
        if (getY()>FrameConstant.FRAME_HEIGHT){
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.enemyBullets.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }

    public void collisionTesting(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())){
            plane.planeblood-=10;
            gameFrame.enemyBullets.remove(this);
            if (plane.planeblood==0){
                gameFrame.gameOver = false;
                gameFrame.gameStart = false;
            }

        }
    }

}
