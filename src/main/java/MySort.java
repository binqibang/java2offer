import java.util.Arrays;

public class MySort {

    public static void heapSort(int[] nums) {
        int n = nums.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(nums, i, n);
        }
        for (int i = n - 1; i >= 0; i--) {
            swap(nums, i, 0);
            maxHeapify(nums, 0, i);
        }
    }

    private static void maxHeapify(int[] nums, int root, int n) {
        int max = root;
        int left = 2 * root + 1, right = 2 * root + 2;
        if (left < n && nums[left] > nums[max]) {
            max = left;
        }
        if (right < n && nums[right] > nums[max]) {
            max = right;
        }
        if (max != root) {
            swap(nums, max, root);
            maxHeapify(nums, max, n);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static void bubbleSort(int[] nums) {
        boolean isSorted = false;
        for (int i = nums.length - 1; i > 0 && !isSorted; i--) {
            isSorted = true;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j+1]) {
                    swap(nums, j, j + 1);
                    isSorted = false;
                }
            }
        }
    }


    public static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int value = nums[i];
            int j = i - 1;
            for (; j >= 0 && nums[j] > value; j--) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = value;
        }
    }


    public static void mergeSort(int[] nums) {
        int[] tmp = new int[nums.length];
        msort(nums, 0, nums.length - 1, tmp);
    }

    private static void msort(int[] nums, int left, int right, int[] tmp) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        msort(nums, left, mid, tmp);
        msort(nums, mid + 1, right, tmp);
        merge(nums, left, mid, right, tmp);
    }

    private static void merge(int[] nums, int left, int mid, int right, int[] tmp) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        for (int l = left; l <= right; l++) {
            nums[l] = tmp[l];
        }
    }

    public static void quickSort(int[] nums) {
        qsort(nums, 0, nums.length - 1);
    }

    private static void qsort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = partition(nums, left, right);
        qsort(nums, left, mid - 1);
        qsort(nums, mid + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {
        int randomIdx = left + (int) ((right - left + 1) * Math.random());
        swap(nums, randomIdx, left);
        int i = left, j = right;
        int pivot = nums[left];
        while (i < j) {
            while (i < j && nums[j] >= pivot) {
                j--;
            }
            while (i < j && nums[i] <= pivot) {
                i++;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        swap(nums, left, i);
        return i;
    }

    public static void main(String[] args) {
        int[] nums = ArrayUtil.createIntArray(20, 100);
        System.out.println(Arrays.toString(nums));
//        heapSort(nums);
//        bubbleSort(nums);
//        insertionSort(nums);
        mergeSort(nums);
//        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

}