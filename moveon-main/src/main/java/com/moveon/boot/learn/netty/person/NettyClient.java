package com.moveon.boot.learn.netty.person;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moveon.boot.learn.netty.person.entity.User;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;
import java.util.Scanner;

/**
 * @ClassName NettyClient
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/19 14:16
 * @Version 1.0
 */
@Slf4j
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        log.debug("初始化连接");
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                log.info("连接成功");
                                User user = new User("moveon", "123", "黄圳河");
                                Gson gson = new Gson();
                                String json = gson.toJson(user);
                                ctx.writeAndFlush(json);
                            }
                        });
                    }
                });
        Channel channel = bootstrap.connect("localhost", 8089).sync().channel();
        channel.closeFuture().sync();
    }
}
