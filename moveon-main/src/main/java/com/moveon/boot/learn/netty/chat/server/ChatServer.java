package com.moveon.boot.learn.netty.chat.server;

import com.moveon.boot.learn.netty.chat.protocol.MessageCodecSharable;
import com.moveon.boot.learn.netty.chat.protocol.ProtocolFrameDecoder;
import com.moveon.boot.learn.netty.chat.server.handler.ChatRequestMessageHandler;
import com.moveon.boot.learn.netty.chat.server.handler.GroupChatRequestMessageHandler;
import com.moveon.boot.learn.netty.chat.server.handler.GroupCreateRequestMessageHandler;
import com.moveon.boot.learn.netty.chat.server.handler.LoginRequestMessageHandler;
import com.moveon.boot.learn.netty.chat.service.LoginServiceImpl;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ChatServer
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/1 15:31
 * @Version 1.0
 */
@Slf4j
public class ChatServer {

    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        LoggingHandler loggingHandler = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable messageCodecSharable = new MessageCodecSharable();
        LoginServiceImpl loginService = new LoginServiceImpl();
        LoginRequestMessageHandler loginHandler = new LoginRequestMessageHandler(loginService);
        ChatRequestMessageHandler chatHandler = new ChatRequestMessageHandler();
        GroupCreateRequestMessageHandler groupCreateHandler = new GroupCreateRequestMessageHandler();
        GroupChatRequestMessageHandler groupChatHandler = new GroupChatRequestMessageHandler();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.group(boss, worker);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // 用来判断是不是 读空闲时间过长 或 写空闲时间过长
                    // 5秒内如果没有收到channel的数据，会触发一个事件
                    ch.pipeline().addLast(new IdleStateHandler(5, 0, 0));
                    ch.pipeline().addLast(new ChannelDuplexHandler() {
                        // 用来触发特殊事件
                        @Override
                        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                            IdleStateEvent event = (IdleStateEvent) evt;
                            if (event.state() == IdleState.READER_IDLE) {
                                log.debug("已经5s没有读到数据了");
                            }
                        }
                    });
                    ch.pipeline().addLast(new ProtocolFrameDecoder());
                    ch.pipeline().addLast(loggingHandler);
                    ch.pipeline().addLast(messageCodecSharable);
                    ch.pipeline().addLast(loginHandler);
                    ch.pipeline().addLast(chatHandler);
                    ch.pipeline().addLast(groupCreateHandler);
                    ch.pipeline().addLast(groupChatHandler);
                }
            });
            Channel channel = serverBootstrap.bind(8080).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("server error", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
