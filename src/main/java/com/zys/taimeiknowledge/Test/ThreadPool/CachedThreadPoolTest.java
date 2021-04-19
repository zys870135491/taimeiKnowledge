package com.zys.taimeiknowledge.Test.ThreadPool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yisong.zhang
 * @since 2021/4/15 17:19
 * 缓存线程池。
 * 没有核心线程，普通线程数量为Integer.MAX_VALUE（可以理解为无限），
 * 线程闲置60s后回收，任务队列使用SynchronousQueue这种无容量的同步队列。适用于任务量大但耗时低的场景。
 */
public class CachedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool ();
        for(int i =0 ;i<20;i++){
            Thread thread = new Thread(() -> {

                System.out.println(new Date() + " ->" + Thread.currentThread().getName());
            });
            executorService.execute(thread);
        }
        executorService.shutdown();
    }
}
