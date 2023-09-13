package concurrency.safety;

import java.text.SimpleDateFormat;

public class ThreadLocalExample implements Runnable{

    // SimpleDateFormat 不是线程安全的，所以每个线程都要有自己独立的副本
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExample obj = new ThreadLocalExample();
        for(int i = 0 ; i < 10; i++){
            Thread t = new Thread(obj, "" + i);
            Thread.sleep(1000);
            t.start();
        }
    }

    @Override
    public void run() {
        System.out.println("Thread-"+Thread.currentThread().getName()+" default Formatter = "+formatter.get().toPattern());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // formatter pattern is changed here by thread, but it won't reflect to other threads
        formatter.set(new SimpleDateFormat());
        System.out.println("Thread-"+Thread.currentThread().getName()+" formatter = "+formatter.get().toPattern());
    }

}


