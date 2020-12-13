package com.myself.tank.net;

import com.myself.tank.*;

import java.io.*;
import java.util.UUID;

public class TankDieMsg extends Msg {

    private UUID id;

    private UUID bulletId;


    public TankDieMsg(){
    }

    public TankDieMsg(UUID id,UUID bulletId){
        this.id = id;
        this.bulletId = bulletId;
    }


    @Override
    public void handler() {
        System.out.println("we got a tank die:" + id);
        System.out.println("and my tank is:" + TankFrame.INSTANCE.getMyTrank().getId());
        Tank tt = TankFrame.INSTANCE.findByUUID(id);
        System.out.println("i found a tank with this id:" + tt);

        Bullet b = TankFrame.INSTANCE.findBulletByUUID(bulletId);
        if(b != null) {
            b.die();
        }

        if(this.id.equals(TankFrame.INSTANCE.getMyTrank().getId())) {
            TankFrame.INSTANCE.getMyTrank().die();
        } else {

            Tank t = TankFrame.INSTANCE.findByUUID(id);
            if(t != null) {
                t.die();
            }
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
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.writeLong(bulletId.getMostSignificantBits());
            dos.writeLong(bulletId.getLeastSignificantBits());
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
        return MsgType.TankDieMsg;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new ByteArrayInputStream(bytes));
            this.id = new UUID(dis.readLong(),dis.readLong());
            this.bulletId = new UUID(dis.readLong(),dis.readLong());
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
