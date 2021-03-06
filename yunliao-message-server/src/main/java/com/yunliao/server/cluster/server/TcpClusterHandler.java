package com.yunliao.server.cluster.server;

import com.alibaba.fastjson.JSON;
import com.yunliao.server.cluster.server.handler.ClusterMessageHandler;
import com.yunliao.server.cluster.transport.message.ClusterMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;


public class TcpClusterHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger.getLogger(TcpClusterHandler.class);


    public void channelRead(ChannelHandlerContext ctx, Object obj) {
        try {
            ByteBuf buf = (ByteBuf) obj;
            byte[] message = new byte[buf.readableBytes()];
            buf.readBytes(message);
            String body = new String(message);
            System.out.println("集群消息:"+body);
            ClusterMessage clusterMessage = JSON.parseObject(body, ClusterMessage.class);
            ClusterMessageHandler.process(clusterMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
