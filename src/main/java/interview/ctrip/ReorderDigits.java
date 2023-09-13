package interview.ctrip;

import java.util.Scanner;

public class ReorderDigits {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            String num = in.next();
            int idx = -1;
            for (int j = num.length() - 1; j >= 0; j--) {
                if ((num.charAt(j) - '0') % 2 == 0) {
                    idx = j;
                    break;
                }
            }
            if (idx == -1) {
                System.out.println(-1);
            } else {
                System.out.println(
                        num.substring(0, idx) +
                        num.substring(idx + 1) +
                        num.charAt(idx)
                );
            }
        }
    }
}
