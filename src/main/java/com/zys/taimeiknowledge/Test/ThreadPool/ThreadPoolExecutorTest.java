package com.zys.taimeiknowledge.Test.ThreadPool;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yisong.zhang
 * @since 2021/4/15 16:28
 * 线程池
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(6,100,0, TimeUnit.SECONDS,new SynchronousQueue());

        for(int i = 0 ;i<10;i++){
            Thread.sleep(i*200);
            Thread thread = new Thread(() -> {
                System.out.println(new Date() + "--->" + Thread.currentThread().getName());
            });
            threadPoolExecutor.execute(thread);
        }
        threadPoolExecutor.shutdown();
    }

}
