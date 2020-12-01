package com.myself.tank.design.abstractfactory;

import com.myself.tank.Dir;
import com.myself.tank.Group;
import com.myself.tank.TankFrame;

public abstract class AbstractFactory {

    public abstract BaseTank createTank(int x, int y, Dir dir, TankFrame tf, Group group);
    public abstract BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group);
    public abstract BaseExplored createExplored(int x,int y,TankFrame tf);
}
