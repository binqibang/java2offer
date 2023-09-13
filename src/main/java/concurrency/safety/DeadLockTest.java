package concurrency.safety;

public class DeadLockTest {
    private static final Object resource1 = new Object(); //资源 1
    private static final Object resource2 = new Object(); //资源 2

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + " got resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + " got resource2");
                }
            }
        }, "Thread 1").start();

        // 构成循环等待
//        new Thread(() -> {
//            synchronized (resource2) {
//                System.out.println(Thread.currentThread() + " got resource2");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread() + " waiting get resource1");
//                synchronized (resource1) {
//                    System.out.println(Thread.currentThread() + " got resource1");
//                }
//            }
//        }, "Thread 2").start();

        // 破坏循环等待
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + " got resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + " got resource2");
                }
            }
        }, "Thread 2").start();
    }
}
