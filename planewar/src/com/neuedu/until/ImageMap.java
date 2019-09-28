package com.neuedu.until;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {
    private static final Map<String,Image> map = new HashMap<>();

    static {
        map.put("bg01",ImageUntil.getimage("com\\neuedu\\img\\bg\\bg1.jpg"));
        map.put("bg02",ImageUntil.getimage("com\\neuedu\\img\\bg\\bg.jpg"));

        map.put("my01",ImageUntil.getimage("com\\neuedu\\img\\plane\\my_2.png"));

        map.put("bt01",ImageUntil.getimage("com\\neuedu\\img\\Bullet\\myplane01_bullet.png"));
        map.put("bt02",ImageUntil.getimage("com\\neuedu\\img\\Bullet\\BULLET_PET_06.png"));
        map.put("bt03",ImageUntil.getimage("com\\neuedu\\img\\Bullet\\BULLET_LIGHTNING.png"));

        map.put("ep01",ImageUntil.getimage("com\\neuedu\\img\\EnemyPlane\\enemyplane_01.png"));
        map.put("ep02",ImageUntil.getimage("com\\neuedu\\img\\EnemyPlane\\enemy_04.png"));
        map.put("ep03",ImageUntil.getimage("com\\neuedu\\img\\EnemyPlane\\ep_2.png"));
        map.put("ep04",ImageUntil.getimage("com\\neuedu\\img\\EnemyPlane\\ep_3.png"));

        map.put("eb01",ImageUntil.getimage("com\\neuedu\\img\\EnemyBullet\\e1.png"));
        map.put("eb02",ImageUntil.getimage("com\\neuedu\\img\\EnemyBullet\\enemyBulletBig.png"));

        map.put("bs01",ImageUntil.getimage("com\\neuedu\\img\\Boss\\boss_A_01.png"));
        map.put("bs02",ImageUntil.getimage("com\\neuedu\\img\\Boss\\boss_A_02.png"));
        map.put("bs03",ImageUntil.getimage("com\\neuedu\\img\\Boss\\boss_A_03.png"));
        map.put("bs04",ImageUntil.getimage("com\\neuedu\\img\\Boss\\boss_A_04.png"));
        map.put("bs05",ImageUntil.getimage("com\\neuedu\\img\\Boss\\boss_A_05.png"));
        map.put("bs06",ImageUntil.getimage("com\\neuedu\\img\\Boss\\boss_A_06.png"));
        map.put("bs07",ImageUntil.getimage("com\\neuedu\\img\\Boss\\boss_A_07.png"));
        map.put("bs08",ImageUntil.getimage("com\\neuedu\\img\\Boss\\boss_A_08.png"));
        map.put("bs09",ImageUntil.getimage("com\\neuedu\\img\\Boss\\boss_A_09.png"));

        map.put("bss01",ImageUntil.getimage("com\\neuedu\\img\\Boss\\BOSS01_1.png"));
        map.put("bss02",ImageUntil.getimage("com\\neuedu\\img\\Boss\\BOSS01_2.png"));
        map.put("bss03",ImageUntil.getimage("com\\neuedu\\img\\Boss\\BOSS01_3.png"));
        map.put("bss04",ImageUntil.getimage("com\\neuedu\\img\\Boss\\BOSS01_4.png"));
        map.put("bss05",ImageUntil.getimage("com\\neuedu\\img\\Boss\\BOSS01_5.png"));
        map.put("bss06",ImageUntil.getimage("com\\neuedu\\img\\Boss\\BOSS01_6.png"));
        map.put("bss07",ImageUntil.getimage("com\\neuedu\\img\\Boss\\BOSS01_7.png"));
        map.put("bss08",ImageUntil.getimage("com\\neuedu\\img\\Boss\\BOSS01_8.png"));

        map.put("bb01",ImageUntil.getimage("com\\neuedu\\img\\BossBullet\\boss01_03.png"));
        map.put("bb02",ImageUntil.getimage("com\\neuedu\\img\\BossBullet\\BULLET_CHAR_DESTINA_CHANGE_01.png"));

        map.put("pp01",ImageUntil.getimage("com\\neuedu\\img\\Prop\\addHP1.png"));
        map.put("pp02",ImageUntil.getimage("com\\neuedu\\img\\Prop\\addHP2.png"));
        map.put("pp03",ImageUntil.getimage("com\\neuedu\\img\\Prop\\addHP3.png"));
        map.put("pp04",ImageUntil.getimage("com\\neuedu\\img\\Prop\\addHP4.png"));

        map.put("pp201",ImageUntil.getimage("com\\neuedu\\img\\Prop\\adddefense1.png"));
        map.put("pp202",ImageUntil.getimage("com\\neuedu\\img\\Prop\\adddefense2.png"));
        map.put("pp203",ImageUntil.getimage("com\\neuedu\\img\\Prop\\adddefense3.png"));
        map.put("pp204",ImageUntil.getimage("com\\neuedu\\img\\Prop\\adddefense4.png"));

        map.put("sd01",ImageUntil.getimage("com\\neuedu\\img\\Prop\\shield.png"));


        map.put("start",ImageUntil.getimage("com\\neuedu\\img\\start.png"));

        map.put("next",ImageUntil.getimage("com\\neuedu\\img\\next.png"));

        map.put("increase",ImageUntil.getimage("com\\neuedu\\img\\increase.png"));

        map.put("gameover",ImageUntil.getimage("com\\neuedu\\img\\Resurrection.png"));

        map.put("allover",ImageUntil.getimage("com\\neuedu\\img\\over.png"));






    }

    public static Image get(String key){
        return map.get(key);
    }
}
