package algorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Top150Test {

    private Top150 top150;

    @BeforeEach
    void setUp() {
        top150 = new Top150();
    }

    @Test
    void sumNumbers() {
        TreeNode root = new TreeNode();
        root.val = 4;
        root.left = new TreeNode();
        root.left.val = 9;
        root.right = new TreeNode();
        root.right.val = 0;
        root.left.left = new TreeNode();
        root.left.left.val = 5;
        root.left.right = new TreeNode();
        root.left.right.val = 1;
        assertEquals(1026, top150.sumNumbers(root));
    }

    @Test
    void evalRPN() {
        String[] expression = {"2","1","+","3","*"};
        assertEquals(9, top150.evalRPN(expression));
    }

    @Test
    void evalRPN_InvalidExpression() {
        String [] expression = {"2","+"};
        assertThrows(IllegalArgumentException.class, () -> top150.evalRPN(expression));
    }

    @Test
    void countNodes_CompleteBinaryTree_ReturnsCorrectCount() {
        TreeNode root = new TreeNode();
        root.val = 1;
        root.left = new TreeNode();
        root.left.val = 2;
        root.right = new TreeNode();
        root.right.val = 3;
        root.left.left = new TreeNode();
        root.left.left.val = 4;
        root.left.right = new TreeNode();
        root.left.right.val = 5;
        root.right.left = new TreeNode();
        root.right.left.val = 6;
        assertEquals(6, top150.countNodes(root));
    }

    @Test
    void uniquePathsWithObstacles() {
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        assertEquals(2, top150.uniquePathsWithObstacles(obstacleGrid));
    }

    @Test
    void uniquePathsWithObstacles_StartWithObstacle_ReturnsZero() {
        int[][] obstacleGrid = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        assertEquals(0, top150.uniquePathsWithObstacles(obstacleGrid));
    }

    @Test
    void uniquePathsWithObstacles_SingleNode_ReturnsOne() {
        int[][] obstacleGrid = {{0}};
        assertEquals(1, top150.uniquePathsWithObstacles(obstacleGrid));
    }

    @Test
    public void buildTree_ReturnsCorrectTree() {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode root = top150.buildTree(inorder, postorder);
        assertNotNull(root);
        assertEquals(3, root.val);
        assertEquals(9, root.left.val);
        assertEquals(20, root.right.val);
        assertEquals(15, root.right.left.val);
        assertEquals(7, root.right.right.val);
    }

    @Test
    void isSameTree_SubtreesDifferent_ReturnsFalse() {
        TreeNode p = new TreeNode();
        p.val = 1;
        p.left = new TreeNode();
        p.left.val = 2;

        TreeNode q = new TreeNode();
        q.val = 1;
        q.right = new TreeNode();
        q.right.val = 2;

        assertFalse(top150.isSameTree(p, q));
    }

    @Test
    void isSameTree_BothSame_ReturnsTrue() {
        TreeNode p = new TreeNode();
        p.val = 1;
        p.left = new TreeNode();
        p.left.val = 2;
        p.right = new TreeNode();
        p.right.val = 3;

        TreeNode q = new TreeNode();
        q.val = 1;
        q.left = new TreeNode();
        q.left.val = 2;
        q.right = new TreeNode();
        q.right.val = 3;

        assertTrue(top150.isSameTree(p, q));
    }
}