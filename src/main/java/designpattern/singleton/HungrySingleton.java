package designpattern.singleton;

public class HungrySingleton {
    private static HungrySingleton singleton = new HungrySingleton();

    private HungrySingleton() {}

    public static HungrySingleton getUniqueInstance() {
        return singleton;
    }
}
