package com.myself.tank.design.abstractfactory;

import com.myself.tank.*;
import com.myself.tank.resourceMge.PropertyMgr;

import java.awt.*;
import java.util.Random;

public class RectTank extends BaseTank {
    //坦克宽度、高度
    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();
    //坦克速度
    private static final int SPEED = 5;
    private boolean moving = true;
    private boolean live = true;
    int x, y;

    //方向
    Dir dir = Dir.DOWN;
    TankFrame tf = null;

    private Random random = new Random();


    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public RectTank(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    //画坦克
    public void paint(Graphics a) {
        if (!live){
            tf.foeTranks.remove(this);
        }

        Color c = a.getColor();
        a.setColor(group == Group.GOOD ? Color.RED : Color.BLUE);
        a.fillRect(x, y, 40, 40);
        a.setColor(c);
        move();
        move();
    }

    //移动
    public void move() {
        if (!moving) return;
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
        if (this.group == Group.BAD && random.nextInt(100) > 95) this.fire(DefaultStrategy.getInstance());
        if (this.group == Group.BAD  && random.nextInt(100) > 95) randomDir();

        //边界检查
        boundsCheck();

        //更新矩形边界，用于边界检查
        rect.x = this.x;
        rect.y = this.y;
    }

    //边界检查
    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH - RectTank.WIDTH) x = TankFrame.GAME_WIDTH - RectTank.WIDTH;
        if (this.y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - RectTank.HEIGHT - 2;
    }

    //随机方向
    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    //开火
    public void fire(FireStrategy fireStrategy) {

//        if (this.group == Group.GOOD){
//            String goodFS = (String) PropertyMgr.get("goodFS");
//            try {
//                fireStrategy = (FireStrategy)Class.forName(goodFS).newInstance();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        fireStrategy.fire(this);

        int bx = this.x + RectTank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = this.y + RectTank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs){
            tf.af.createBullet(bx, by, dir, this.tf,this.group);
//            new RectBullet(bx, by, dir, t.tf,t.group);
        }
//        for (int i = 0;i < tf.bullets.size();i ++){
//            Bullet bullet = tf.bullets.get(i);
//            if (x < bullet.x && 2 * x > bullet.x && y == bullet.y){
//                live = false;
//            }
//
//        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //死亡
    public void die() {
        this.live = false;
        int eX = this.x + RectTank.WIDTH / 2 - Explored.WIDTH / 2;
        int eY = this.y + RectTank.HEIGHT / 2 - Explored.HEIGHT / 2;
//        Explored e = new Explored(eX, eY, tf);
        tf.explored.add(tf.af.createExplored(eX,eY,tf));
    }
}
