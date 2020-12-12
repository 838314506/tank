package com.myself.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 8) return;

        byteBuf.markReaderIndex();

        MsgType mt = MsgType.values()[byteBuf.readInt()];
        int msgLength = byteBuf.readInt();
        //如果小于去掉头和数据长度的字节数，说明数据还未传输完成
        if (byteBuf.readableBytes() < msgLength) {
            byteBuf.resetReaderIndex();
            return;
        }

        byte[] bytes = new byte[msgLength];
        byteBuf.readBytes(bytes);
        System.out.println(mt.toString());
        Msg msg = null;
//        Msg msg = (Msg) Class.forName("com.myself.tank.net." + mt.toString()).getDeclaredConstructor().newInstance();
        switch (mt) {
            case TankJoinMsg:
                msg = new TankJoinMsg();
                break;
            case TankMoveMsg:
                msg = new TankMoveMsg();
                break;
            case TankStopMsg:
                msg = new TankStopMsg();
                break;
            case BulletNew:
                msg = new BulletNew();
                break;
            default:
                break;
        }
        msg.parse(bytes);
        list.add(msg);
    }
}
