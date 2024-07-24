package com.moveon.boot.learn.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestHandler
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/19 10:46
 * @Version 1.0
 */
@Slf4j
@Component
public class TestHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.debug("receive data: {}", msg.toString());
    }
}
