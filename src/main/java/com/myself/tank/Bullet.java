package com.myself.tank;

import com.myself.tank.mediator.GameObject;

import java.awt.*;

public class Bullet extends GameObject {
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private static final int SPEED = 10;
    public int x,y;
    private Dir dir;
    private Group group = Group.BAD;

    private Rectangle rect = new Rectangle();

    private boolean live = true;

    public Bullet(int x, int y, Dir dir, Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics a){
        if (!live){
            GameModel.getInstance().remove(this);
        }
        switch (dir) {
            case DOWN:
                a.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case UP:
                a.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case LEFT:
                a.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                a.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            default:
                break;
        }
        move();
    }

    public void move(){
        switch (dir){
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
        rect.x = this.x;
        rect.y = this.y;
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) live = false;

    }

    public void die() {
        this.live = false;
    }

    public Group getGroup() {
        return group;
    }

    public Rectangle getRect() {
        return rect;
    }
}
