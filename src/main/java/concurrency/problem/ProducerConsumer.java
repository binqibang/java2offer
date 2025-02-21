package concurrency.problem;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    private static final int CAPACITY = 5;
    private static final Queue<Integer> RESOURCE = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(() -> {
            while(true) {
                synchronized (RESOURCE) {
                    // 缓冲区已满
                    while (RESOURCE.size() == CAPACITY) {
                        System.out.println("当前队列已满");
                        // 唤醒消费者进程
                        RESOURCE.notify();
                        try {
                            RESOURCE.wait();
                        } catch (InterruptedException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    RESOURCE.offer(1);
                    RESOURCE.notify();
                    System.out.println("生产者生产一条任务，当前队列长度为" + RESOURCE.size());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            while (true) {
                synchronized (RESOURCE) {
                    while (RESOURCE.isEmpty()) {
                        System.out.println("当前队列为空");
                        // 唤醒生产者进程
                        RESOURCE.notify();
                        try {
                            RESOURCE.wait();
                        } catch (InterruptedException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    RESOURCE.poll();
                    RESOURCE.notify();
                    System.out.println("消费者消费一条任务，当前队列长度为" + RESOURCE.size());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }, "Consumer");

        producer.start();
        Thread.sleep(500);
        consumer.start();

    }
}
