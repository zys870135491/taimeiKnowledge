package com.zys.taimeiknowledge.Test.ThreadPool;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yisong.zhang
 * @since 2021/4/15 17:02
 * 线程池的一种 固定容量线程池
 *
 * 特点是最大线程数就是核心线程数，意味着线程池只能创建核心线程，keepAliveTime为0，
 * 即线程执行完任务立即回收。任务队列未指定容量，代表使用默认值Integer.MAX_VALUE。适用于需要控制并发线程的场景。
 */
public class FixedThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i =0 ;i<20;i++){
            Thread thread = new Thread(() -> {
                System.out.println(new Date() + " ->" + Thread.currentThread().getName());
            });
            executorService.execute(thread);
        }
        executorService.shutdown();
    }
}
