package concurrency.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ThreadPoolTest {

    static class MyThreadFactory implements ThreadFactory {
        private AtomicInteger count;
        private String prefix;

        public MyThreadFactory(String prefix) {
            super();
            count = new AtomicInteger(0);
            this.prefix = prefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, prefix + "-" + count.incrementAndGet());
        }
    }

    public static void main(String[] args) {
        int corePoolSize = 2;
        int maximumPoolSize = 5;
        long keepAliveTime = 2L;
        TimeUnit unit = TimeUnit.MINUTES;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadFactory threadFactory = new MyThreadFactory("my-thread");
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler
        );

        final int TASK_COUNT = 3;
        for (int i = 0; i < TASK_COUNT; i++) {
            int taskId = i + 1;
            threadPool.execute(() -> {
                try {
                    log.info("task-{} started", taskId);
                    Thread.sleep((long) (Math.random() * 5000));
                    log.info("task-{} finished", taskId);
                } catch (InterruptedException e) {
                    log.warn(e.getMessage());
                }
            });
        }

        threadPool.shutdown();
    }
}
