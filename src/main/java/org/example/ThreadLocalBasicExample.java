package org.example;

import java.util.concurrent.TimeUnit;

/**
 * 介紹基本原理
 */
public class ThreadLocalBasicExample {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        Thread thread1 = createThread(threadLocal, "thread1");
        Thread thread2 = createThread(threadLocal, "thread2");

        thread1.start();
        thread2.start();
    }

    private static Thread createThread(ThreadLocal<String> threadLocal, String threadValue) {
        return new Thread(() -> {
            /*
             不同的 thread 有各自的 `ThreadLocal.ThreadLocalMap<ThreadLocal, 值>`，
             `thread1` 在自己的 ThreadLocalMap 物件存了 key 是`threadLocal`的值`thread1`，
             `thread2` 在自己的 ThreadLocalMap 物件存了 key 是`threadLocal`的值`thread2`，
             雖然 key 都是同個物件，但因為是獨立的兩個 map，所以存的值也是各自獨立。
             */
            threadLocal.set(threadValue);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(threadLocal.get());
        });
    }
}