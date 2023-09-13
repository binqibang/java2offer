package concurrency.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ListTest {
    static class MultiThreadModifier implements Runnable {
        private final List<Integer> list;

        public MultiThreadModifier(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (int i = 0; i < 15; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                list.add(i);
                System.out.printf("%s add %d at the index of %d, now list's size is %d\n",
                        Thread.currentThread().getName(), i, list.size(), list.size());
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        // List 线程不安全
        List<Integer> list = new ArrayList<>();
        MultiThreadModifier a = new MultiThreadModifier(list),
                b = new MultiThreadModifier(list);
        Thread t1 = new Thread(a), t2 = new Thread(b);
        t1.start();
        t2.start();

        // Vector 线程安全
        List<Integer> vector = new Vector<>();
        MultiThreadModifier c = new MultiThreadModifier(vector),
                d = new MultiThreadModifier(vector);
        Thread t3 = new Thread(c), t4 = new Thread(d);
        t3.start();
        t4.start();
    }
}
