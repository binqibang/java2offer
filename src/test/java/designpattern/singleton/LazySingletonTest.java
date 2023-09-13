package designpattern.singleton;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class LazySingletonTest {

    @Test
    void singleThreadTest() {
        var ins1 = LazySingleton.getUniqueInstance();
        var ins2 = LazySingleton.getUniqueInstance();
        System.out.println(ins1 == ins2);
    }

    @Test
    void multiThreadTest() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                var ins = LazySingleton.getUniqueInstance();
                System.out.println(ins.toString());
            });
        }
    }

}