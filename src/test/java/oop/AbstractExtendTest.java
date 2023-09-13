package oop;

import org.junit.jupiter.api.Test;

class AbstractExtendTest {

    @Test
    void testAbstractClass() {
        // AbstractClassDemo is abstract; cannot be instantiated
        // AbstractClassDemo ac1 = new AbstractClassDemo();
        AbstractClassDemo ac = new AbstractExtendDemo();
        ac.func1();
        ac.func2();
        ac.y = 3;
        System.out.println(ac.y);
    }
}