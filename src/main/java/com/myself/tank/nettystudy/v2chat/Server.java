package com.myself.tank.nettystudy.v2chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

import static com.myself.tank.nettystudy.v2chat.Server.clients;

public class Server {
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void main(String[] arg) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workers = new NioEventLoopGroup(2);

        ServerBootstrap b = new ServerBootstrap();
        try {
            ChannelFuture f = b.group(bossGroup,workers)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerChannelInitializer2())
                    .bind(8999)
                    .sync();
            System.out.println("server start");

            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workers.shutdownGracefully();
        }
    }
}

class ServerHandler2 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = null;
        try {
            buf = (ByteBuf) msg;
            byte[] bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(), bytes);
            String revMsg = new String(bytes);
            if (revMsg.equals("_bye_")){
                clients.remove(ctx.channel());
                System.out.println("客户端请求下线");
                ctx.close();
            }
            clients.writeAndFlush(buf);
        } finally {
//            if(buf != null) ReferenceCountUtil.release(buf);
//            System.out.println(buf.refCnt());
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        clients.remove(ctx.channel());
        ctx.close();
        System.out.println("exception");
    }
}

class ServerChannelInitializer2 extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast(new ServerHandler2());
    }
}