package concurrency;

import concurrency.thread.JoinExample;
import org.junit.jupiter.api.Test;

class JoinExampleTest {

    @Test
    void testJoin() {
        JoinExample e = new JoinExample();
        e.testJoin();
    }
}