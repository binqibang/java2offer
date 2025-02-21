package concurrency.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {

    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName() + " is started");
            Thread.sleep((long) (10000 * Math.random()));
            return "response1";
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable task1 = new MyCallable();
        Callable task2 = () -> {
            System.out.println(Thread.currentThread().getName() + " is started");
            Thread.sleep((long) (10000 * Math.random()));
            return "response2";
        };

        FutureTask<String> ft1 = new FutureTask<>(task1);
        FutureTask<String> ft2 = new FutureTask<>(task2){
            @Override
            protected void done() {
                System.out.println(Thread.currentThread().getName() + " is done");
            }
        };

        Thread t1 = new Thread(ft1, "Thread-1");
        Thread t2 = new Thread(ft2, "Thread-2");
        t1.start();
        t2.start();
        System.out.println(t1.getName() + "'result: " + ft1.get());
        System.out.println(t2.getName() + "'result: " + ft2.get());

        for (int i = 0; i < 100; i++) {
            System.out.println("Main thread is busy");
            Thread.sleep((long) (5000 * Math.random()));
        }
    }
}
