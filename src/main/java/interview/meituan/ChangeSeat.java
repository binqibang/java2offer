package interview.meituan;

import java.util.Scanner;

public class ChangeSeat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt(), a = sc.nextInt();
        String[][] seats = new String[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                seats[i][j] = sc.next();
            }
        }
        String[][] tmp = new String[m][n];
        for (int i = 0; i < m; i++) {
            tmp[(i + 1) % m] = seats[i];
        }
        String[][] changed = new String[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                changed[i][(j + 1) % n] = tmp[i][j];
            }
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < a; k++) {
                    if (seats[i][j].charAt(k) != changed[i][j].charAt(k)) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
