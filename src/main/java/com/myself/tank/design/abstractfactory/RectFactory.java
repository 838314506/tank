package com.myself.tank.design.abstractfactory;

import com.myself.tank.Dir;
import com.myself.tank.Group;
import com.myself.tank.TankFrame;

public class RectFactory extends AbstractFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new RectTank(x,y,dir,tf,group);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new RectBullet(x,y,dir,tf,group);
    }

    @Override
    public BaseExplored createExplored(int x, int y, TankFrame tf) {
        return new RectExplored(x,y,tf);
    }
}
