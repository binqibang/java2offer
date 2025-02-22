package concurrency.problem;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class BlockingQueueConsumerProducer {
    private static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            while (true) {
                try {
                    queue.put(1);
                    System.out.println("生产者生产一条任务，当前队列长度为" + queue.size());
                    Thread.sleep(new Random().nextInt(1000) + 100);
                } catch (InterruptedException e) {
                    log.warn(e.getMessage());
                }
            }
        }, "Producer");

        Thread b = new Thread(() -> {
            while (true) {
                try {
                    queue.take();
                    System.out.println("消费者消费一条任务，当前队列长度为" + queue.size());
                    Thread.sleep(new Random().nextInt(1000) + 100);
                } catch (InterruptedException e) {
                    log.warn(e.getMessage());
                }
            }
        }, "Consumer");

        a.start();
        Thread.sleep(500);
        b.start();
    }
}
