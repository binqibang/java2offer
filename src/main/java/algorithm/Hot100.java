package algorithm;

import java.util.*;

public class Hot100 {

    static class Tuple {
        TreeNode node;
        boolean visited;

        public Tuple(TreeNode node, boolean visited) {
            this.node = node;
            this.visited = visited;
        }
    }

    /**
     * LeetCode #94
     * 返回二叉树的中序遍历结果，该方法使用迭代方式实现中序遍历，而不是传统的递归方式
     *
     * @param root 二叉树的根节点，如果根节点为null，则返回一个空列表
     * @return 返回一个包含中序遍历结果的整数列表
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> order = new ArrayList<>();
        if (root == null) {
            return order;
        }
        Deque<Tuple> stack = new LinkedList<>();
        stack.push(new Tuple(root, false));
        while (!stack.isEmpty()) {
            Tuple curr = stack.pop();
            if (curr.node != null) {
                if (curr.visited) {
                    order.add(curr.node.val);
                } else {
                    stack.push(new Tuple(curr.node.right, false));
                    stack.push(new Tuple(curr.node, true));
                    stack.push(new Tuple(curr.node.left, false));
                }
            }
        }
        return order;
    }


    /**
     * LeetCode #104
     * 计算二叉树的最大深度，最大深度是指从根节点到最远叶子节点的最长路径上的节点数
     *
     * @param root 二叉树的根节点
     * @return 返回二叉树的最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lDepth = maxDepth(root.left);
        int rDepth = maxDepth(root.right);
        return Math.max(lDepth, rDepth) + 1;
    }


    /**
     * LeetCode #226
     * 翻转二叉树，即交换每个节点的左右子树
     *
     * @param root 二叉树的根节点，反转操作的起点。
     * @return 返回反转后的二叉树的根节点。
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }


    /**
     * LeetCode #101
     * 判断给定的二叉树是否为对称二叉树
     * 对称二叉树定义：对于树中任意两个对称节点，它们的值相等，且它们的左子树和右子树也对称
     *
     * @param root 二叉树的根节点
     * @return 如果二叉树是对称的，则返回true；否则返回false
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && dfs(p.left, q.right) && dfs(p.right, q.left);
    }


    /**
     * LeetCode #543
     * 计算二叉树的直径，直径定义为二叉树中任意两个节点之间最长路径上的边数
     *
     * @param root 二叉树的根节点
     * @return 返回二叉树的直径
     */
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return diameter;
    }

    private int diameter = 0;

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lDepth = depth(root.left);
        int rDepth = depth(root.right);
        // 实际上，直径等于二叉树中某个节点的左子树深度加上右子树深度的最大值
        diameter = Math.max(lDepth + rDepth, diameter);
        return Math.max(lDepth, rDepth) + 1;
    }


    /**
     * LeetCode #102
     * 二叉树的层序遍历，从上到下按行输出
     *
     * @param root 二叉树的根节点
     * @return 返回二叉树的层序遍历序列
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> order = new ArrayList<>();
        if (root == null) {
            return order;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode curr = queue.poll();
                list.add(curr.val);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            order.add(list);
        }
        return order;
    }


    /**
     * LeetCode #108
     * 给一个递增整数数组 nums，将其转换为一棵平衡二叉搜索树
     *
     * @param nums 整数数组，严格递增顺序排列
     * @return 平衡二叉树根节点
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);
        return root;
    }


    private long prev = Long.MIN_VALUE;

    /**
     * LeetCode #98
     * 判断给定的二叉树是否为有效的二叉搜索树
     *
     * @param root 二叉树的根节点
     * @return 如果是有效的二叉搜索树则返回true，否则返回false
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (prev >= root.val) {
            return false;
        }
        prev = root.val;
        return isValidBST(root.right);
    }

    /**
     * LeetCode #230
     * 二叉搜索树中第 K 小的元素
     *
     * @param root 二叉搜索树的根节点
     * @param k    第k小的元素的位置
     * @return 第k小的元素的值
     */
    public int kthSmallest(TreeNode root, int k) {
        dfs1(root, k);
        return kthSmallest;
    }

    private int kthSmallest;
    private int cnt = 1;

    private void dfs1(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        dfs1(root.left, k);
        if (cnt == k) {
            kthSmallest = root.val;
        }
        cnt++;
        dfs1(root.right, k);
    }


    /**
     * LeetCode 437
     * 路径总和III，计算二叉树中所有路径的和等于给定值的路径总数，可以不经过根节点
     *
     * @param root      二叉树的根节点
     * @param targetSum 目标和
     * @return 满足条件的路径总数
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ans = targetSum(root, targetSum);
        ans += pathSum(root.left, targetSum);
        ans += pathSum(root.right, targetSum);
        return ans;
    }

    private int targetSum(TreeNode root, int target) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        if (root.val == target) {
            count++;
        }
        count += targetSum(root.left, target - root.val);
        count += targetSum(root.right, target - root.val);
        return count;
    }


    /**
     * LeetCode #48
     * 旋转图像，将图像顺时针旋转90度
     *
     * @param matrix 二维数组，表示图像
     */
    public void rotate(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // transpose
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        // reverse each row
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }


    /**
     * LeetCode #74
     * 每行中的整数从左到右按非严格递增顺序排列，每行的第一个整数大于前一行的最后一个整数，判断给定的目标值是否在矩阵中
     *
     * @param matrix 二维数组
     * @param target 目标值
     * @return 如果在矩阵中则返回true，否则返回false
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int x = mid / n, y = mid % n;
            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] < target) {
                low++;
            } else {
                high--;
            }
        }
        return false;
    }


    /**
     * LeetCode #240
     * 二维数组中查找目标值，每行的元素从左到右升序排列，每列的元素从上到下升序排列
     *
     * @param matrix 二维数组
     * @param target 目标值
     * @return 如果在数组中则返回true，否则返回false
     */
    public boolean searchMatrixII(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] < target) {
                x++;
            } else {
                y--;
            }
        }
        return false;
    }


    /**
     * LeetCode #34
     * 在排序数组中查找元素的第一个和最后一个位置
     *
     * @param nums   整数数组，升序排列
     * @param target 目标值
     * @return 目标值在数组中的起始位置和结束位置，如果数组中不存在目标值则返回[-1, -1]
     */
    public int[] searchRange(int[] nums, int target) {
        int left = search(nums, target, true);
        int right = search(nums, target, false);
        return new int[]{left, right};
    }

    private int search(int[] nums, int target, boolean isLeftBound) {
        int pos = -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                pos = mid;
                if (isLeftBound) {
                    right--;
                } else {
                    left++;
                }
            } else if (nums[mid] < target) {
                left++;
            } else {
                right--;
            }
        }
        return pos;
    }


    /**
     * LeetCode #136
     * 只出现一次的数字，数组中除了一个数字只出现一次，其他数字都出现两次，找出只出现一次的数字
     *
     * @param nums 整数数组
     * @return 只出现一次的数字
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }


    /**
     * LeetCode #20
     * 判断字符串中的括号是否合法
     *
     * @param s 字符串，由'('，')'，'{'，'}'，'['，']'组成
     * @return 如果字符串中的括号合法则返回true，否则返回false
     */
    public boolean isValid(String s) {
        // (())[]
        if (s == null || s.isEmpty()) {
            return true;
        }
        Map<Character, Character> pairs = Map.of(
                '(', ')',
                '{', '}',
                '[', ']'
        );
        Deque<Character> stack = new LinkedList<>();
        for (char ch : s.toCharArray()) {
            if (pairs.containsKey(ch)) {
                stack.push(ch);
            } else {
                if (stack.isEmpty() || pairs.get(stack.peek()) != ch) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}

