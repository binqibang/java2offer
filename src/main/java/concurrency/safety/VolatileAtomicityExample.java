package concurrency.safety;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileAtomicityExample {
    public static volatile int NUM = 0;

    public void increase() {
        NUM++;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        VolatileAtomicityExample example = new VolatileAtomicityExample();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(() -> {
                for (int j = 0; j < 500; j++) {
                    example.increase();
                }
            });
        }
        Thread.sleep(1500);
        System.out.println(NUM);
        threadPool.shutdown();
    }
}
