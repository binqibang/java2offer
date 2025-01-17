package concurrency.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        // 创建一个固定大小的线程池，可控制并发的线程数，超出的线程会在队列中等待
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        Runnable task = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.info(e.getMessage());
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " is running.");
        };
        // 执行任务的方法有两种:submit 和 execute
        threadPool.submit(task);
        threadPool.execute(task);
        // 超出的线程会在队列中等待
        threadPool.execute(task);
        threadPool.shutdown();
    }
}
