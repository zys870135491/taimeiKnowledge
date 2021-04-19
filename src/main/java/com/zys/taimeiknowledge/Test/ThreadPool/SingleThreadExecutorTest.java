package com.zys.taimeiknowledge.Test.ThreadPool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yisong.zhang
 * @since 2021/4/15 17:10
 * 单线程线程池。
 * 特点是线程池中只有一个线程（核心线程），线程执行完任务立即回收，使用有界阻塞队列（容量未指定，使用默认值
 */
public class SingleThreadExecutorTest {

    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newSingleThreadExecutor();
        for(int i =0 ;i<10;i++){
            Thread thread = new Thread(() -> {
                System.out.println(new Date() + " ->" + Thread.currentThread().getName());
            });
            executorService.execute(thread);
        }
        executorService.shutdown();
    }

}
