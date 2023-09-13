package basis;

public class StringPool {


    public static void main(String[] args) {
        // 在字符串常量池中初始化 "HelloWorld" 字符串，返回其引用
        String a = "HelloWorld";
        // 在常量池中存在该字符串，直接返回同一对象的引用
        String b = "HelloWorld";
        // 创建新的字符串对象
        String c = new String("HelloWorld");
        System.out.println(a == b);         // true
        // 字符串变量`a`和`c`使不同对象的引用，但其值相同
        System.out.println(a == c);         // false
        System.out.println(a.equals(c));    // true
        // 调用 intern 方法将字符串加入常量池
        String d = new String("Google").intern();
        String e = new String("Google").intern();
        System.out.println(d == e);
    }
}
