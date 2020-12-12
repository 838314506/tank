package com.myself.tank;

import com.myself.tank.net.MsgDecoder;
import com.myself.tank.net.MsgEncoder;
import com.myself.tank.net.MsgType;
import com.myself.tank.net.TankJoinMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TankJoinMsgTest2 {

    @Test
    public void testDecoder(){
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        byte[] bytes = new TankJoinMsg(10, 10, Dir.DOWN, Group.GOOD, true, id).toBytes();
        ch.pipeline().addLast(new MsgDecoder());

        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(MsgType.TankJoinMsg.ordinal());
        buffer.writeInt(bytes.length);
        buffer.writeBytes(bytes);

        ch.writeInbound(buffer.duplicate());

        TankJoinMsg msg = (TankJoinMsg)ch.readInbound();
        assertEquals(10, msg.x);
        assertEquals(10, msg.y);
        assertEquals(Dir.DOWN, msg.dir);
        assertEquals(Group.GOOD, msg.group);
        assertEquals(true, msg.moving);
        assertEquals(id, msg.id);
    }

    @Test
    public void testEncoder(){
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        ch.pipeline().addLast(new MsgEncoder());
        TankJoinMsg msg = new TankJoinMsg(10, 10, Dir.DOWN, Group.GOOD, true, id);
        ch.writeOutbound(msg);

        ByteBuf byteBuf = (ByteBuf)ch.readOutbound();
        MsgType msgType = MsgType.values()[byteBuf.readInt()];
        assertEquals(msgType,MsgType.TankJoinMsg);
        assertEquals(33,byteBuf.readInt());

        int x = byteBuf.readInt();
        int y = byteBuf.readInt();
        Dir dir = Dir.values()[byteBuf.readInt()];
        Group group = Group.values()[byteBuf.readInt()];
        boolean moving = byteBuf.readBoolean();
        UUID ids = new UUID(byteBuf.readLong(),byteBuf.readLong());

        assertEquals(10, x);
        assertEquals(10, y);
        assertEquals(Dir.DOWN, dir);
        assertEquals(Group.GOOD, group);
        assertEquals(true, moving);
        assertEquals(id, ids);

    }
}
