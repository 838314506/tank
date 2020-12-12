package com.myself.tank;

import com.myself.tank.net.MsgDecoder;
import com.myself.tank.net.MsgEncoder;
import com.myself.tank.net.TankJoinMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TankJoinMsgTest {

    @Test
    public void testEncoder(){
        EmbeddedChannel ec = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankJoinMsg tjm = new TankJoinMsg(10, 10, Dir.DOWN, Group.GOOD, true, id);
        ec.pipeline().addLast(new MsgEncoder());
        ec.writeOutbound(tjm);

        ByteBuf bf = (ByteBuf)ec.readOutbound();
        int x = bf.readInt();
        int y = bf.readInt();
        Dir dir = Dir.values()[bf.readInt()];
        Group g = Group.values()[bf.readInt()];
        boolean moving = bf.readBoolean();
        UUID id2 = new UUID(bf.readLong(),bf.readLong());

        assertEquals(10, x);
        assertEquals(10, y);
        assertEquals(Dir.DOWN, dir);
        assertEquals(Group.GOOD, g);
        assertEquals(true, moving);
        assertEquals(id, id2);

    }

    @Test
    public void testDecoder(){
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankJoinMsg tjm = new TankJoinMsg(10, 10, Dir.DOWN, Group.GOOD, true, id);
        ch.pipeline().addLast(new MsgDecoder());

        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(tjm.toBytes());

        ch.writeInbound(buffer.duplicate());

        TankJoinMsg msg = (TankJoinMsg)ch.readInbound();
        assertEquals(10, msg.x);
        assertEquals(10, msg.y);
        assertEquals(Dir.DOWN, msg.dir);
        assertEquals(Group.GOOD, msg.group);
        assertEquals(true, msg.moving);
        assertEquals(id, msg.id);
    }
}
