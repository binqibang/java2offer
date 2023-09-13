package algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReversePairsTest {

    @Test
    void reversePairs() {
        ReversePairs rp = new ReversePairs();
        int[] nums = {7, 5, 6, 4};
        Assertions.assertEquals(5, rp.reversePairs(nums));
    }
}