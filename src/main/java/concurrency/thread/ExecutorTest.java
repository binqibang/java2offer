package concurrency.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {
    static final int COUNT = 3;
    static final int STEPS = 5;

    public static void main(String[] args) {
        // executor 同时执行多个线程
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < COUNT; i++) {
            executor.execute(() -> {
                for (int j = 0; j < STEPS; j++) {
                    try {
                        System.out.println("thread" + j);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executor.shutdown();
        // executor.shutdownNow(); // 引发中断异常
    }
}
