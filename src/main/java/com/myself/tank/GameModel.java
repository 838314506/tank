package com.myself.tank;

import com.myself.tank.cor.BulletTankCollider;
import com.myself.tank.cor.Collider;
import com.myself.tank.cor.TankTankCollider;
import com.myself.tank.mediator.GameObject;
import com.myself.tank.resourceMge.PropertyMgr;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 即是门面又是调停者
 */
public class GameModel {

    Tank myTrank = new Tank(300, 300, Dir.DOWN, this,Group.GOOD);

//    List<Tank> foeTranks = new ArrayList<>();
//
//    List<Bullet> bullets = new ArrayList<>();
//
//    List<Explored> explored = new ArrayList<>();

    private List<GameObject> objects = new ArrayList<>();

    private Collider collider = new BulletTankCollider();
    private Collider collider2 = new TankTankCollider();

    public GameModel(){
        int initTankCount = Integer.valueOf((String) PropertyMgr.get("tank.initTankCount"));

        //初始化坦克
        for (int i = 0;i < initTankCount;i ++){
            add(new Tank(i * 80 + 50, Tank.WIDTH / 2, Dir.DOWN, this,Group.BAD));
        }
    }

    public void add(GameObject go){
        this.objects.add(go);
    }

    public void remove(GameObject go){
        this.objects.remove(go);
    }


    public void paint(Graphics a) {
        myTrank.paint(a);
//        for (int i = 0;i < foeTranks.size();i ++){
//            foeTranks.get(i).paint(a);
//        }
//        a.drawString("子弹的数量："+ bullets.size(),10,60);
//        for (int i = 0;i < bullets.size();i ++) {
//            bullets.get(i).paint(a);
//        }

        for (int i = 0;i < objects.size();i ++){
            this.objects.get(i).paint(a);
        }

        for (int i = 0;i < objects.size();i ++){
            for (int j = i + 1;j < objects.size();j ++){
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                collider.collider(o1,o2);
                collider2.collider(o1,o2);
            }
        }

//        for (int i = 0;i < bullets.size();i ++){
//            for (int j = 0;j < foeTranks.size();j ++){
//                bullets.get(i).collide(foeTranks.get(j));
//            }
//        }
//
//        for (int i = 0;i < explored.size();i ++){
//            explored.get(i).paint(a);
//        }
    }
}
