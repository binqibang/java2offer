package concurrency.problem;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNumLetter {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static int state = 0;
    private static int num = 1;
    private static char letter = 'A';

    private static void printNum() {
        while (true) {
            lock.lock();
            try {
                while (state % 2 != 0) {
                    condition.await();
                }
                System.out.print(num + " ");
                num++;
                state++;
                Thread.sleep(500);
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private static void printLetter() {
        while (true) {
            lock.lock();
            try {
                while (state % 2 != 1) {
                    condition.await();
                }
                System.out.print(letter + " ");
                letter = letter != 'Z' ? (char) (letter + 1) : 'A';
                state++;
                Thread.sleep(500);
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(PrintNumLetter::printNum).start();
        new Thread(PrintNumLetter::printLetter).start();
    }
}
