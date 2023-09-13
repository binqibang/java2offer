package basis;

public class WrapperTest {
    public static void main(String[] args) {
        int a = 5;
        Integer b = 5;
        System.out.println(a == b);
        int c = 200;
        Integer d = 200;
        System.out.println(d.equals(c));
    }
}
