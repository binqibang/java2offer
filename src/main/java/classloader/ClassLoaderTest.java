package classloader;

public class ClassLoaderTest {
    public static void main(String[] args) {
        // 系统类加载器为 AppClassLoader
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        printClassLoaderTree(systemClassLoader);

        // 自定义类加载器为 AppClassLoader
        ClassLoader usualClassLoader = ClassLoaderTest.class.getClassLoader();
        printClassLoaderTree(usualClassLoader);

        // String等类库使用 BootstrapClassLoader
        // 使用 C++ 实现，无法获取
        ClassLoader stringClassLoader = String.class.getClassLoader();
        printClassLoaderTree(stringClassLoader);

    }

    private static void printClassLoaderTree(ClassLoader classLoader) {
        StringBuilder sb = new StringBuilder("|--");
        boolean needContinue = true;
        while (needContinue) {
            System.out.println(sb.toString() + classLoader);
            if (classLoader == null) {
                needContinue = false;
            } else {
                classLoader = classLoader.getParent();
                sb.insert(0, "\t");
            }
        }
        System.out.println();
    }
}
