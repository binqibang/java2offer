package algorithm;

public class ReversePairs {
    int count = 0;

    public int reversePairs(int[] nums) {
        int[] tmp = new int[nums.length];
        msort(nums, 0, nums.length - 1, tmp);
        return count;
    }

    private void msort(int[] nums, int left, int right, int[] tmp) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        msort(nums, left, mid, tmp);
        msort(nums, mid + 1, right, tmp);
        merge(nums, left, mid, right, tmp);
    }

    private void merge(int[] nums, int left, int mid, int right, int[] tmp) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
                // 产生逆序对
                count += (mid - i + 1);
            }
        }
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        if (right + 1 - left >= 0) {
            System.arraycopy(tmp, left, nums, left, right + 1 - left);
        }
    }
}