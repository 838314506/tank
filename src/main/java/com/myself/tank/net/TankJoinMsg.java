package com.myself.tank.net;

import com.myself.tank.Dir;
import com.myself.tank.Group;
import com.myself.tank.Tank;
import com.myself.tank.TankFrame;

import java.io.*;
import java.util.UUID;

public class TankJoinMsg extends Msg{

    public int x, y;
    public Dir dir;
    public Group group;
    public boolean moving;
    public UUID id;

    public TankJoinMsg() {
    }

    public TankJoinMsg(Tank t) {
        this.x = t.getX();
        this.y = t.getY();
        this.dir = t.getDir();
        this.group = t.getGroup();
        this.id = t.getId();
        this.moving = t.isMoving();
    }

    public TankJoinMsg(int x, int y, Dir dir, Group group, boolean moving, UUID id) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.moving = moving;
        this.id = id;
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
            dos.writeBoolean(moving);
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
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
        return MsgType.TankJoinMsg;
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
            this.moving = dis.readBoolean();
            this.id = new UUID(dis.readLong(),dis.readLong());
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

    @Override
    public String toString() {
        return "TankJoinMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", group=" + group +
                ", moving=" + moving +
                ", id=" + id +
                '}';
    }

    @Override
    public void handler() {
        if (this.id.equals(TankFrame.INSTANCE.getMyTrank().getId())||
                TankFrame.INSTANCE.findByUUID(this.id) != null){
            return;
        }
        Tank t = new Tank(this);
        TankFrame.INSTANCE.put(t);
        Client.INSTANCE.send(new TankJoinMsg(TankFrame.INSTANCE.getMyTrank()));
    }
}
