package com.myself.tank.fireObserver;

import java.io.Serializable;

public  interface TankFireObserver extends Serializable {

    void fireHandler(TankFireEvent event);
}
