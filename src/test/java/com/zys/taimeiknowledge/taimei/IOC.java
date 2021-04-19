package com.zys.taimeiknowledge.taimei;

public class IOC {

    public static void main(String[] args) {
        A a = new A();
        a.getB();
        B b = new B();
    }

}

class A{
    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}

class B{
    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
