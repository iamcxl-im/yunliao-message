package com.yunliao.server.test;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandlerBak extends ChannelInboundHandlerAdapter {



    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        ByteBuf buf = (ByteBuf) msg;
        byte[] message = new byte[buf.readableBytes()];
        buf.readBytes(message);
        System.out.println("client received getData :" + new String(message));

    }

}