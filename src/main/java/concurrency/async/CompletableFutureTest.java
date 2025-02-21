package concurrency.async;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {
    public static void main(String[] args) {

        // 创建一个可能抛出异常的异步任务
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.5) {
                throw new RuntimeException("任务失败");
            }
            return "任务成功";
        });

        // 使用 exceptionally 处理异常
        CompletableFuture<String> handledFuture = future.exceptionally(ex -> {
            System.out.println("捕获到异常: " + ex.getMessage());
            return "备用值";
        });

        // 获取结果
        handledFuture.thenAccept(result -> System.out.println("最终结果: " + result)).join();
    }
}
