package exception;

public class TryFinallyTest {
    public static int square(int val) {
        try {
            return val * val;
        } finally {
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(square(1));
    }
}
