package com.myself.tank;

import com.myself.tank.mediator.GameObject;

import java.awt.*;

public class Explored extends GameObject {
    public static final int WIDTH = ResourceMgr.explored[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explored[0].getHeight();
    public int x,y;
    //private boolean live = true;
    private int step = 0;

    public Explored(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics a){
        a.drawImage(ResourceMgr.explored[step++],x,y,null);
        if (step >= ResourceMgr.explored.length){
            GameModel.getInstance().remove(this);
        }
    }

}
