package com.myself.tank.decorator;

import com.myself.tank.mediator.GameObject;

import java.awt.*;

public abstract class GoDecorator extends GameObject {

    protected GameObject go;

    public GoDecorator(GameObject go){
        this.go = go;
    }

    public abstract void paint(Graphics g);

}
