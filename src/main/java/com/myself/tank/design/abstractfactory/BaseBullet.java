package com.myself.tank.design.abstractfactory;

import com.myself.tank.Tank;

import java.awt.*;

public abstract class BaseBullet {

    public abstract void paint(Graphics a);

    public abstract void collide(BaseTank trank);
}
