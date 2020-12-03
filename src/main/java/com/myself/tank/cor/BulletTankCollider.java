package com.myself.tank.cor;

import com.myself.tank.Bullet;
import com.myself.tank.Tank;
import com.myself.tank.mediator.GameObject;

public class BulletTankCollider implements Collider{
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank){
            Bullet b = (Bullet)o1;
            Tank t = (Tank)o2;
            if (b.getGroup() == t.getGroup()) return true;
            if (b.getRect().intersects(t.getRect())){
                b.die();
                t.die();
                return false;
            }
        }else if (o1 instanceof Tank && o2 instanceof Bullet){
            collider(o2,o1);
        }
        return true;
    }
}
