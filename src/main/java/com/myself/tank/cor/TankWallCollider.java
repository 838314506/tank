package com.myself.tank.cor;

import com.myself.tank.Bullet;
import com.myself.tank.Tank;
import com.myself.tank.Wall;
import com.myself.tank.mediator.GameObject;

public class TankWallCollider implements Collider{
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall){
            Tank t = (Tank)o1;
            Wall w = (Wall)o2;
            if (t.getRect().intersects(w.getRect())){
                t.rollback();
            }
        }else if (o1 instanceof Wall && o2 instanceof Tank){
            collider(o2,o1);
        }
        return true;
    }
}
