package interview.meituan;

import java.util.Scanner;

public class MeiTuanTestI {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // “烤串”
        int n = in.nextInt();
        String s1 = in.next();
        String s2 = in.next();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(s1.charAt(i));
            builder.append(s2.charAt(i));
        }
        System.out.println(builder);

         // “乒乓球”
        int a = in.nextInt();
        int b = in.nextInt();
        System.out.println(Math.max(11, b + 2) - a);
    }
}
