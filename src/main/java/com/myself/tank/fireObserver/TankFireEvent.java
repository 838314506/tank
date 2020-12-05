package com.myself.tank.fireObserver;

import com.myself.tank.Tank;

public class TankFireEvent {

    Tank tank;

    public TankFireEvent(Tank tank){
        this.tank = tank;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }
}
