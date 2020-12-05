package com.myself.tank;

import com.myself.tank.mediator.GameObject;

import java.awt.*;

public class Wall extends GameObject {
    public int w,h;
    private Rectangle rect = null;

    private boolean live = true;

    public Wall(int x, int y, int w,int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        rect = new Rectangle(x,y,w,h);
    }

    public void paint(Graphics a){

        Color color = a.getColor();
        a.setColor(Color.DARK_GRAY);
        a.fillRect(x,y,w,h);
        a.setColor(color);
    }


    public Rectangle getRect() {
        return rect;
    }

    @Override
    public int getWidth() {
        return w;
    }

    @Override
    public int getHeight() {
        return h;
    }
}
