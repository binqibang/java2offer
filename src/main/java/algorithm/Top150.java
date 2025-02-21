package algorithm;

import java.util.*;

public class Top150 {

    /**
     * LeetCode #129
     * 计算从根节点到叶节点生成的所有数字之和
     *
     * @param root 二叉树根节点
     * @return 所有数字之和
     */
    public int sumNumbers(TreeNode root) {
        int digit = 0;
        dfs(root, digit);
        return sum;
    }

    private int sum = 0;

    private void dfs(TreeNode root, int digit) {
        if (root == null) {
            return;
        }
        digit = digit * 10 + root.val;
        if (root.left == null && root.right == null) {
            sum += digit;
        }
        dfs(root.left, digit);
        dfs(root.right, digit);
    }


    /**
     * LeetCode #150
     * 逆波兰表达式求值
     *
     * @param tokens 逆波兰表达式，每个元素都是数字或者运算符
     * @return 表达式的值
     */
    public int evalRPN(String[] tokens) {
        // 如果输入为空，则返回0
        if (tokens.length == 0) {
            return 0;
        }
        // 使用栈来存储数字
        Deque<Integer> stack = new LinkedList<>();
        // 定义运算符集合
        Set<String> operators = Set.of("+", "-", "*", "/");
        // 遍历逆波兰表达式
        for (var token : tokens) {
            // 如果是运算符
            if (operators.contains(token)) {
                try {
                    // 从栈中弹出两个数字
                    int left = stack.pop();
                    int right = stack.pop();
                    // 根据运算符计算结果
                    int res;
                    switch (token) {
                        case "+":
                            res = right + left;
                            break;
                        case "-":
                            res = right - left;
                            break;
                        case "*":
                            res = right * left;
                            break;
                        case "/":
                            res = right / left;
                            break;
                        default:
                            // 如果运算符无效，抛出异常
                            throw new IllegalArgumentException("Invalid operator: " + token);
                    }
                    // 将结果压入栈中
                    stack.push(res);
                } catch (NoSuchElementException e) {
                    // 如果栈中元素不足，说明表达式无效，抛出异常
                    throw new IllegalArgumentException("Invalid expression: " + Arrays.toString(tokens));
                }
            } else {
                try {
                    // 如果是数字，将其转换为整数后压入栈中
                    stack.push(Integer.parseInt(token));
                } catch (NumberFormatException e) {
                    // 如果转换失败，说明表达式无效，抛出异常
                    throw new IllegalArgumentException("Invalid expression: " + Arrays.toString(tokens));
                }
            }
        }
        // 最后栈中剩余的元素即为表达式的值
        if (stack.size() > 1) {
            throw new IllegalArgumentException("Invalid expression: " + Arrays.toString(tokens));
        }
        return stack.pop();
    }


    /**
     * LeetCode #222
     * 完全二叉树的节点个数
     *
     * @param root 完全二叉树根节点
     * @return 节点个数
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lDepth = 0, rDepth = 0;
        // 判断当前子树是不是满二叉树
        var node = root;
        while (node.left != null) {
            lDepth++;
            node = node.left;
        }
        node = root;
        while (node.right != null) {
            rDepth++;
            node = node.right;
        }
        if (lDepth == rDepth) {
            return (int) Math.pow(2, lDepth + 1) - 1;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }


    /**
     * LeetCode #63
     * 求左上角到右下角的不同路径总数，路径不能含有障碍物
     *
     * @param obstacleGrid 障碍物矩阵，1表示障碍物，0表示空地
     * @return 路径总数
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] != 0) {
                break;
            }
            dp[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] != 0) {
                break;
            }
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * LeetCode #106
     * 根据前序遍历和中序遍历构建二叉树
     *
     * @param inorder 中序遍历
     * @param postorder 后序遍历
     * @return 二叉树根节点
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            index.put(inorder[i], i);
        }
        return build(index, 0, inorder.length - 1,
                postorder, postorder.length - 1);
    }

    private TreeNode build(Map<Integer, Integer> InorderIndex, int iStart,
                           int iEnd, int[] postorder, int pEnd) {
        if (iStart > iEnd) {
            return null;
        }
        int rootVal = postorder[pEnd];
        TreeNode root = new TreeNode(rootVal);
        int rootIdx = InorderIndex.get(rootVal);
        int rightLen = iEnd - rootIdx;
        root.left = build(InorderIndex, iStart, rootIdx - 1, postorder, pEnd - rightLen - 1);
        root.right = build(InorderIndex, rootIdx + 1, iEnd, postorder, pEnd - 1);
        return root;
    }


    /**
     * LeetCode #100
     * 判断两棵二叉树是否相同
     *
     * @param p 二叉树根节点 p
     * @param q 二叉树根节点 q
     * @return 如果两棵二叉树相同则返回true，否则返回false
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return dfs1(p, q);
    }

    private boolean dfs1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && dfs1(p.left, q.left) && dfs1(p.right, q.right);
    }


}
