package com.yunliao.server.handler.im.account;

import com.yunliao.server.handler.Message;
import com.yunliao.server.handler.MessageHandler;
import com.yunliao.server.handler.im.chat.ruting.RuterTableLocal;
import com.yunliao.server.handler.im.UserSession;

/**
 * @author peter
 * @version V1.0 创建时间：18/2/1
 *          Copyright 2018 by PreTang
 */
public class MessageHandlerRegister extends MessageHandler{
    /**
     * 用户注册
     * @param message
     * @throws Exception
     */
    public void process(Message message) throws Exception {
        System.out.println("注册消息："+new String(message.getBody()));
        UserSession userSession = new UserSession();
        String registerKey =new String(message.getBody());
        userSession.setUserId(registerKey);
        userSession.setServerIp("127.0.0.1");
        userSession.setServerPort(9000);
        userSession.setChanelId(message.getFromChanel());

        //@todo 注册成功后，账户写入数据库、集群环境下广播自己位置、返回客户端信息（据具体协议）
        RuterTableLocal.userSessioinMap.put(registerKey, userSession);
        RuterTableLocal.chanelSessioinMap.put(message.getFromChanel(), userSession);
        message = null;
    }
}