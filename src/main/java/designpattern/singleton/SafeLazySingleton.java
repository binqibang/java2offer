package designpattern.singleton;

public class SafeLazySingleton {
    private static volatile SafeLazySingleton singleton = null;

    private SafeLazySingleton () {}

    public static SafeLazySingleton getUniqueInstance() {
        if (singleton == null) {
            synchronized (SafeLazySingleton.class) {
                if (singleton == null) {
                    singleton = new SafeLazySingleton();
                }
            }
        }
        return singleton;
    }
}
