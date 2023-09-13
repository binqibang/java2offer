package oop;

import org.junit.jupiter.api.Test;

class InterfaceImplementTest {
    @Test
    void testInterface() {
        InterfaceDemo ie2 = new InterfaceImplementDemo();
        ie2.func1();
        // 接口中所有成员变量都默认`public static`
        System.out.println(InterfaceDemo.x);
    }

}