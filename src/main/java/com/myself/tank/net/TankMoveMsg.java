package com.myself.tank.net;

import com.myself.tank.Dir;
import com.myself.tank.Tank;
import com.myself.tank.TankFrame;

import java.io.*;
import java.util.UUID;

public class TankMoveMsg extends Msg {

    private int x,y;

    private Dir dir;

    private UUID id;

    public TankMoveMsg(){}

    public TankMoveMsg(Tank t){
        this.x = t.getX();
        this.y = t.getY();
        this.id = t.getId();
        this.dir = t.getDir();
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

    public Dir isDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public void handler() {
        if (this.id.equals(TankFrame.INSTANCE.getMyTrank().getId())){
            return;
        }

        Tank t = TankFrame.INSTANCE.findByUUID(this.id);
        if (t != null){
            t.setMoving(true);
            t.setX(this.x);
            t.setY(this.y);
            t.setDir(this.dir);
        }
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
        return MsgType.TankMoveMsg;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = null;

        try {
            dis = new DataInputStream(new ByteArrayInputStream(bytes));
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
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
}
