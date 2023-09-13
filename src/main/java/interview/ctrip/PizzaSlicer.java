package interview.ctrip;

import java.util.Scanner;

public class PizzaSlicer {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();
        double area;
        if (k == 1) {
            area = 2d;
        } else {
            int m = k / 2;
            double a = (double) n / m, b = (double) n / (k - m);
            area = a * b;
        }
        System.out.printf("%.2f", area);
    }
}
