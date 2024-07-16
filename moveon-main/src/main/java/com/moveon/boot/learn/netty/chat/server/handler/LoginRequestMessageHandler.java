package com.moveon.boot.learn.netty.chat.server.handler;

import com.moveon.boot.learn.netty.chat.message.LoginRequestMessage;
import com.moveon.boot.learn.netty.chat.message.LoginResponseMessage;
import com.moveon.boot.learn.netty.chat.server.session.SessionFactory;
import com.moveon.boot.learn.netty.chat.service.LoginServiceImpl;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName LoginRequestMessageHandler
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/15 10:14
 * @Version 1.0
 */
@ChannelHandler.Sharable
public class LoginRequestMessageHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {
    private final LoginServiceImpl loginService;

    public LoginRequestMessageHandler(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
        String username = msg.getUsername();
        String password = msg.getPassword();
        boolean login = loginService.login(username, password);
        LoginResponseMessage message;
        if (login) {
            SessionFactory.getSession().bind(ctx.channel(), username);
            message = new LoginResponseMessage(true, "登录成功");
        } else {
            message = new LoginResponseMessage(true, "用户名或密码不正确");
        }
        ctx.writeAndFlush(message);
    }
}
