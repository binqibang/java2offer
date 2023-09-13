package concurrency.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            try {
                // 执行完 sleep 方法后，线程处于限时等待状态
                // 中断它会引发异常，从而提前结束
                log.info("--- enter sleep ---");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.info("--- wake ---");
                e.printStackTrace();
            }
        };
        Thread t = new Thread(task);
        t.start();

        Thread.sleep(1000);
        log.info("--- interrupt ---");
        t.interrupt();

    }
}
