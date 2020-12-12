package com.myself.tank.net;

import com.myself.tank.Dir;
import com.myself.tank.Group;
import com.myself.tank.Tank;
import com.myself.tank.TankFrame;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

import java.util.UUID;

public class Client {

    public static final Client INSTANCE = new Client();
    Channel channel = null;

    private Client(){

    }

    public void connect() {
        EventLoopGroup elg = new NioEventLoopGroup(1);

        Bootstrap b = new Bootstrap();

        try {
            ChannelFuture f = b.group(elg)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer2())
                    .connect("localhost", 8999);
            f.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(!future.isSuccess()) {
                        System.out.println("not connected!");
                    } else {
                        System.out.println("connected!");
                        channel =  future.channel();
                    }
                }
            });
            f.sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            elg.shutdownGracefully();
        }

    }

    public void send(Msg msg) {
        channel.writeAndFlush(msg);
    }

    public void closeConnect() {
        //this.send("_bye_");
    }
}

class ClientHandler2 extends SimpleChannelInboundHandler<Msg>{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMyTrank()));
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exception");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext chc, Msg msg) throws Exception {
        msg.handler();
    }
}

class ClientChannelInitializer2 extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast(new MsgEncoder()).addLast(new MsgDecoder()).addLast(new ClientHandler2());
    }
}
