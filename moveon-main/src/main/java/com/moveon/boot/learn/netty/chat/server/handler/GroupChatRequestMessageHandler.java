package com.moveon.boot.learn.netty.chat.server.handler;

import com.moveon.boot.learn.netty.chat.message.GroupChatRequestMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName GroupChatRequestMessageHandler
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/15 10:26
 * @Version 1.0
 */
@ChannelHandler.Sharable
public class GroupChatRequestMessageHandler extends SimpleChannelInboundHandler<GroupChatRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatRequestMessage msg) throws Exception {

    }
}
