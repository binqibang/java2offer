package interview.anti;

import java.util.Scanner;

public class ShortestStr {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (k > 0) {
            if (k % 2 == 1) {
                sb.append((char) ('a' + i));
            }
            i++;
            k /= 2;
        }
        System.out.println(sb);
    }
}
