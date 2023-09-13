package concurrency.problem;

import org.junit.jupiter.api.Test;

class ReadWriteLockListTest {
    @Test
    void test() {
        ReadWriteLockList<Integer> list1 = new ReadWriteLockList<>();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list1.add(i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("list1: " + list1.get(i));
            }
        });

        thread1.start();
        thread2.start();
    }
}