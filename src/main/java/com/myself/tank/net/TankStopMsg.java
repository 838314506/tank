package com.myself.tank.net;

import com.myself.tank.Dir;
import com.myself.tank.Tank;
import com.myself.tank.TankFrame;

import java.io.*;
import java.util.UUID;

public class TankStopMsg extends Msg {

    private int x, y;
    private UUID id;

    public TankStopMsg() {
    }

    public TankStopMsg(Tank t) {
        this.x = t.getX();
        this.y = t.getY();
        this.id = t.getId();
    }

    public TankStopMsg(int x, int y, UUID id) {
        this.x = x;
        this.y = y;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public void handler() {
        if (this.getId().equals(TankFrame.INSTANCE.getMyTrank())) {
            return;
        }
        Tank t = TankFrame.INSTANCE.findByUUID(this.id);
        if (t != null) {
            t.setMoving(false);
            t.setX(this.getX());
            t.setY(this.getY());
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
        return MsgType.TankStopMsg;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = null;

        try {
            dis = new DataInputStream(new ByteArrayInputStream(bytes));
            this.x = dis.readInt();
            this.y = dis.readInt();
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
