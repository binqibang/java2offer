package algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


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
        Assertions.assertTrue(algorithm.canSortArray(nums1));
        Assertions.assertFalse(algorithm.canSortArray(nums2));
    }

    @Test
    void buildTree() {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode root1 = BTUtil.buildTree(preorder, inorder);
        TreeNode root2 = algorithm.buildTree(inorder, postorder);
        Assertions.assertEquals(algorithm.levelOrderBottom(root1),
                algorithm.levelOrderBottom(root2));
        System.out.println(algorithm.levelOrderBottom(root1));
    }
}