package com.neuedu.runtime;

import com.neuedu.base.BaseSpirit;
import com.neuedu.base.Draw;
import com.neuedu.base.Move;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.until.DataStore;
import com.neuedu.until.ImageMap;
import java.util.List;

import java.awt.*;

public class Bullet extends BaseSpirit implements Move, Draw {
    private Image image1;
    private Image image2;
    private Image image3;

    private int type;
    private int count;

    private int speed = FrameConstant.GAME_SPEED * 5;

    public Bullet() {
        this(0, 0, 1);
    }

    public Bullet(int x, int y, int type) {
        super(x, y);
        this.type = type;
        this.count = 0;
        this.image1 = ImageMap.get("bt01");
        this.image2 = ImageMap.get("bt02");
        this.image3 = ImageMap.get("bt03");
    }

    @Override
    public void draw(Graphics g) {
        if (type ==1){
            move();
            borderTesting();
            g.drawImage(image1, getX(), getY(), image1.getWidth(null), image1.getHeight(null), null);
        }
        if (type==2){
            move();
            borderTesting();
            g.drawImage(image2, getX(), getY(), image2.getWidth(null), image2.getHeight(null), null);

        }
        if (type == 3){
            move();
            borderTesting();
            g.drawImage(image3, getX(), getY(), image3.getWidth(null), image3.getHeight(null), null);

        }

    }

    @Override
    public void move() {
        if(type ==3){

        }else{
            setY(getY() - speed);
        }


    }

    public void borderTesting() {
        if (type ==1){
            if (getY() < 30 - image1.getHeight(null)) {
                GameFrame gameFrame = DataStore.get("gameFrame");
                gameFrame.bulletList.remove(this);
            }
        }
        if (type ==2){
            if (getY() < 30 - image2.getHeight(null)) {
                GameFrame gameFrame = DataStore.get("gameFrame");
                gameFrame.bulletList.remove(this);
            }
        }
        if (type ==3){
            if (getY() < 30 - image3.getHeight(null)) {
                GameFrame gameFrame = DataStore.get("gameFrame");
                gameFrame.bulletList.remove(this);
            }
        }

    }

    @Override
    public Rectangle getRectangle() {
        if (type==1){
            return new Rectangle(getX(), getY(), image1.getWidth(null), image1.getHeight(null));
        }
        if (type==2){
            return new Rectangle(getX(), getY(), image2.getWidth(null), image2.getHeight(null));
        }
        if (type==3){
            return new Rectangle(getX(), getY(), image3.getWidth(null), image3.getHeight(null));
        }
        return null;
    }

    public void collisionTesting(List<EnemyPlane> enemyPlaneList) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (EnemyPlane enemyPlane : enemyPlaneList) {
                if (enemyPlane.getRectangle().intersects(this.getRectangle())) {
                    if (type == 1 || type == 2) {
                        enemyPlaneList.remove(enemyPlane);
                        gameFrame.plane.magic += 5;
                        if (gameFrame.plane.magic >= 100) {
                            gameFrame.plane.magic = 100;
                        }
                        gameFrame.fraction += enemyPlane.getType() * 50;
                        gameFrame.bulletList.remove(this);
                    }
                    if (this.type == 3) {
                        count++;
                        enemyPlaneList.remove(enemyPlane);
                        gameFrame.fraction += enemyPlane.getType() * 50;
                        if (count>=5) {
                            gameFrame.bulletList.remove(this);
                        }


                    }
                }


        }

    }

    public void collisionTesting2(Boss boss) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (boss.getRectangle().intersects(this.getRectangle())) {
            if (type == 1 || type == 2) {
                boss.blood--;
                gameFrame.plane.magic += 5;
                if (gameFrame.plane.magic >= 100) {
                    gameFrame.plane.magic = 100;
                }
                gameFrame.bulletList.remove(this);
            }
            if (type == 3) {
                count++;
                if (count >= 5) {
                    gameFrame.bulletList.remove(this);
                    boss.blood -= 10;
                }
            }

        }

    }
    public void collisionTesting3(Boss2 boss2) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (boss2.getRectangle().intersects(this.getRectangle())) {
            if (type == 1 || type == 2) {
                boss2.blood--;
                gameFrame.plane.magic += 5;
                if (gameFrame.plane.magic > 100) {
                    gameFrame.plane.magic = 100;
                }
                gameFrame.bulletList.remove(this);
            }
            if (type == 3) {
                count++;
                if (count >= 6) {
                    gameFrame.bulletList.remove(this);
                    boss2.blood -= 10;
                }
            }

        }

    }
}
