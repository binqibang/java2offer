package oop;

public class AbstractExtendDemo extends AbstractClassDemo {
    @Override
    public void func1() {
        System.out.println("func1");
    }

    // 抽象类子类也可以重写父类已经实现的方法
    @Override
    public void func2() {
        System.out.println("Subclass's func2");
    }
}
