import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class PracticeTest {

    Practice p = new Practice();


    @Test
    void twoSum() {
        int[] nums = {1, 5, 3, 1};
        int target = 2;
        System.out.println(Arrays.toString(p.twoSum(nums, target)));
    }

    @Test
    void addTwoNumbers() {
        int[] v1 = new int[] {1, 3, 4};
        int[] v2 = new int[] {8, 7};
        ListNode l1 = ListUtil.createList(v1);
        ListNode l2 = ListUtil.createList(v2);
        ListUtil.printList(p.addTwoNumbers(l1, l2));
    }

    @Test
    void threeSum() {
        int[] nums = {-1, 3, 2, -8, 6, 4, -4, 0};
        System.out.println(p.threeSum(nums));
    }

    @Test
    void longestPalindromeSubstring() {
        String s = "a";
        System.out.println(p.longestPalindromeSubstring(s));
    }

    @Test
    void lengthOfLongestSubstring() {
        String s = "ilovefifa";
        System.out.println(p.lengthOfLongestSubstring(s));
    }

    @Test
    void letterCombinations() {
        String digits = "246";
        System.out.println(p.letterCombinations(digits));
    }

    @Test
    void removeNthFromEnd() {
        int[] nums = {1, 2, 3, 4};
        ListNode head = ListUtil.createList(nums);
        head = p.removeNthFromEnd(head, 5);
        ListUtil.printList(head);
    }

    @Test
    void searchRange() {
        int[] nums = {1, 2, 2, 3, 6, 7};
        int target = 2;
        System.out.println(Arrays.toString(p.searchRange(nums, target)));
    }

    @Test
    void isValid() {
        String s1 = "{}{}([])";
        String s2 = "{{]";
        Assertions.assertTrue(p.isValid(s1));
        Assertions.assertFalse(p.isValid(s2));
    }

    @Test
    void mergeTwoLists() {
        int[] v1 = new int[] {1, 3, 4};
        int[] v2 = new int[] {8, 7};
        ListNode l1 = ListUtil.createList(v1);
        ListNode l2 = ListUtil.createList(v2);
        ListUtil.printList(p.mergeTwoLists(l1, l2));
    }

    @Test
    void generateParenthesis() {
        int n = 1;
        System.out.println(p.generateParenthesis(n));
    }

    @Test
    void mergeKLists() {
        int[] v1 = {1, 3, 4};
        int[] v2 = {7, 8};
        int[] v3 = {3, 5, 7, 9, 12};
        ListNode l1 = ListUtil.createList(v1);
        ListNode l2 = ListUtil.createList(v2);
        ListNode l3 = ListUtil.createList(v3);
        ListNode[] lists = new ListNode[] {l1, l2, l3};
        ListUtil.printList(p.mergeKLists(lists));
    }

    @Test
    void search() {
        int[] nums = {3, 1};
        int target = 1;
        Assertions.assertEquals(1, p.search(nums, target));
    }

    @Test
    void combinationSum() {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println(p.combinationSum(candidates, target));
    }

    @Test
    void permute() {
        int[] nums = {1, 2, 3};
        System.out.println(p.permute(nums));
    }

    @Test
    void combine() {
        System.out.println(p.combine(4, 2));
    }

    @Test
    void buildTree() {
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};
        TreeNode root = p.buildTree(pre, in);
        Assertions.assertEquals(3, root.val);
        Assertions.assertEquals(9, root.left.val);
        Assertions.assertEquals(15, root.right.left.val);
    }

    @Test
    void flatten() {
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};
        TreeNode root = p.buildTree(pre, in);
        p.flatten(root);
    }

    @Test
    void bubbleSort() {
        int[] nums = {1, 5, 3, 2, 7, 6};
        p.bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}