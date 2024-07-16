package com.moveon.boot.learn.netty.chat.server.handler;

import com.moveon.boot.learn.netty.chat.message.ChatRequestMessage;
import com.moveon.boot.learn.netty.chat.message.ChatResponseMessage;
import com.moveon.boot.learn.netty.chat.server.session.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName ChatRequestMessageHandler
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/15 10:15
 * @Version 1.0
 */
@ChannelHandler.Sharable
public class ChatRequestMessageHandler extends SimpleChannelInboundHandler<ChatRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatRequestMessage msg) throws Exception {
        String to = msg.getTo();
        Channel channel = SessionFactory.getSession().getChannel(to);
        if (channel != null) {
            // 在线
            channel.writeAndFlush(new ChatResponseMessage(msg.getFrom(), msg.getContent()));
        } else {
            // 不在线
            ctx.writeAndFlush(new ChatResponseMessage(false, "对方用户不存在或不在线"));
        }
    }
}
