package algorithm;

import java.util.*;


/**
 * LeetCode Daily Practice
 * @author binqibang
 */
public class Daily {

    /**
     * LeetCode #2970
     * @date 2024/7/10
     */
    public int incremovableSubarrayCount(int[] nums) {
        // 5 3 4 6 7
        int n = nums.length;
        int ans = 0;
        List<List<Integer>> list = new ArrayList<>();
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                if (isIncremovable(nums, l, r)) {
                    ans += 1;
                    list.add(List.of(l, r));
                }
            }
        }
        System.out.println(list);
        return ans;
    }

    private boolean isIncremovable(int[] nums, int l, int r) {
        int n = nums.length;
        // [0, l - 1]
        for (int i = 1; i < l; i++) {
            if (nums[i - 1] >= nums[i]) {
                return false;
            }
        }
        // [r + 1, n - 1]
        for (int i = r + 2; i < n; i++) {
            if (nums[i - 1] >= nums[i]) {
                return false;
            }
        }
        return l - 1 < 0 || r + 1 >= n || nums[l - 1] < nums[r + 1];
    }

    /**
     * LeetCode #165
     * @date 2024/7/11
     */
    public int compareVersion(String version1, String version2) {
        String[] revisions1 = version1.split("\\.");
        String[] revisions2 = version2.split("\\.");
        int l1 = revisions1.length, l2 = revisions2.length;
        int compare = 0;
        for (int i = 0; i < l1 || i < l2; i++) {
            int v1 = i < l1 ? Integer.parseInt(revisions1[i]) : 0;
            int v2 = i < l2 ? Integer.parseInt(revisions2[i]) : 0;
            if (v1 > v2) {
                compare = 1;
                break;
            }
            if (v1 < v2) {
                compare = -1;
                break;
            }
        }
        return compare;
    }


    /**
     * LeetCode #165
     * @date 2024/7/12
     */
    public int[] numberGame(int[] nums) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
        }
        int[] arr = new int[nums.length];
        int idx = 0;
        for (int i = 0; i < nums.length / 2; i++) {
            int v1 = minHeap.poll();
            int v2 = minHeap.poll();
            arr[idx++] = v2;
            arr[idx++] = v1;
        }
        return arr;
    }


    /**
     * LeetCode #107
     * @date 2024/7/12
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> order = new ArrayList<>();
        if (root == null) {
            return order;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                list.add(curr.val);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            order.add(0, list);
        }
        return order;
    }


    /**
     * LeetCode #3011
     * @date 2024/7/13
     */
    public boolean canSortArray(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j] && Integer.bitCount(nums[i])
                        != Integer.bitCount(nums[j])) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * LeetCode #106
     * @date 2024/7/13
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> indices = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indices.put(inorder[i], i);
        }
        return build(indices, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    private TreeNode build(Map<Integer, Integer> indices,
                           int inStart, int inEnd,
                           int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int rootIdx = indices.get(rootVal);
        int rightLen = inEnd - rootIdx;
        root.left = build(indices, inStart, rootIdx - 1,
                postorder, postStart, postEnd - rightLen - 1);
        root.right = build(indices, rootIdx + 1, inEnd,
                postorder, postEnd - rightLen, postEnd - 1);
        return root;
    }
}
