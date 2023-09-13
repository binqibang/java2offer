package designpattern.singleton;

public class LazySingleton {
    private static LazySingleton singleton = null;

    private LazySingleton() {
        // 设置构造方法私有，使得无法通过构造方法创建实例
    }

    public static LazySingleton getUniqueInstance() {
        if (singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }
}
