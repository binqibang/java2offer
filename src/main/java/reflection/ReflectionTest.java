package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        // --------------------------- 获取 Class 对象 ---------------------------- //
        // 1. 通过字符串获取 Class 对象，这个字符串必须带上完整路径名
        Class playerClass1 = Class.forName("reflection.Player");
        // 2. 通过类的 class 属性
        Class playerClass2 = Player.class;
        // 3. 通过对象的 getClass() 函数
        Player player = new Player();
        Class playerClass3 = player.getClass();
        // 4. 通过类加载器
        Class playerClass4 = ClassLoader.getSystemClassLoader().loadClass("reflection.Player");

        System.out.println("class1 = " + playerClass1 + "\n" +
                "class2 = " + playerClass2 + "\n" +
                "class3 = " + playerClass3 + "\n" +
                "class4 = " + playerClass4 + "\n" +
                "class1 == class2 ? " + (playerClass1 == playerClass2) + "\n" +
                "class2 == class3 ? " + (playerClass2 == playerClass3));


        // --------------------------- 获取成员变量 ---------------------------- //
        // 1.获取所有声明的字段
        Field[] declaredFieldList = playerClass1.getDeclaredFields();
        for (Field declaredField : declaredFieldList) {
            System.out.println("declared Field: " + declaredField);
        }
        // 2.获取所有公有的字段
        Field[] fieldList = playerClass1.getFields();
        for (Field field : fieldList) {
            System.out.println("field: " + field);
        }

        // --------------------------- 获取方法 ---------------------------- //
        // 1.获取所有声明的方法
        Method[] declaredMethodList = playerClass1.getDeclaredMethods();
        for (Method declaredMethod : declaredMethodList) {
            System.out.println("declared Method: " + declaredMethod);
        }
        // 2.获取所有公有的方法
        Method[] methodList = playerClass1.getMethods();
        for (Method method : methodList) {
            System.out.println("method: " + method);
        }

        // ------------------------- 反射调用方法 -------------------------- //
        Method method = playerClass1.getMethod("play");
        Constructor constructor = playerClass1.getConstructor(String.class, int.class, int.class);
        Object o = constructor.newInstance("Bob", 10, 10);
        method.invoke(o);
    }
}
