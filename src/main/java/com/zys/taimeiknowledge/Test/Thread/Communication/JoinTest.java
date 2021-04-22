package com.zys.taimeiknowledge.Test.Thread.Communication;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author yisong.zhang
 * @since 2021/4/21 10:31
 * 线程通信--》join
 */
public class JoinTest {

    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Date() +":这是t1");
        });

        Thread b = new Thread(() -> {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Date() +":这是t2");
        });

        a.start();
        b.start();
    }



}
