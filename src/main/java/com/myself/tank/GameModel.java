package com.myself.tank;

import com.myself.tank.resourceMge.PropertyMgr;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank myTrank = new Tank(300, 300, Dir.DOWN, this,Group.GOOD);

    List<Tank> foeTranks = new ArrayList<>();

    List<Bullet> bullets = new ArrayList<>();

    List<Explored> explored = new ArrayList<>();

    public GameModel(){
        int initTankCount = Integer.valueOf((String) PropertyMgr.get("tank.initTankCount"));

        //初始化坦克
        for (int i = 0;i < initTankCount;i ++){
            foeTranks.add(new Tank(i * 80 + 50, Tank.WIDTH / 2, Dir.DOWN, this,Group.BAD));
        }
    }

    public void paint(Graphics a) {
        myTrank.paint(a);
        for (int i = 0;i < foeTranks.size();i ++){
            foeTranks.get(i).paint(a);
        }
        a.drawString("子弹的数量："+ bullets.size(),10,60);
        for (int i = 0;i < bullets.size();i ++) {
            bullets.get(i).paint(a);
        }

        for (int i = 0;i < bullets.size();i ++){
            for (int j = 0;j < foeTranks.size();j ++){
                bullets.get(i).collide(foeTranks.get(j));
            }
        }

        for (int i = 0;i < explored.size();i ++){
            explored.get(i).paint(a);
        }
    }
}
