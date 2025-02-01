package concurrency.problem;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintInTurn {
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final Condition CONDITION = LOCK.newCondition();
    private static int num = 0;
    private static final int THREAD_COUNT = 10;

    private static void print(int id) {
        while (true) {
            LOCK.lock();
            try {
                if (num > 100) {
                    break;
                }
                if (num % THREAD_COUNT == id) {
                    System.out.println(Thread.currentThread().getName() + ": " + num);
                    num++;
                    Thread.sleep(500);
                    CONDITION.signalAll();
                } else {
                    CONDITION.await();
                }
            } catch(InterruptedException e) {
                System.err.println(e.getMessage());
            } finally {
                LOCK.unlock();
            }
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            int id = i;
            new Thread(() -> print(id), "Thread" + i).start();
        }
    }
}
