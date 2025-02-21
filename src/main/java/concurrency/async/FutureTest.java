package concurrency.async;

import java.util.concurrent.*;

public class FutureTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {
            Thread.sleep(2000);
            return "任务完成";
        });

        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println(e.getMessage());
        }

        executor.shutdown();
    }
}
