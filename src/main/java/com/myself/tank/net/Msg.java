package com.myself.tank.net;

public abstract class Msg {
    public abstract void handler();
    public abstract byte[] toBytes();
    public abstract MsgType getMsgType();
    public abstract void parse(byte[] bytes);
}
