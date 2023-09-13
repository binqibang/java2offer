package classloader;

public class PrintClassLoaderTree {
    public static void main(String[] args) {
        ClassLoader classLoader = PrintClassLoaderTree.class.getClassLoader();

        StringBuilder sb = new StringBuilder("|--");
        boolean needContinue = true;
        while (needContinue){
            System.out.println(sb.toString() + classLoader);
            if(classLoader == null){
                needContinue = false;
            }else{
                classLoader = classLoader.getParent();
                sb.insert(0, "\t");
            }
        }
    }
}
