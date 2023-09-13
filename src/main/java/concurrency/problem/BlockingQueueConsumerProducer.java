package concurrency.problem;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueConsumerProducer {
    private static final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            while (true) {
                try {
                    queue.put(1);
                    System.out.println("生产者生产一条任务，当前队列长度为" + queue.size());
                    Thread.sleep(new Random().nextInt(1000) + 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
                    e.printStackTrace();
                }
            }
        }, "Consumer");

        a.start();
        Thread.sleep(500);
        b.start();
    }
}
