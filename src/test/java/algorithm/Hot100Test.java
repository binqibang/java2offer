package algorithm;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Hot100Test {

    Hot100 algorithm = new Hot100();


    @Test
    void twoSum() {
        int[] nums = {1, 5, 3, 1};
        int target = 2;
        System.out.println(Arrays.toString(algorithm.twoSum(nums, target)));
    }

    @Test
    void addTwoNumbers() {
        int[] v1 = new int[]{1, 3, 4};
        int[] v2 = new int[]{8, 7};
        ListNode l1 = ListUtil.createList(v1);
        ListNode l2 = ListUtil.createList(v2);
        ListUtil.printList(algorithm.addTwoNumbers(l1, l2));
    }

    @Test
    void threeSum() {
        int[] nums = {-1, 3, 2, -8, 6, 4, -4, 0};
        System.out.println(algorithm.threeSum(nums));
    }

    @Test
    void longestPalindromeSubstring() {
        String s = "abb";
        System.out.println(algorithm.longestPalindromeSubstring(s));
    }

    @Test
    void lengthOfLongestSubstring() {
        String s = "ilovefifa";
        System.out.println(algorithm.lengthOfLongestSubstring(s));
    }

    @Test
    void letterCombinations() {
        String digits = "246";
        System.out.println(algorithm.letterCombinations(digits));
    }

    @Test
    void removeNthFromEnd() {
        int[] nums = {1, 2, 3, 4};
        ListNode head = ListUtil.createList(nums);
        head = algorithm.removeNthFromEnd(head, 5);
        ListUtil.printList(head);
    }

    @Test
    void searchRange() {
        int[] nums = {1, 2, 2, 3, 6, 7};
        int target = 2;
        System.out.println(Arrays.toString(algorithm.searchRange(nums, target)));
    }

    @Test
    void isValid() {
        String s1 = "{}{}([])";
        String s2 = "{{]";
        assertTrue(algorithm.isValid(s1));
        assertFalse(algorithm.isValid(s2));
    }

    @Test
    void mergeTwoLists() {
        int[] v1 = new int[]{1, 3, 4};
        int[] v2 = new int[]{8, 7};
        ListNode l1 = ListUtil.createList(v1);
        ListNode l2 = ListUtil.createList(v2);
        ListUtil.printList(algorithm.mergeTwoLists(l1, l2));
    }

    @Test
    void generateParenthesis() {
        int n = 1;
        System.out.println(algorithm.generateParenthesis(n));
    }

    @Test
    void mergeKLists() {
        int[] v1 = {1, 3, 4};
        int[] v2 = {7, 8};
        int[] v3 = {3, 5, 7, 9, 12};
        ListNode l1 = ListUtil.createList(v1);
        ListNode l2 = ListUtil.createList(v2);
        ListNode l3 = ListUtil.createList(v3);
        ListNode[] lists = new ListNode[]{l1, l2, l3};
        ListUtil.printList(algorithm.mergeKLists(lists));
    }

    @Test
    void search() {
        int[] nums = {3, 1};
        int target = 1;
        assertEquals(1, algorithm.search(nums, target));
    }

    @Test
    void combinationSum() {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println(algorithm.combinationSum(candidates, target));
    }

    @Test
    void permute() {
        int[] nums = {1, 2, 3};
        System.out.println(algorithm.permute(nums));
    }

    @Test
    void combine() {
        System.out.println(algorithm.combine(4, 2));
    }

    @Test
    void buildTree() {
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};
        TreeNode root = algorithm.buildTree(pre, in);
        assertEquals(3, root.val);
        assertEquals(9, root.left.val);
        assertEquals(15, root.right.left.val);
    }

    @Test
    void flatten() {
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};
        TreeNode root = algorithm.buildTree(pre, in);
        algorithm.flatten(root);
    }

    @Test
    void multiply() {
        String num1 = "25";
        String num2 = "25";
        System.out.println(algorithm.multiply(num1, num2));
    }

    @Test
    void uniquePaths() {
        int m = 7, n = 3;
        System.out.println(algorithm.uniquePaths(m, n));
    }

    @Test
    void divString() {
        String a = "98289292988";
        int b = 9;
        BigDecimal bd1 = new BigDecimal(a);
        BigDecimal bd2 = new BigDecimal(b);
        System.out.println(bd1.divide(bd2, 2, RoundingMode.HALF_UP));
        System.out.println(algorithm.divString(a, b));
    }

    @Test
    void reverse() {
        int[] vals = {1, 2, 3, 4, 5};
        ListNode l = ListUtil.createList(vals);
        ListNode r = algorithm.reverse(l);
        ListUtil.printList(r);
    }

    @Test
    void evalRPN() {
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println(algorithm.evalRPN(tokens));
    }

    @Test
    void calculate() {
        String s = "(38+2)*2/40";
        System.out.println(algorithm.calculate(s));
    }

    @Test
    void compare() {
        String version1 = "1.1", version2 = "1.2";
        System.out.println(algorithm.compare(version1, version2));
    }

    @Test
    void testLRU() {
        Hot100.MyLRUCache lruCache = new Hot100.MyLRUCache(2);
        lruCache.set(1, 1);
        lruCache.set(2, 2);
        assertEquals(1, lruCache.get(1));
        lruCache.set(3, 3);
        assertEquals(-1, lruCache.get(2));
    }
}