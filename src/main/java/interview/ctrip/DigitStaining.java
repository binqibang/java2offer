package interview.ctrip;

import java.util.Scanner;

public class DigitStaining {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = in.nextInt();
            }
            int rMin = Integer.MAX_VALUE, rMax = 0;
            int bMin = Integer.MAX_VALUE, bMax = 0;
            String colors = in.next();
            for (int j = 0; j < n; j++) {
                if (colors.charAt(j) == 'R') {
                    rMin = Math.min(rMin, nums[j]);
                    rMax = Math.max(rMax, nums[j]);
                } else {
                    bMin = Math.min(bMin, nums[j]);
                    bMax = Math.max(bMax, nums[j]);
                }
            }
            if (rMax == 0) {    // 全蓝
                System.out.println(bMax - bMin);
                continue;
            }
            if (bMax == 0) {    // 全红
                System.out.println(rMax - rMin);
                continue;
            }
            if (rMax >= bMax && rMin >= bMin) {
                System.out.println(rMax - bMin);
                continue;
            }
            System.out.println(Math.max(rMax - rMin, bMax - bMin));
        }
    }
}
