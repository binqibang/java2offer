package concurrency.problem;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class PrintABC {
    private static final Lock LOCK = new ReentrantLock();
    private static int idx;
    private static Condition c1 = LOCK.newCondition();
    private static Condition c2 = LOCK.newCondition();
    private static Condition c3 = LOCK.newCondition();

    private static void print(int state, Condition curr, Condition next) {
        while (true) {
            LOCK.lock();
            try {
                while (idx % 3 != state) {
                    curr.await();
                }
                idx++;
                System.out.print(Thread.currentThread().getName() + " ");
                Thread.sleep(500);
                next.signal();
            } catch (InterruptedException e) {
                log.warn(e.getMessage());
            } finally {
                LOCK.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> print(0, c1, c2), "A").start();
        new Thread(() -> print(1, c2, c3), "B").start();
        new Thread(() -> print(2, c3, c1), "C").start();
    }
}
