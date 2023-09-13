package concurrency;

import concurrency.safety.LockExample;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class LockTest {

    @Test
    void testReentrantLock() {
        LockExample lockExample = new LockExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(lockExample::count);
        executorService.execute(lockExample::count);
    }

}