package com.zys.taimeiknowledge.taimei;

public class ThreadLocalTest {

    public static void main(String[] args) {
        User user = new User();
        for(int i=0;i<5;i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    user.setName(Thread.currentThread().getName()+"的内容");
                    user.setId(Thread.currentThread().getName()+"的ID");
                    System.out.println("--------------------------");
                    System.out.println(Thread.currentThread().getName()+":"+user.getName()+"--->"+user.getId());
                }
            });
            t.setName("线程"+i);
            t.start();
        }
    }

}

class User{

   final ThreadLocal t1 =  new ThreadLocal();
   final ThreadLocal t2 =  new ThreadLocal();

    private String name;
    private String id;

    public String getName() {
        return t1.get().toString();
    }

    public void setName(String name) {
        t1.set(name);
    }

    public String getId() {
        return t2.get().toString();
    }

    public void setId(String id) {
        t2.set(id);
    }
}
