package com.zys.taimeiknowledge.Test.ThreadLocal;

import com.zys.taimeiknowledge.util.SystemContext;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yisong.zhang
 * @since 2021/4/16 13:53
 */
public class ThreadLocalTest {

    public static void main(String[] args) {

        ExecutorService executorService =
                Executors.newFixedThreadPool(5);

       /* for(int i =0 ;i<10;i++){
            int j = i;
            Thread thread = new Thread(() -> {
                Map<String, String> map = new HashMap<>();
                map.put("tm",j+"");
                SystemContext.setContextMap(map);
                System.out.println(Thread.currentThread().getName()+"---->"+SystemContext.get("tm"));
            });
            executorService.execute(thread);
        }*/
       /* Map<String, String> map = new HashMap<>();
        map.put("tm","111");
        SystemContext.setContextMap(map);*/
        SystemContext.put("tm","111");
        Map<String, String> contextMap = SystemContext.getContextMap();
        executorService.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"---->"+SystemContext.get("tm"));
            }
        });

        // 跨线程赋值ThreadLocal(其实就是把ThreadLocal再次赋值到新的线程里)
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // 开启了新的线程，main函数的ThreadLocal值拿不到，只能再次赋值
                SystemContext.setContextMap(contextMap);
                System.out.println(Thread.currentThread().getName()+"---->"+SystemContext.get("tm"));
            }
        });


        // main函数的ThreadLocal
        System.out.println(Thread.currentThread().getName()+"---->"+SystemContext.get("tm"));
        executorService.shutdown();
    }

}
