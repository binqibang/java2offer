package oop;

public abstract class AbstractClassDemo {
    // 抽象类的成员变量默认`default`，也可加其余修饰符
    private int x = 4;
    // 可被子类实例重新赋值
    int y;

    // 抽象类可以定义方法但不实现，所有子类必须实现该方法
    public abstract void func1();

    // 抽象类也可以有具体的方法实现，子类也可以进行重写
    public void func2() {
        System.out.println("Superclass's func2");
    }
}
