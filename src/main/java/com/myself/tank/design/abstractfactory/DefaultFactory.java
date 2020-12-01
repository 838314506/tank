package com.myself.tank.design.abstractfactory;

import com.myself.tank.*;

public class DefaultFactory extends AbstractFactory{
    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new Tank(x,y,dir,tf,group);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new Bullet(x,y,dir,tf,group);
    }

    @Override
    public BaseExplored createExplored(int x,int y,TankFrame tf) {
        return new Explored(x,y,tf);
    }
}
