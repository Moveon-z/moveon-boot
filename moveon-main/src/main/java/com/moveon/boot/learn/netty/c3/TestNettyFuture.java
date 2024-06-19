package com.moveon.boot.learn.netty.c3;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @ClassName TestNettyFuture
 * @Description TODO
 * @Author huangzh
 * @Date 2024/6/19 15:36
 * @Version 1.0
 */
@Slf4j
public class TestNettyFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop eventLoop = group.next();

        Future<Integer> future = eventLoop.submit(() -> {
            Thread.sleep(1000);
            return 70;
        });
//        log.debug("等待结果");
//        log.debug("结果是{}", future.get());

        future.addListener(future1 -> log.debug("接收结果：{}", future1.getNow()));
    }
}
