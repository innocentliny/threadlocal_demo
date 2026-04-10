package org.example;

import java.util.concurrent.TimeUnit;

public class InheritableThreadLocalExample {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        Thread thread1 = new Thread(() -> {
            threadLocal.set("threadLocalValue");
            inheritableThreadLocal.set("inheritableThreadLocalValue");

            System.out.println("thread 1: " + threadLocal.get());
            System.out.println("thread 1: " + inheritableThreadLocal.get());

            Thread childThread = new Thread(() -> {
                System.out.println("thread 1's childThread: " + threadLocal.get());
                System.out.println("thread 1's childThread: " + inheritableThreadLocal.get());
            });
            childThread.start();
        });

        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread 2: " + threadLocal.get());
            System.out.println("thread 2: " + inheritableThreadLocal.get());
        });

        try {
            thread1.start();
            thread2.start();
        } finally {
            threadLocal.remove();
            inheritableThreadLocal.remove();
        }
    }
}
