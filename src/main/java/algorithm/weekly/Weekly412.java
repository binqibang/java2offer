package algorithm.weekly;

public class Weekly412 {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        // nums = [2,1,3,5,6], k = 5, multiplier = 2
        for (int i = 0; i < k; i++) {
            int min = getMinIdx(nums);
            nums[min] = nums[min] * multiplier;
        }
        return nums;
    }

    private int getMinIdx(int[] nums) {
        int minVal = nums[0];
        int minIdx = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < minVal) {
                minIdx = i;
                minVal = nums[i];
            }
        }
        return minIdx;
    }


    public int countPairs(int[] nums) {
        int count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int num1 = nums[i];
                int num2 = nums[j];
                if (check(String.valueOf(num1), String.valueOf(num2))) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean check(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 == len2) {
            StringBuilder diff1 = new StringBuilder();
            StringBuilder diff2 = new StringBuilder();
            for (int i = 0; i < len1; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    diff1.append(s1.charAt(i));
                    diff2.append(s2.charAt(i));
                }
            }
            if (diff1.length() == 0) {
                return true;
            }
            if (diff1.length() == 2) {
                return diff1.charAt(0) == diff2.charAt(1) && diff1.charAt(1) == diff2.charAt(0);
            }
            return false;
        } else if (len1 < len2) {
            StringBuilder sb = new StringBuilder(s1);
            sb.insert(0, "0".repeat(len2 - len1));
            return check(sb.toString(), s2);
        } else {
            StringBuilder sb = new StringBuilder(s2);
            sb.insert(0, "0".repeat(len1 - len2));
            return check(s1, sb.toString());
        }
    }


}
