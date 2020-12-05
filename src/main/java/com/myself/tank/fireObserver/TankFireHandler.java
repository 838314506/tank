package com.myself.tank.fireObserver;

import com.myself.tank.Bullet;
import com.myself.tank.GameModel;
import com.myself.tank.Tank;

public class TankFireHandler implements TankFireObserver{
    @Override
    public void fireHandler(TankFireEvent event) {
        Tank tank = event.getTank();
        tank.fire();
    }
}
