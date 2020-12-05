package com.myself.tank.decorator;

import com.myself.tank.mediator.GameObject;

import java.awt.*;

public class RectDecorator extends GoDecorator{

    public RectDecorator(GameObject go){
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;

        go.paint(g);

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawRect(super.go.x, super.go.y, super.go.getWidth()+2, super.go.getHeight()+2);
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return go.WIDGH;
    }

    @Override
    public int getHeight() {
        return go.HEIGHT;
    }


}
