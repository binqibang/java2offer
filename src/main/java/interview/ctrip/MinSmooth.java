package interview.ctrip;

import java.util.Scanner;

public class MinSmooth {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int minSmooth = Integer.MAX_VALUE;
        for (int i = 1; i < n - 1; i++) {
            int prev = nums[i - 1];
            int next = nums[i + 1];
            int tmp = nums[i];
            int avg = (prev + next) / 2;
            nums[i] = avg;
            int smooth = 0;
            for (int j = 1; j < n; j++) {
                smooth = Math.max(smooth, Math.abs(nums[j] - nums[j - 1]));
            }
            minSmooth = Math.min(minSmooth, smooth);
            nums[i] = tmp;
        }
        System.out.println(minSmooth);
    }
}
