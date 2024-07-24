package com.moveon.boot.learn.netty.test.handle;

import com.moveon.boot.learn.netty.test.entity.User;
import com.moveon.boot.learn.netty.test.service.LoginService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginHandler extends SimpleChannelInboundHandler<User> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, User user) throws Exception {
        LoginService loginService = new LoginService();
        boolean login = loginService.login(user.getUsername(), user.getPassword());
        if (login) {
//            channelHandlerContext.writeAndFlush(new MessageResponse("登录成功"));
        }
    }
}
