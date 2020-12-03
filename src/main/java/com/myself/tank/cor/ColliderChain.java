package com.myself.tank.cor;

import com.myself.tank.mediator.GameObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider{

    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain(){
        colliders.add(new BulletTankCollider());
        colliders.add( new TankTankCollider());
        colliders.add( new BulletWallCollider());
        colliders.add( new TankWallCollider());
    }

    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        for (int i = 0;i < colliders.size();i ++){
            Collider collider = colliders.get(i);
            if (!collider.collider(o1,o2)){
                return false;
            }
        }
        return true;
    }
}
