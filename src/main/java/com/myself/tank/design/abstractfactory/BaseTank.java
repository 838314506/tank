package com.myself.tank.design.abstractfactory;

import com.myself.tank.Group;

import java.awt.*;

public abstract class BaseTank {

    public Group group = Group.BAD;
    public Rectangle rect = new Rectangle();

    public abstract void paint(Graphics a);

    public abstract void die();

    public Group getGroup() {
        return group;
    }

    public Rectangle getRect() {
        return rect;
    }
    
}
