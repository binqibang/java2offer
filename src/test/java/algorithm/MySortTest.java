package algorithm;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MySortTest {

    MySort mySort = new MySort();

    @Test
    public void test_quickSort() {
        int[] nums = ArrayUtil.createIntArray(20, 100);
        System.out.println(Arrays.toString(nums));
        mySort.quickSort(nums);
        System.out.println(Arrays.toString(nums));
        assertTrue(ArrayUtil.isSorted(nums));
    }


    @Test
    void test_heapSort() {
        int[] nums = ArrayUtil.createIntArray(20, 100);
        System.out.println(Arrays.toString(nums));
        mySort.heapSort(nums);
        System.out.println(Arrays.toString(nums));
        assertTrue(ArrayUtil.isSorted(nums));
    }

    @Test
    void test_bubbleSort() {
        int[] nums = ArrayUtil.createIntArray(20, 100);
        System.out.println(Arrays.toString(nums));
        mySort.bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
        assertTrue(ArrayUtil.isSorted(nums));
    }

    @Test
    void test_insertionSort() {
        int[] nums = ArrayUtil.createIntArray(20, 100);
        System.out.println(Arrays.toString(nums));
        mySort.insertionSort(nums);
        System.out.println(Arrays.toString(nums));
        assertTrue(ArrayUtil.isSorted(nums));
    }

    @Test
    void test_mergeSort() {
        int[] nums = ArrayUtil.createIntArray(20, 100);
        System.out.println(Arrays.toString(nums));
        mySort.mergeSort(nums);
        System.out.println(Arrays.toString(nums));
        assertTrue(ArrayUtil.isSorted(nums));
    }
}