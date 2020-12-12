package com.myself.tank.net;

public class TankDieMsg extends Msg {


    @Override
    public void handler() {

    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public MsgType getMsgType() {
        return null;
    }

    @Override
    public void parse(byte[] bytes) {

    }
}
