package com.myself.tank.net;

import com.myself.tank.Bullet;
import com.myself.tank.Dir;
import com.myself.tank.Group;
import com.myself.tank.TankFrame;

import java.io.*;
import java.util.UUID;

public class BulletNew extends Msg {

    private UUID playerID;
    private UUID id;
    private int x,y;
    private Dir dir;
    private Group group;

    public BulletNew(){}

    public BulletNew(Bullet b){
        this.id = b.getId();
        this.x = b.getX();
        this.y = b.getY();
        this.dir = b.getDir();
        this.group = b.getGroup();
        this.playerID = b.getPlayerID();
    }

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

    @Override
    public void handler() {
        if (this.playerID.equals(TankFrame.INSTANCE.getMyTrank().getId())){
            return;
        }
        Bullet bullet = new Bullet( x, y, dir, TankFrame.INSTANCE,group, this.playerID);
        bullet.setId(this.id);
        TankFrame.INSTANCE.addBullet(bullet);
//        Client.INSTANCE.send(new BulletNew(TankFrame.INSTANCE.findBulletByUUID(this.getPlayerID())));
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream bao = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            bao = new ByteArrayOutputStream();
            dos = new DataOutputStream(bao);
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeInt(group.ordinal());
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.writeLong(playerID.getMostSignificantBits());
            dos.writeLong(playerID.getLeastSignificantBits());
            dos.flush();
            bytes = bao.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bao != null) {
                    bao.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (dos != null) {
                    bao.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.BulletNew;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = null;

        try {
            dis = new DataInputStream(new ByteArrayInputStream(bytes));
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.group = Group.values()[dis.readInt()];
            this.id = new UUID(dis.readLong(),dis.readLong());
            this.playerID = new UUID(dis.readLong(),dis.readLong());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
