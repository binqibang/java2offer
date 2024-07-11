package algorithm;

import java.util.Random;

public class ArrayUtil {
    private static final Random random = new Random();

    public static int[] createIntArray(int length, int bound) {
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = random.nextInt(bound);
        }
        return nums;
    }
}
