package com.myself.tank;

public class DefaultStrategy implements FireStrategy{

    private static volatile DefaultStrategy INSTANCE = null;

    private DefaultStrategy(){
    }

    public static DefaultStrategy getInstance(){
        if (INSTANCE == null){
            synchronized (DefaultStrategy.class){
                if (INSTANCE == null){
                    INSTANCE = new DefaultStrategy();
                }
            }
        }
        return INSTANCE;
    }

//    @Override
//    public void fire(Tank t) {
//        int bx = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
//        int by = t.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
//        new Bullet(bx, by, t.dir, t.tf,t.group);
//    }
}
