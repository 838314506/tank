package com.myself.tank;

import com.myself.tank.mediator.GameObject;

import java.awt.*;

public class Explored extends GameObject {
    public static final int WIDTH = ResourceMgr.explored[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explored[0].getHeight();
    public int x,y;
    private GameModel gm = null;
    //private boolean live = true;
    private int step = 0;

    public Explored(int x, int y,  GameModel gm){
        this.x = x;
        this.y = y;
        this.gm = gm;
    }

    public void paint(Graphics a){
        a.drawImage(ResourceMgr.explored[step++],x,y,null);
        if (step >= ResourceMgr.explored.length){
            gm.remove(this);
        }
    }

}
