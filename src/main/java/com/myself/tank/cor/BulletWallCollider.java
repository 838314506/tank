package com.myself.tank.cor;

import com.myself.tank.Bullet;
import com.myself.tank.Tank;
import com.myself.tank.Wall;
import com.myself.tank.mediator.GameObject;

public class BulletWallCollider implements Collider{
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall){
            Bullet b = (Bullet)o1;
            Wall w = (Wall)o2;
            if (b.getRect().intersects(w.getRect())){
                b.die();
            }
        }else if (o1 instanceof Wall && o2 instanceof Bullet){
            collider(o2,o1);
        }
        return true;
    }
}
