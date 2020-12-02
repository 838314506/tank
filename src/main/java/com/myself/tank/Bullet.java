package com.myself.tank;

import java.awt.*;

public class Bullet {
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private static final int SPEED = 10;
    public int x,y;
    private Dir dir;
    private GameModel gm = null;
    private Group group = Group.BAD;

    private Rectangle rect = new Rectangle();

    private boolean live = true;

    public Bullet(int x, int y, Dir dir, GameModel gm, Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics a){
        if (!live){
            gm.bullets.remove(this);
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

    public void collide(Tank trank) {
        if (this.group == trank.group) return;

        if (this.rect.intersects(trank.rect)){
            this.die();
            trank.die();
        }
    }

    private void die() {
        this.live = false;
    }
}
