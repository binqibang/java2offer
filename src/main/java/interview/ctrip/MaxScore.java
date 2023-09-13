package interview.ctrip;

import java.util.Scanner;

public class MaxScore {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
            int min = Math.min(a, Math.min(b, c));
            System.out.println(2 * min + (b - min) / 2);
        }
    }
}
