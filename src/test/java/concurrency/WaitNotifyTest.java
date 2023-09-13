package concurrency;

import concurrency.thread.WaitNotifyExample;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WaitNotifyTest {

    @Test
    void testWaitNotify() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        WaitNotifyExample example = new WaitNotifyExample();
        executorService.execute(example::after);
        executorService.execute(example::before);
    }

}