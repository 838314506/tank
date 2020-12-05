package com.myself.tank.mediator;

import java.awt.*;

public abstract class GameObject {

    public int x,y;

    public int WIDGH,HEIGHT;

    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();

}
