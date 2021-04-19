package com.zys.taimeiknowledge.Test.ThreadPool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yisong.zhang
 * @since 2021/4/15 17:11
 * 时线程池。
 * 指定核心线程数量，普通线程数量无限，线程执行完任务立即回收，
 * 任务队列为延时阻塞队列。这是一个比较特别的线程池，适用于执行定时或周期性的任务。
 */
public class ScheduledThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newScheduledThreadPool(5);
        for(int i =0 ;i<10;i++){
            Thread thread = new Thread(() -> {
                System.out.println(new Date() + " ->" + Thread.currentThread().getName());
            });
            ((ScheduledExecutorService) executorService).schedule (thread,2000,TimeUnit.MILLISECONDS);// 延迟2s后执行任务
            //((ScheduledExecutorService) executorService).scheduleAtFixedRate(thread,50,2000,TimeUnit.MILLISECONDS); // 延迟50ms后、每隔2000ms执行任务

        }
        executorService.shutdown();
    }

}
