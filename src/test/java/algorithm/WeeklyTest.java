package algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WeeklyTest {

    Weekly weekly = new Weekly();

    @Test
    void getSmallestString() {
        String s = "45320";
        Assertions.assertEquals("43520", weekly.getSmallestString(s));
    }

    @Test
    void modifiedList() {
        int[] vals = {1, 2, 3, 4, 5};
        int[] nums = {1, 2, 3};
        ListNode head = ListUtil.createList(vals);
        ListNode modified = weekly.modifiedList(nums, head);
        Assertions.assertEquals("4 -> 5", ListUtil.toString(modified));
    }

    @Test
    void minimumCost() {
        int m = 3;
        int n = 2;
        int[] horizontalCut = {1, 3};
        int[] verticalCut = {5};
        Assertions.assertEquals(13, weekly.minimumCost(m, n, horizontalCut, verticalCut));
    }
}