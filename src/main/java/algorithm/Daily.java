package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Daily {

    /**
     * LeetCode #2970
     * @date 2024/7/10
     */
    public int incremovableSubarrayCount(int[] nums) {
        // 5 3 4 6 7
        int n = nums.length;
        int ans = 0;
        List<List<Integer>> list = new ArrayList<>();
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                if (isIncremovable(nums, l, r)) {
                    ans += 1;
                    list.add(List.of(l, r));
                }
            }
        }
        System.out.println(list);
        return ans;
    }

    private boolean isIncremovable(int[] nums, int l, int r) {
        int n = nums.length;
        // [0, l - 1]
        for (int i = 1; i < l; i++) {
            if (nums[i - 1] >= nums[i]) {
                return false;
            }
        }
        // [r + 1, n - 1]
        for (int i = r + 2; i < n; i++) {
            if (nums[i - 1] >= nums[i]) {
                return false;
            }
        }
        return l - 1 < 0 || r + 1 >= n || nums[l - 1] < nums[r + 1];
    }

    /**
     * LeetCode #165
     * @date 2024/7/11
     */
    public int compareVersion(String version1, String version2) {
        String[] revisions1 = version1.split("\\.");
        String[] revisions2 = version2.split("\\.");
        int l1 = revisions1.length, l2 = revisions2.length;
        int compare = 0;
        for (int i = 0; i < l1 || i < l2; i++) {
            int v1 = i < l1 ? Integer.parseInt(revisions1[i]) : 0;
            int v2 = i < l2 ? Integer.parseInt(revisions2[i]) : 0;
            if (v1 > v2) {
                compare = 1;
                break;
            }
            if (v1 < v2) {
                compare = -1;
                break;
            }
        }
        return compare;
    }

}
