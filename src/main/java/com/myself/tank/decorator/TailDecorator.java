package com.myself.tank.decorator;

import com.myself.tank.mediator.GameObject;

import java.awt.*;

public class TailDecorator extends GoDecorator{

    public TailDecorator(GameObject go){
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        go.paint(g);
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawLine(go.x, go.y, go.y + getWidth(), go.x + getHeight());
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return super.go.WIDGH;
    }

    @Override
    public int getHeight() {
        return super.go.HEIGHT;
    }
}
