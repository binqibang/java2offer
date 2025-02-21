package algorithm;

import java.util.*;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class Hot100Test {

    private Hot100 hot100;

    @BeforeEach
    public void setup() {
        hot100 = new Hot100();
    }


    @Test
    public void testInorderTraversal_NormalTree_ReturnsInorderTraversal() {
        TreeNode node = new TreeNode(1);
        node.right = new TreeNode(2);
        node.right.left = new TreeNode(3);
        List<Integer> expected = Arrays.asList(1, 3, 2);
        List<Integer> result = hot100.inorderTraversal(node);
        assertEquals(expected, result);
    }


    @Test
    public void testMaxDepth_NormalTree_ReturnsCorrectDepth() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        assertEquals(2, hot100.maxDepth(node));
    }


    @Test
    public void testInvertTree_NormalTree_ReturnsInvertedTree() {
        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(2);
        node.right = new TreeNode(7);
        node.left.left = new TreeNode(1);
        node.left.right = new TreeNode(3);
        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(9);

        TreeNode inverted = hot100.invertTree(node);
        assertEquals(4, inverted.val);
        assertEquals(7, inverted.left.val);
        assertEquals(2, inverted.right.val);
        assertEquals(9, inverted.left.left.val);
        assertEquals(6, inverted.left.right.val);
        assertEquals(3, inverted.right.left.val);
        assertEquals(1, inverted.right.right.val);
    }


    @Test
    public void testIsSymmetric_NormalSymmetricTree_ReturnsTrue() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(2);
        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode(4);
        node.right.left = new TreeNode(4);
        node.right.right = new TreeNode(3);
        assertTrue(hot100.isSymmetric(node));
    }

    @Test
    public void testIsSymmetric_NormalNonSymmetricTree_ReturnsFalse() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(2);
        node.left.right = new TreeNode(3);
        node.right.right = new TreeNode(3);
        assertFalse(hot100.isSymmetric(node));
    }

    @Test
    public void testDiameterOfBinaryTree_NormalTree_ReturnsCorrectDiameter() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        assertEquals(3, hot100.diameterOfBinaryTree(node));
    }


    @Test
    public void testLevelOrder_NormalTree_ReturnsLevelOrderTraversal() {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);
        List<List<Integer>> expected = Arrays.asList(
                List.of(3),
                Arrays.asList(9, 20),
                Arrays.asList(15, 7)
        );
        List<List<Integer>> result = hot100.levelOrder(node);
        assertEquals(expected, result);
    }


    @Test
    public void testSortedArrayToBST_NormalArray_ReturnsBalancedBST() {
        TreeNode root = hot100.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        assertEquals(0, root.val);
        assertEquals(-10, root.left.val);
        assertEquals(5, root.right.val);
        assertEquals(-3, root.left.right.val);
        assertEquals(9, root.right.right.val);
    }

    @Test
    public void testIsValidBST_EmptyTree_ReturnsTrue() {
        assertTrue(hot100.isValidBST(null));
    }


    @Test
    public void testIsValidBST_ComplexInvalidBST_ReturnsFalse() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(2);

        assertFalse(hot100.isValidBST(root));
    }


    @Test
    public void testKthSmallest_DeepTree_ReturnsKthSmallest() {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(3);
        node.right = new TreeNode(6);
        node.left.left = new TreeNode(2);
        node.left.right = new TreeNode(4);
        node.left.left.left = new TreeNode(1);

        assertEquals(3, hot100.kthSmallest(node, 3));
    }

    @Test
    void testPathSum_TargetSumExists_ReturnsCorrectCount() {
        TreeNode node = new TreeNode(10);
        node.left = new TreeNode(5);
        node.right = new TreeNode(-3);
        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode(2);
        node.right.right = new TreeNode(11);
        node.left.left.left = new TreeNode(3);
        node.left.left.right = new TreeNode(-2);
        node.left.right.right = new TreeNode(1);

        assertEquals(3, hot100.pathSum(node, 8));
    }

    @Test
    void testPathSum_TargetSumDoesNotExist_ReturnsZero() {
        TreeNode node = new TreeNode(10);
        node.left = new TreeNode(5);
        node.right = new TreeNode(-3);
        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode(2);
        node.right.right = new TreeNode(11);
        node.left.left.left = new TreeNode(3);
        node.left.left.right = new TreeNode(-2);
        node.left.right.right = new TreeNode(1);

        assertEquals(0, hot100.pathSum(node, 0));
    }

    @Test
    public void testRotate_3x3Matrix_RotatesCorrectly() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] expected = {
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3}
        };
        hot100.rotate(matrix);
        assertArrayEquals(expected, matrix);
    }


    @Test
    public void testSearchMatrix_TargetInMatrix_ReturnsTrue() {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        assertTrue(hot100.searchMatrix(matrix, 3));
    }

    @Test
    public void testSearchMatrix_TargetNotInMatrix_ReturnsFalse() {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        assertFalse(hot100.searchMatrix(matrix, 13));
    }

    @Test
    public void testTrie_InsertAndSearch_ReturnsCorrectResults() {
        Trie trie = new Trie();
        trie.insert("apple");
        assertTrue(trie.search("apple"));
        assertFalse(trie.search("app"));
        assertTrue(trie.startsWith("app"));
    }

    @Test
    public void searchMatrixII_TargetInMatrix_ReturnsTrue() {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target = 5;
        assertTrue(hot100.searchMatrixII(matrix, target));
    }

    @Test
    public void searchMatrixII_TargetNotInMatrix_ReturnsFalse() {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target = 20;
        assertFalse(hot100.searchMatrixII(matrix, target));
    }

    @Test
    public void testSearchRange_TargetNotInArray_ReturnsNegativeOne() {
        int[] nums = {1, 2, 3, 4, 5};
        int target = 6;
        int[] expected = {-1, -1};
        assertArrayEquals(expected, hot100.searchRange(nums, target));
    }

    @Test
    public void testSearchRange_TargetSingleInstance_ReturnsSameStartEnd() {
        int[] nums = {1, 2, 3, 4, 5};
        int target = 3;
        int[] expected = {2, 2};
        assertArrayEquals(expected, hot100.searchRange(nums, target));
    }

    @Test
    public void testSearchRange_TargetMultipleInstances_ReturnsCorrectRange() {
        int[] nums = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;
        int[] expected = {1, 3};
        assertArrayEquals(expected, hot100.searchRange(nums, target));
    }

    @Test
    public void testSingleNumber_SingleElement_ReturnsElement() {
        int[] nums = {1};
        assertEquals(1, hot100.singleNumber(nums));
    }

    @Test
    public void testSingleNumber_DifferentOrder_ReturnsSingleNumber() {
        int[] nums = {4, 1, 2, 1, 2};
        assertEquals(4, hot100.singleNumber(nums));
    }

    @Test
    public void testSingleNumber_AllSameExceptOne_ReturnsSingleNumber() {
        int[] nums = {1, 1, 1, 1, 1, 1};
        assertEquals(0, hot100.singleNumber(nums));
    }

    @Test
    public void testIsValid_EmptyString_ReturnsTrue() {
        assertTrue(hot100.isValid(""));
        assertTrue(hot100.isValid(null));
    }

    @Test
    public void testIsValid_MixedBrackets_ReturnsTrue() {
        assertTrue(hot100.isValid("{[()]}"));
    }

    @Test
    public void testIsValid_MixedBrackets_ReturnsFalse() {
        assertFalse(hot100.isValid("{[(])}"));
    }

    @Test
    public void put_CacheOverflow_ShouldRemoveLRUItem() {
        Hot100.LRUCache cache = new Hot100.LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3); // 触发溢出，移除键1
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
        assertEquals(3, cache.get(3));
    }
}
