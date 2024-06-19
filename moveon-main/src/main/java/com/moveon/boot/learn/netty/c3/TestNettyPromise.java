package com.moveon.boot.learn.netty.c3;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;

import java.util.concurrent.ExecutionException;

/**
 * @ClassName TestNettyPromise
 * @Description TODO
 * @Author huangzh
 * @Date 2024/6/19 15:48
 * @Version 1.0
 */
public class TestNettyPromise {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1.准备EventLoop对象
        EventLoop eventLoop = new NioEventLoopGroup().next();

        //2.可以主动创建promise结果容器
        DefaultPromise<Object> promise = new DefaultPromise<>(eventLoop);

        new Thread(() -> {
            //3.任意一个线程执行计算，计算完毕后向promise填充结果
            System.out.println("开始计算");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            promise.setSuccess(80);
        }).start();

        // 4.接受结果的线程
        System.out.println("等待结果");
        System.out.println("结果是" + promise.get());
    }
}
