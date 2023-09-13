package designpattern.singleton;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class HungrySingletonTest {
    @Test
    void singleThreadTest() {
        var ins1 = HungrySingleton.getUniqueInstance();
        var ins2 = HungrySingleton.getUniqueInstance();
        System.out.println(ins1 == ins2);
    }

    @Test
    void multiThreadTest() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                var ins = HungrySingleton.getUniqueInstance();
                System.out.println(ins.toString());
            });
        }
    }
}