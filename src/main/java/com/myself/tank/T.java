package com.myself.tank;

import com.myself.tank.resourceMge.PropertyMgr;

public class T {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
