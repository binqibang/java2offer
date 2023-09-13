package interview.meituan;

import java.util.Arrays;
import java.util.Scanner;

public class AdjustArray {

    private static int minOperations(int[] nums, int mid) {
        int count = 0;
        int val = nums[mid] - 1;
        for (int i = mid - 1; i >= 0; i--) {
            count += Math.abs(nums[i] - val);
            val--;
        }
        val = nums[mid] + 1;
        for (int i = mid + 1; i < nums.length; i++) {
            count += Math.abs(nums[i] - val);
            val++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        Arrays.sort(arr);
        if (n % 2 == 1) {
            System.out.println(minOperations(arr, n / 2));
        } else {
            int c1 = minOperations(arr, n / 2);
            int c2 = minOperations(arr, n / 2 - 1);
            System.out.println(Math.min(c1, c2));
        }

    }
}
