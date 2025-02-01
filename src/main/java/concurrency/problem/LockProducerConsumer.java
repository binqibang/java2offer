package concurrency.problem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockProducerConsumer {
    private static final int CAPACITY = 5;
    private static final Queue<Integer> resource = new LinkedList<>();
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(() -> {
            while(true) {
                lock.lock();
                try {
                    while (resource.size() == CAPACITY) {
                        System.out.println("当前队列已满");
                        condition.await();
                    }
                    resource.offer(1);
                    condition.signal();
                    System.out.println("生产者生产一条任务，当前队列长度为" + resource.size());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                } finally {
                    lock.unlock();
                }
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    while (resource.isEmpty()) {
                        System.out.println("当前队列为空");
                        condition.await();
                    }
                    resource.poll();
                    condition.signal();
                    System.out.println("消费者消费一条任务，当前队列长度为" + resource.size());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                } finally {
                    lock.unlock();
                }
            }
        }, "Consumer");

        producer.start();
        Thread.sleep(500);
        consumer.start();
    }
}
