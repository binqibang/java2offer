package concurrency;

import concurrency.safety.SynchronizedExample;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SynchronizedTest {
    @Test
    void synchronizedTest1() {
        SynchronizedExample e = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e::count);
        executorService.execute(e::count);
    }

    @Test
    void synchronizedTest2() {
        SynchronizedExample e1 = new SynchronizedExample(),
                            e2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e1::count);
        executorService.execute(e2::count);
    }

    @Test
    void synchronizedTest3() {
        SynchronizedExample e1 = new SynchronizedExample(),
                            e2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e1::count1);
        executorService.execute(e2::count1);
    }


}