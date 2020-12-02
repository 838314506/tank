package com.myself.tank;

import java.awt.*;
import java.util.Random;

//坦克
public class Tank {
    //坦克宽度、高度
    static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    static final int HEIGHT = ResourceMgr.goodTankU.getHeight();
    //坦克速度
    private static final int SPEED = 5;
    private boolean moving = true;
    private boolean live = true;
    private int x, y;
    //方向
    private Dir dir = Dir.DOWN;
    private GameModel gm = null;

    public Group group = Group.BAD;

    private Random random = new Random();

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    Rectangle rect = new Rectangle();


    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Tank(int x, int y, Dir dir,GameModel gm, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gm = gm;
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
            gm.foeTranks.remove(this);
        }

        switch (dir) {
            case DOWN:
                a.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            case UP:
                a.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case LEFT:
                a.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                a.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            default:
                break;
        }
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
        if (this.group == Group.BAD && random.nextInt(100) > 95) this.fire();
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
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    //随机方向
    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    //开火
    public void fire() {
        int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        gm.bullets.add(new Bullet(bx, by, dir, gm,this.group));
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
        int eX = this.x + Tank.WIDTH / 2 - Explored.WIDTH / 2;
        int eY = this.y + Tank.HEIGHT / 2 - Explored.HEIGHT / 2;
        Explored e = new Explored(eX, eY, gm);
        gm.explored.add(e);
    }
}
