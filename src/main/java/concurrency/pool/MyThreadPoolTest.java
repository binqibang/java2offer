package concurrency.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MyThreadPoolTest {
    public static void main(String[] args) {
        int corePoolSize = 2;
        int maximumPoolSize = 5;
        long timeout = 1L;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
        MyThreadPool myThreadPool = new MyThreadPool(
                corePoolSize,
                maximumPoolSize,
                workQueue,
                timeout,
                timeUnit
        );
        final int TASK_COUNT = 4;
        for (int i = 0; i < TASK_COUNT; i++) {
            int taskId = i;
            myThreadPool.execute(() -> {
                try {
                    System.out.printf("task-%d started\n", taskId);
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.printf("task-%d finished\n", taskId);
                } catch (InterruptedException e) {
                    log.warn(e.getMessage());
                }
            });
        }
    }
}
