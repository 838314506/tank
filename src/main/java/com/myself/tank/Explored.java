package com.myself.tank;

import java.awt.*;
import java.util.UUID;

public class Explored {
    public static final int WIDTH = ResourceMgr.explored[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explored[0].getHeight();
    public int x,y;
    private UUID id = UUID.randomUUID();
    private TankFrame tf = null;
    //private boolean live = true;
    private int step = 0;

    public Explored(int x, int y,  TankFrame tf){
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics a){
        a.drawImage(ResourceMgr.explored[step++],x,y,null);
        if (step >= ResourceMgr.explored.length){
            tf.explored.remove(this);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
