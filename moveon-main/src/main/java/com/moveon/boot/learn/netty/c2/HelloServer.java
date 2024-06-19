package com.moveon.boot.learn.netty.c2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @ClassName HelloServer
 * @Description TODO
 * @Author huangzh
 * @Date 2024/6/12 16:14
 * @Version 1.0
 */
public class HelloServer {

    public static void main(String[] args) {

        // 1.启动器，负责组装netty组件，启动服务器
        new ServerBootstrap()
                // 2.BossEventLoop，WorkerEventLoop（selector，thread），group 组
                .group(new NioEventLoopGroup())
                // 3.选择服务器的ServerSocketChannel实现
                .channel(NioServerSocketChannel.class)
                // 4.boss负责处理连接 worker（child）负责读写，决定了worker能执行那些操作（handler）
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    //5.channel代表呵客户端进行数据读写的通道 Initializer初始化，负责添加别的handler
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        //6.添加具体handler
                        nioSocketChannel.pipeline().addLast(new StringDecoder());// 将ByteBuf转换为字符串
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() { // 自定义handler
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println(msg); //打印上一步转换好的字符串
                            }
                        });
                    }
                })
                //7。绑定监听端口
                .bind(8085);
    }
}
