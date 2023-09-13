package interview.ctrip;

import java.util.Scanner;

public class MatrixZeroOne {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int[][] a = new int[2][2];
        int[][] b = new int[2][2];
        int ans;
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < 2; j++) {
                a[j][0] = in.nextInt();
                a[j][1] = in.nextInt();
            }
            for (int j = 0; j < 2; j++) {
                b[j][0] = in.nextInt();
                b[j][1] = in.nextInt();
            }
            int diff = 0;
            int ones1 = 0, ones2 = 0;
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    if (a[j][k] == 1)
                        ones1++;
                    if (b[j][k] == 1)
                        ones2++;

                    if (a[j][k] != b[j][k])
                        diff++;
                }
            }
            if (ones1 != ones2 || diff % 2 != 0) {
                ans = -1;
            } else {
                ans = diff / 2;
            }
            System.out.print(ans);
        }
    }
}
