package concurrency.thread;

public class JoinExample {
    static class A extends Thread {
        @Override
        public void run() {
            System.out.println("Thread A");
        }
    }

    static class B extends Thread {

        private final A a;

        B (A a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread B");
        }
    }

    public void testJoin() {
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();
    }
}
