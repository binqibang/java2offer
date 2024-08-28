package algorithm;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class DailyTest {

    Daily algorithm = new Daily();

    @Test
    void incremovableSubarrayCount() {
        int[] nums = {5, 4, 6, 8, 1};
        assertEquals(3, algorithm.incremovableSubarrayCount(nums));
    }

    @Test
    void compareVersion() {
        String v1 = "1.0";
        String v2 = "1.0.0.0";
        assertEquals(0, algorithm.compareVersion(v1, v2));
    }

    @Test
    void numberGame() {
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(algorithm.numberGame(nums)));
    }

    @Test
    void levelOrderBottom() {
        int[] pre = {1, 2, 3, 4, 5};
        int[] in = {4, 3, 5, 2, 1};
        TreeNode root = BTUtil.buildTree(pre, in);
        System.out.println(algorithm.levelOrderBottom(root));
    }

    @Test
    void canSortArray() {
        int[] nums1 = {8, 4, 2, 30, 15};
        int[] nums2 = {3, 16, 8, 4, 2};
        assertTrue(algorithm.canSortArray(nums1));
        assertFalse(algorithm.canSortArray(nums2));
    }

    @Test
    void buildTree() {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode root1 = BTUtil.buildTree(preorder, inorder);
        TreeNode root2 = algorithm.buildTree(inorder, postorder);
        assertEquals(algorithm.levelOrderBottom(root1),
                algorithm.levelOrderBottom(root2));
        System.out.println(algorithm.levelOrderBottom(root1));
    }

    @Test
    void accountsMerge() {
        List<List<String>> accounts = Arrays.asList(
                Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                Arrays.asList("Mary", "mary@mail.com"),
                Arrays.asList("John", "johnnybravo@mail.com")
        );
        System.out.println(algorithm.accountsMerge(accounts));
    }

    @Test
    void findIntersectionValues() {
        int[] nums1 = {4, 3, 2, 3, 1}, nums2 = {2, 2, 5, 2, 3, 6};
        assertArrayEquals(new int[]{3, 4},
                algorithm.findIntersectionValues(nums1, nums2));
    }

    @Test
    void numSimilarGroups() {
        String[] strs1 = {"tars", "rats", "arts", "star"};
        assertEquals(2, algorithm.numSimilarGroups(strs1));
        String[] strs2 = {"omv", "ovm"};
        assertEquals(1, algorithm.numSimilarGroups(strs2));
    }

    @Test
    void isSimilar() {
        String s1 = "tars", s2 = "rats", s3 = "star";
        assertTrue(algorithm.isSimilar(s1, s2));
        assertFalse(algorithm.isSimilar(s1, s3));
    }

    @Test
    void merge() {
        int[] nums1 = {1, 2, 3, 0, 0, 0}, nums2 = {2, 5, 6};
        algorithm.merge(nums1, 3, nums2, 3);
        assertArrayEquals(new int[]{1, 2, 2, 3, 5, 6}, nums1);
    }

    @Test
    void removeElement() {
        int[] nums = {3, 2, 2, 3};
        assertEquals(2, algorithm.removeElement(nums, 3));
        assertArrayEquals(new int[]{2, 2}, Arrays.copyOfRange(nums, 0, 2));
    }

    @Test
    void findValueOfPartition() {
        int[] nums = {1, 5, 3, 4, 2};
        int expected = 1;
        int actual = algorithm.findValueOfPartition(nums);
        assertEquals(expected, actual);
    }
}