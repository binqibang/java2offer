package algorithm;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

class MySortTest {

    MySort mySort = new MySort();

    @Test
    public void testMySort() {
        int[] nums = ArrayUtil.createIntArray(20, 100);
        System.out.println(Arrays.toString(nums));
        mySort.mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}