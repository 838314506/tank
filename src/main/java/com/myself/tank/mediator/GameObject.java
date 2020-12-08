package com.myself.tank.mediator;

import java.awt.*;
import java.io.Serializable;

public abstract class GameObject implements Serializable {

    public int x,y;

    public int WIDGH,HEIGHT;

    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();

}
