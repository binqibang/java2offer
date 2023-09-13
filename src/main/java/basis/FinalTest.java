package basis;

public class FinalTest {
    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("(%.2f, %.2f)", x, y);
        }
    }

    public static void main(String[] args) {
        final Point a = new Point(1.22, 3.44);
        // 引用类型变量被`final`修饰后，不能再指向其他对象
        // a = new Point(2.3, 4.5);

        // 但可以改变其指向的对象
        a.x = 0.9;

        final int b = 9;
        // 基本数据类型变量被`final`修饰后，值不能再改变
        // b = 8;

        final String c = "Java";
        // 普通`String`变量指向的字符串不可变，但可以指向其他字符串，
        // 被`final`修饰后，不能再指向其他字符串
        // c = "Python";
    }
}
