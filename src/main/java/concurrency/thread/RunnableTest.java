package concurrency.thread;

/**
 * Implements {@code Runnable} interface to start a thread.
 * @date 2023/1/11
 * @reference Ch.12, Core Java I (Twelfth Edition)
 */
public class RunnableTest {
    static final int DELAY = 10;
    static final int STEPS = 100;
    static final double MAX_AMOUNT = 1000;

    public static void main(String[] args) {
        var bank = new Bank(4, 100000);
        // 实现`Runnable`接口只需要实现其中的`run()`方法
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < STEPS; i++) {
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(0, 1, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        };
        // 可以使用`lambda`函数式编程简便定义
        Runnable task2 = () -> {
            try {
                for (int i = 0; i < STEPS; i++) {
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transfer(2, 3, amount);
                    Thread.sleep((int) (DELAY * Math.random()));
                }
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        };
        // 使用`Runnable`实例再创建一个`Thread`实例，然后调用`Thread`
        // 实例的`start()`方法来启动线程
        new Thread(task1).start();
        new Thread(task2).start();
    }

}
