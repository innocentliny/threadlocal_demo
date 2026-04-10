package org.example;

/**
 * ThreadLocal 的 initial value 用法。
 * 看 <code>ThreadLocal</code>原始碼，似乎 lazy 到 get() 時，才有機會取得初始值。
 */
public class ThreadLocalInitialValueExample {
    private static final ThreadLocal<Object> threadLocal1 = new  ThreadLocal<>(){
        @Override
        protected Object initialValue() {
            return new Object();
        }
    };
    private static final ThreadLocal<Object> threadLocal2 = ThreadLocal.withInitial(Object::new);

    public static void main(String[] args) {
        Thread thread1 = createThread("thread1");
        Thread thread2 = createThread("thread2");

        try {
            thread1.start();
            thread2.start();
        } finally {
            threadLocal1.remove();
            threadLocal2.remove();
        }
    }

    private static Thread createThread(String threadName) {
        return new Thread(() -> {
            System.out.println(threadName + "'s threadLocal1: " + threadLocal1.get());
            System.out.println(threadName + "'s threadLocal2: " + threadLocal2.get());
        }, threadName);
    }
}
