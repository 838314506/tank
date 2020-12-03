package com.myself.tank.cor;

import com.myself.tank.Bullet;
import com.myself.tank.Tank;
import com.myself.tank.mediator.GameObject;

public class TankTankCollider implements Collider {
    @Override
    public void collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank){
            Tank t1 = (Tank)o1;
            Tank t2 = (Tank)o2;
            t1.rollback();
//            t2.rollback();
        }else {
            return;
        }
    }
}
