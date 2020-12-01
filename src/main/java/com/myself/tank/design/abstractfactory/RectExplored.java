package com.myself.tank.design.abstractfactory;

import com.myself.tank.ResourceMgr;
import com.myself.tank.TankFrame;

import java.awt.*;

public class RectExplored extends BaseExplored {

    public static final int WIDTH = ResourceMgr.explored[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explored[0].getHeight();
    public int x,y;
    private TankFrame tf = null;
    //private boolean live = true;
    private int step = 0;

    public RectExplored(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    @Override
    public void paint(Graphics a) {
        Color c = a.getColor();
        a.setColor(Color.RED);
        a.fillRect(x,y,10*step,10*step);
        step++;
//        a.drawImage(ResourceMgr.explored[step++],x,y,null);
        if (step >= 5){
            tf.explored.remove(this);
        }
        a.setColor(c);
    }
}
