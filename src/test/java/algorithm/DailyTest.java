package algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class DailyTest {

    Daily algorithm = new Daily();

    @Test
    void incremovableSubarrayCount() {
        int[] nums = {5, 4, 6, 8, 1};
        Assertions.assertEquals(3, algorithm.incremovableSubarrayCount(nums));
    }

    @Test
    void compareVersion() {
        String v1 = "1.0";
        String v2 = "1.0.0.0";
        Assertions.assertEquals(0, algorithm.compareVersion(v1, v2));
    }
}