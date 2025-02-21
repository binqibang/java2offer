package concurrency.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {

    public MyThreadPool(int corePoolSize, int maxPoolSize, BlockingQueue<Runnable> workQueue, long timeout, TimeUnit timeUnit) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.workQueue = workQueue;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    private int corePoolSize;
    private int maxPoolSize;
    private BlockingQueue<Runnable> workQueue;
    private long timeout;
    private TimeUnit timeUnit;


    private List<Thread> coreThreadList = new ArrayList<>();
    private List<Thread> supportThreadList = new ArrayList<>();


    private final Runnable coreTask = () -> {
        while (true) {
            try {
                Runnable command = workQueue.take();
                command.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    };

    private final Runnable supportTask = () -> {
        while (true) {
            try {
                Runnable command = workQueue.poll(timeout, timeUnit);
                if (command == null) {
                    break;
                }
                command.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("support thread " + Thread.currentThread().getName() + " ended");
    };

    public void execute(Runnable command) {
        if (coreThreadList.size() < corePoolSize) {
            Thread thread = new Thread(coreTask);
            coreThreadList.add(thread);
            thread.start();
        }
        if (workQueue.offer(command)) {
            return;
        }
        if (coreThreadList.size() + supportThreadList.size() < maxPoolSize) {
            Thread thread = new Thread(supportTask);
            supportThreadList.add(thread);
            thread.start();
        }
        if (!workQueue.offer(command)) {
            throw new RuntimeException("thread pool full");
        }
    }

}
