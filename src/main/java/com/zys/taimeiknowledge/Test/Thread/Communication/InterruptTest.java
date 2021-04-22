package com.zys.taimeiknowledge.Test.Thread.Communication;

/**
 * @author yisong.zhang
 * @since 2021/4/21 15:19
 * interrupt 打断线程
 */
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t2 = new Thread(()->{
            while(true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                // 根据打断状态来决定是否中单该线程
                if(interrupted) {
                    System.out.println(" 打断状态: "+interrupted);
                /*    //可以恢复中断状态,恢复后仍然可以继续打印 开心
                    Thread.currentThread().interrupted();*/
                    break;
                }
                System.out.println("开心");
            }
        }, "t2");
        t2.start();
        Thread.sleep(2000);
        t2.interrupt();
    }

}
