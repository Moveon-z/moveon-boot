package com.moveon.boot.learn.netty.c3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @ClassName TestFuture
 * @Description TODO
 * @Author huangzh
 * @Date 2024/6/19 15:30
 * @Version 1.0
 */
@Slf4j
public class TestFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 1。线程池
        ExecutorService service = Executors.newFixedThreadPool(2);
        // 2. 提交任务
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("执行计算");
                Thread.sleep(1000);
                return 50;
            }
        });

        // 3.主线程通过future获取结果
        log.debug("等待结果");
        log.debug("结果是{}", future.get());
    }
}
