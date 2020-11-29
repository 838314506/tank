package com.myself.tank;

public class T {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        //初始化坦克
        for (int i = 0;i < 5;i ++){
            tf.foeTranks.add(new Tank(i * 80 + 50, Tank.WIDTH / 2, Dir.DOWN, tf,Group.BAD));
        }
        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
