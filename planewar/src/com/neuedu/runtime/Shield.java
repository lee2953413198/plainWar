package com.neuedu.runtime;

import com.neuedu.base.BaseSpirit;
import com.neuedu.base.Draw;
import com.neuedu.base.Move;
import com.neuedu.main.GameFrame;
import com.neuedu.until.DataStore;
import com.neuedu.until.ImageMap;

import java.awt.*;

public class Shield extends BaseSpirit implements Draw, Move {

    public static boolean ishudun;

    private Image image;
    public Shield() {
        this(0,0,ImageMap.get("sd01"));
    }

    public Shield(int x, int y,Image image) {
        super(x, y);
        this.image = image;
    }

    int x = 0;
    @Override
    public void draw(Graphics g) {
        if (ishudun) {

            if (x < 300) {
                x++;

                g.drawImage(image, getX()-1, getY()-13,
                        image.getWidth(null),image.getHeight(null),
                        null);
            }

            if(x>=300){
                ishudun = false;
                x = 0;
            }
        }
        move();
        if(ishudun){
            collisionTesting();
            collisionEnemyPlane();
            collisionBossBullet();
            collisionBossBullet2();
        }

    }

    @Override
    public void move() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        setX(gameFrame.plane.getX()- 50);
        setY(gameFrame.plane.getY() - 65);
    }


    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    public void collisionTesting(){

        GameFrame gameFrame = DataStore.get("gameFrame");
        for (EnemyBullet enemyBullet : gameFrame.enemyBullets) {
            if(enemyBullet.getRectangle().intersects(this.getRectangle())){
                gameFrame.enemyBullets.remove(enemyBullet);
            }
        }

    }
    public void collisionBossBullet(){

        GameFrame gameFrame = DataStore.get("gameFrame");
        for (BossBullet bossBullet : gameFrame.bossBulletList) {
            if(bossBullet.getRectangle().intersects(this.getRectangle())){
                gameFrame.bossBulletList.remove(bossBullet);
            }
        }

    }
    public void collisionBossBullet2(){

        GameFrame gameFrame = DataStore.get("gameFrame");
        for (BossBullet2 bossBullet2 : gameFrame.bossBulletList2) {
            if(bossBullet2.getRectangle().intersects(this.getRectangle())){
                gameFrame.bossBulletList2.remove(bossBullet2);
            }
        }

    }

    public void collisionEnemyPlane(){
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (EnemyPlane enemyPlane : gameFrame.enemyPlanes) {
            if(enemyPlane.getRectangle().intersects(this.getRectangle())){
                gameFrame.enemyPlanes.remove(enemyPlane);
            }
        }
    }
}
