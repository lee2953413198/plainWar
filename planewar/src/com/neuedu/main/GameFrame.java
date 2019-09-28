package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.*;
import com.neuedu.until.DataStore;
import com.neuedu.until.ImageMap;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {
    //创建背景对象

    public Background background = new Background();

    //创建飞机对象
    public Plane plane = new Plane();

    //创建护盾对象
    public Shield shield = new Shield();

    //创建boss对象
    private Boss boss = new Boss();
    private Boss2 boss2 = new Boss2();

    public Boss getBoss() {
        return boss;
    }

    public int rank=1;

    public int Checkpoint = 1;


    //定义一个布尔变量来判断boss的draw方法
    public boolean a = true;
    public boolean b = true;
    public boolean c = true;

    //创建子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();

    //创建敌方子弹集合
    public final List<EnemyBullet>  enemyBullets = new CopyOnWriteArrayList<>();

    //创建boss子弹集合
    public final List<BossBullet> bossBulletList= new CopyOnWriteArrayList<>();
    public final List<BossBullet2> bossBulletList2= new CopyOnWriteArrayList<>();

    //创建敌方飞机集合
    public final List<EnemyPlane> enemyPlanes = new CopyOnWriteArrayList<>();

    //创建道具集合
    public final List<Prop> props = new CopyOnWriteArrayList<>();
    public final List<Prop2> props2 = new CopyOnWriteArrayList<>();

    public boolean gameOver = true;
    public boolean gameStart = false;

    public int fraction = 0;

    public void start(Graphics g) {
        if (!gameStart && gameOver) {
            background.draw(g);
            g.drawImage(ImageMap.get("start"),
                    FrameConstant.FRAME_WIDTH / 2 - ImageMap.get("start").getWidth(null) / 2,
                    FrameConstant.FRAME_HEIGHT / 2 - ImageMap.get("start").getHeight(null) / 2,
                    ImageMap.get("start").getWidth(null),
                    ImageMap.get("start").getHeight(null),
                    null);
        }

    }

    public void next(Graphics g) {
        if (background.getY() > 0 && background.getY() < FrameConstant.FRAME_WIDTH/4) {
            g.drawImage(ImageMap.get("next"),
                    FrameConstant.FRAME_WIDTH / 2 - ImageMap.get("start").getWidth(null) / 2-50,
                    FrameConstant.FRAME_HEIGHT / 2 - ImageMap.get("start").getHeight(null) / 2,
                    ImageMap.get("next").getWidth(null),
                    ImageMap.get("next").getHeight(null),
                    null);

        }
    }
    public void increas(Graphics g) {
        if (background.getY() > 0 && background.getY() < FrameConstant.FRAME_WIDTH/4) {
            g.drawImage(ImageMap.get("increase"),
                    FrameConstant.FRAME_WIDTH / 2 - ImageMap.get("start").getWidth(null) / 2-100,
                    FrameConstant.FRAME_HEIGHT / 2 - ImageMap.get("start").getHeight(null) / 2 + 100,
                    ImageMap.get("increase").getWidth(null),
                    ImageMap.get("increase").getHeight(null),
                    null);

        }
    }

    public void allover(Graphics g) {
            g.drawImage(ImageMap.get("allover"),
                    FrameConstant.FRAME_WIDTH / 2 - ImageMap.get("allover").getWidth(null) / 2,
                    FrameConstant.FRAME_HEIGHT / 2 - ImageMap.get("allover").getHeight(null) / 2 ,
                    ImageMap.get("allover").getWidth(null),
                    ImageMap.get("allover").getHeight(null),
                    null);

    }

    @Override
    public void paint(Graphics g) {
        start(g);
        if (!gameOver&&gameStart){
            background.draw(g);
            if (b){
                EnemyPlane.addEnemyPlane();
                EnemyPlane.addEnemyPlane1();
                EnemyPlane.addEnemyPlane2();
                EnemyPlane.addEnemyPlane3();
            }

            Prop.addProp();
            Prop2.addProp();
            for (EnemyPlane enemyPlane : enemyPlanes) {
                enemyPlane.draw(g);

            }

            for (EnemyBullet enemyBullet : enemyBullets) {
                enemyBullet.draw(g);
            }

            for (BossBullet bossBullet : bossBulletList) {
                bossBullet.draw(g);
            }
            for (BossBullet2 bossBullet2 : bossBulletList2) {
                bossBullet2.draw(g);
            }

            for (Prop prop : props) {
                prop.draw(g);

            }
            for (Prop2 prop : props2) {
                prop.draw(g);

            }
            if (background.getY()>=0){

                if (a) {
                    boss.draw(g);
                    background.setSpeed(0);
                    b=false;
                    for (Bullet bullet : bulletList) {

                        bullet.collisionTesting2(boss);
                    }
                        plane.collisionTesting(boss);
                        if (boss.blood <= 0) {
                            GameFrame gameFrame = DataStore.get("gameFrame");
                            gameFrame.fraction += 100000;
                            boss = null;
                            a = false;
                            b = true;
                            gameFrame.Checkpoint = 2;
                            background.setSpeed(FrameConstant.GAME_SPEED);
                        }
                }
            }
            if (fraction>180000){
                if (c) {
                    boss2.draw(g);
                    b = false;
                    background.setSpeed(0);
                    for (Bullet bullet : bulletList) {
                        bullet.collisionTesting3(boss2);
                    }
                    if (boss2.blood <= 0) {
                        GameFrame gameFrame = DataStore.get("gameFrame");
                        gameFrame.fraction += 200000;
                        boss2 = null;
                        c = false;
                    }

                }
                if (boss2 ==null){
                    allover(g);
                }
            }
            plane.draw(g);
            shield.draw(g);
            for (Bullet bullet : bulletList) {
                bullet.draw(g);
            }
            next(g);
            increas(g);



            for (Bullet bullet : bulletList) {
                /*if(a){
                    bullet.collisionTesting2(boss);
                }*/

                bullet.collisionTesting(enemyPlanes);
            }
            for (EnemyBullet enemyBullet : enemyBullets) {
                enemyBullet.collisionTesting(plane);

            }
            for (EnemyPlane enemyPlane : enemyPlanes) {
                enemyPlane.collisionTesting(plane);

            }
            for (BossBullet bossBullet : bossBulletList) {
                bossBullet.collisionTesting(plane);

            }
            for (BossBullet2 bossBullet2 : bossBulletList2) {
                bossBullet2.collisionTesting(plane);

            }
            for (Prop prop : props) {
                prop.collisionTesting(plane);

            }
            for (Prop2 prop2 : props2) {
                prop2.collisionTesting(plane);
            }



        g.setFont(new Font("楷体",Font.BOLD,18));
            g.setColor(Color.black);
            g.drawString("得分:"+fraction, 640, 48);
            g.setColor(Color.red);
            g.drawString("HP:", 640, 68);
            g.drawRect(680, 60, 70, 10);
            g.setColor(Color.white);
            g.drawString("蓄能", 640, 88);
            g.drawRect(680, 80, 70, 10);
            g.setColor(Color.magenta);
            g.drawString("关卡："+Checkpoint, 640, 108);
            g.setColor(Color.cyan);
            g.drawString("等级："+rank, 640, 130);

            if (fraction>=15000 && fraction<30000){
                rank=2;
            }
            if(fraction>=35000){
                rank=3;
            }

        if (background.getSpeed()==0){
                g.setFont(new Font("楷体", Font.BOLD, 18));
                g.setColor(new Color(255, 6, 41));
                g.drawString("BHP：", 20, 60);
                g.drawRect(60, 50, 210, 10);
        }
        }
        if (!gameStart && !gameOver) {
            background.draw(g);
            g.drawImage(ImageMap.get("gameover"),
                    FrameConstant.FRAME_WIDTH/ 2 - ImageMap.get("gameover").getWidth(null) / 2,
                    FrameConstant.FRAME_HEIGHT / 2 - ImageMap.get("gameover").getHeight(null) / 2,
                    ImageMap.get("gameover").getWidth(null),
                    ImageMap.get("gameover").getHeight(null),
                    null);
        }
    }





    /**
     * 初始化窗口
     */
    public void init(){
        //设置尺寸
        setSize(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);

        //设置居中
        setLocationRelativeTo(null);

        enableInputMethods(false);

        setResizable(false);

        //添加窗口监听
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //添加键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                plane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);
            }
        });

        //添加鼠标监听器
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameStart = true;
                gameOver = false;
                plane.planeblood = 100;
            }
        });


        new Thread(){
            @Override
            public void run() {

                while (true) {
                    repaint();
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


        setVisible(true);


    }
    private Image offScreenImage = null;//初始化缓冲区

    //解决闪屏问题
    @Override
    public void update(Graphics g)
    {
        if(offScreenImage == null)
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);     //新建一个图像缓存空间,这里图像大小为800*600
        Graphics gImage = offScreenImage.getGraphics();  //把它的画笔拿过来,给gImage保存着
        paint(gImage);                                   //将要画的东西画到图像缓存空间去
        g.drawImage(offScreenImage, 0, 0, null);         //然后一次性显示出来
    }
}
