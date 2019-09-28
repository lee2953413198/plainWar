package com.neuedu.runtime;

import com.neuedu.base.BaseSpirit;
import com.neuedu.base.Draw;
import com.neuedu.base.Move;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.until.DataStore;
import com.neuedu.until.ImageMap;
import com.neuedu.until.ImageUntil;

import java.awt.*;

public class Background extends BaseSpirit implements Move, Draw {

    private Image image;

    private Image image1;

    private int speed = FrameConstant.GAME_SPEED;

    private int y1 = -ImageMap.get("bg02").getHeight(null);

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Background() {

        this(0,FrameConstant.FRAME_HEIGHT-ImageMap.get("bg01").getHeight(null), ImageMap.get("bg01"));

    }

    public Background(int x, int y, Image image) {
        super(x, y);
        this.image = image;
        this.image1 = ImageMap.get("bg02");
    }

    @Override
    public void move() {

        setY(getY()+speed);
        y1+=speed;
        //System.out.println(speed);
        if (getY()>image.getHeight(null)){
            setY(y1-image.getHeight(null));

        }
        if (y1>image1.getHeight(null));{
            y1=getY()-image1.getHeight(null);

        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        g.drawImage(image1,getX(),y1,image.getWidth(null),image.getHeight(null),null);
        move();

    }
}

