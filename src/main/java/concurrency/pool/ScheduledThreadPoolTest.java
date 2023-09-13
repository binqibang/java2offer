package concurrency.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        // 创建一个可以执行延迟任务的线程池
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
        // 添加定时执行任务，1s 后执行
        log.info("Adding task.");
        threadPool.schedule(() -> {
            log.info("Task is running.");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }, 1, TimeUnit.SECONDS);
        threadPool.shutdown();
    }
}
