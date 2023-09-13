package concurrency.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {

    static int STEPS = 10;

    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            for (int i = 0; i < STEPS; i++) {
                System.out.println("call1 " + i);
                Thread.sleep(200);
            }
            return "response1";
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable task1 = new MyCallable();
        Callable task2 = () -> {
            for (int i = 0; i < STEPS; i++) {
                System.out.println("call2 " + i);
                Thread.sleep(100);
            }
            return "response2";
        };

        FutureTask<String> ft1 = new FutureTask<>(task1), ft2 = new FutureTask<>(task2);

        Thread t1 = new Thread(ft1), t2 = new Thread(ft2);
        t1.start(); t2.start();
        System.out.println(t1.getState() + " " + ft1.get());
        System.out.println(t2.getState() + " " + ft2.get());
    }
}
