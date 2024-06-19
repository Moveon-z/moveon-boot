package com.moveon.boot.learn.netty.c3;

import io.netty.channel.DefaultEventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestEventLoop
 * @Description TODO
 * @Author huangzh
 * @Date 2024/6/14 16:40
 * @Version 1.0
 */
@Slf4j
public class TestEventLoop {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup(2);
//        EventLoopGroup group = new DefaultEventLoop();
        // 2.获取下一个事件循环对象
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());

        // 3.执行普通任务
        /*group.next().submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.debug("ok");
        });
        log.debug("main");*/

        // 4. 执行定时任务
        group.next().scheduleAtFixedRate(() -> {
            log.debug("ok");
        }, 0, 1, TimeUnit.SECONDS);

        log.debug("main");
    }
}
