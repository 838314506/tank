package com.myself.tank;

import com.myself.tank.net.BulletNew;
import com.myself.tank.net.Client;
import com.myself.tank.net.TankDieMsg;

import java.awt.*;
import java.util.UUID;

public class Bullet {
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private static final int SPEED = 2;
    private UUID id = UUID.randomUUID();

    private UUID playerID;

    public UUID getPlayerID() {
        return playerID;
    }

    public void setPlayerID(UUID playerID) {
        this.playerID = playerID;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int x,y;

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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    private Dir dir;
    private TankFrame tf = null;
    private Group group = Group.BAD;

    private Rectangle rect = new Rectangle();

    private boolean live = true;

    public Bullet(BulletNew bn){
        this.x = bn.getX();
        this.y = bn.getY();
        this.dir = bn.getDir();
        this.id = bn.getId();
        this.group = bn.getGroup();
        this.playerID = bn.getPlayerID();

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public Bullet(int x, int y, Dir dir, TankFrame tf, Group group,UUID playerID){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        this.playerID = playerID;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics a){
        if (!live){
            tf.bullets.remove(this);
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

    public void collide(Tank tank) {
        if(this.playerID.equals(tank.getId())) return;
        //System.out.println("bullet rect:" + this.rect);
        //System.out.println("tank rect:" + tank.rect);
        if(this.live && tank.isLive() && this.rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            Client.INSTANCE.send(new TankDieMsg(tank.getId(),this.id));
        }

    }

    public void die() {
        this.live = false;
    }
}
