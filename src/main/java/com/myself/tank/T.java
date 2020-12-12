package com.myself.tank;

import com.myself.tank.net.Client;
import com.myself.tank.resourceMge.PropertyMgr;

public class T {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = TankFrame.INSTANCE;

        int initTankCount = Integer.valueOf((String) PropertyMgr.get("tank.initTankCount"));

//        //初始化坦克
//        for (int i = 0;i < initTankCount;i ++){
//            tf.foeTranks.add(new Tank(i * 80 + 50, Tank.WIDTH / 2, Dir.DOWN, tf,Group.BAD));
//        }
        new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tf.repaint();
            }
        }).start();

        Client.INSTANCE.connect();
    }
}
