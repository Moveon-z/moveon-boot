package com.moveon.boot.learn.netty.chat.server.handler;

import com.moveon.boot.learn.netty.chat.message.GroupCreateRequestMessage;
import com.moveon.boot.learn.netty.chat.message.GroupCreateResponseMessage;
import com.moveon.boot.learn.netty.chat.server.session.Group;
import com.moveon.boot.learn.netty.chat.server.session.GroupSession;
import com.moveon.boot.learn.netty.chat.server.session.GroupSessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;
import java.util.Set;

/**
 * @ClassName GroupCreateRequestMessageHandler
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/15 10:27
 * @Version 1.0
 */
public class GroupCreateRequestMessageHandler extends SimpleChannelInboundHandler<GroupCreateRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupCreateRequestMessage msg) throws Exception {
        String groupName = msg.getGroupName();
        Set<String> members = msg.getMembers();
        GroupSession groupSession = GroupSessionFactory.getGroupSession();
        Group group = groupSession.createGroup(groupName, members);
        if (group == null) {
            // 发送拉群消息
            List<Channel> channels = groupSession.getMembersChannel(groupName);
            for (Channel channel : channels) {
                channel.writeAndFlush(new GroupCreateResponseMessage(true, "您已被拉入" + groupName));
            }
            ctx.writeAndFlush(new GroupCreateResponseMessage(true, groupName + "创建成功"));
        } else {
            ctx.writeAndFlush(new GroupCreateResponseMessage(false, groupName + "已经存在"));
        }
    }
}
