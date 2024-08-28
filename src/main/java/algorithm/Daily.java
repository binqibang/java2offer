package algorithm;

import java.util.*;
import java.util.stream.Collectors;


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
     * LeetCode #165 比较版本号
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
     * LeetCode #2974 最小数字游戏
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

    private TreeNode build(Map<Integer, Integer> inorder, int inStart, int inEnd,
                           int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int rootIdx = inorder.get(rootVal);
        int rightLen = inEnd - rootIdx;
        root.left = build(inorder, inStart, rootIdx - 1,
                postorder, postStart, postEnd - rightLen - 1);
        root.right = build(inorder, rootIdx + 1, inEnd,
                postorder, postEnd - rightLen, postEnd - 1);
        return root;
    }


    /**
     * LeetCode #721 账户合并
     * @date 2024/7/15
     * @param accounts 一个列表，其中每个元素代表一个账户，账户包含一个名字和多个电子邮件地址
     * @return 返回一个列表，其中每个元素代表一个合并后的账户，账户包含一个名字和所有相关的电子邮件地址
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 初始化两个映射，用于快速查找电子邮件地址的ID和账户名
        Map<String, Integer> email2id = new HashMap<>();
        Map<String, String> email2name = new HashMap<>();
        // 初始化计数器，用于为新电子邮件地址分配ID
        int emailCount = 0;
        for (var account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if (!email2id.containsKey(email)) {
                    email2id.put(email, emailCount++);
                    email2name.put(email, name);
                }
            }
        }
        DisjointSetUnion dsu = new DisjointSetUnion(emailCount);
        // 再次遍历账户列表，使用并查集将属于同一账户的电子邮件地址连接起来
        for (var account : accounts) {
            String firstEmail = account.get(1);
            int p = email2id.get(firstEmail);
            for (int i = 2; i < account.size(); i++) {
                int q = email2id.get(account.get(i));
                dsu.union(p, q);
            }
        }
        Map<Integer, List<String>> emailId2emails = new HashMap<>();
        for (var email : email2id.keySet()) {
            // 找到并查集的根节点，即合并后的某邮箱ID
            int root = dsu.find(email2id.get(email));
            List<String> account = emailId2emails.getOrDefault(root, new ArrayList<>());
            account.add(email);
            emailId2emails.put(root, account);
        }
        List<List<String>> merged = new ArrayList<>();
        for (List<String> emails : emailId2emails.values()) {
            Collections.sort(emails);
            String name = email2name.get(emails.get(0));
            List<String> account = new ArrayList<>();
            account.add(name);
            account.addAll(emails);
            merged.add(account);
        }
        return merged;
    }



    static class DisjointSetUnion {
        private int[] parent; // 存储每个元素的父节点
        private int[] rank;   // 存储以每个元素为根的树的高度（或称为“秩”）
        private int count;    // 连通块个数

        public DisjointSetUnion(int n) {
            parent = new int[n];
            rank = new int[n];
            count = n;
            for (int i = 0; i < n; ++i) {
                parent[i] = i; // 初始化，每个元素自己构成一个集合
                rank[i] = 1;   // 初始时，每个集合的树高为1（只有自己一个节点）
            }
        }

        // 查找x的根节点，同时执行路径压缩
        private int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]); // 路径压缩
            }
            return parent[x];
        }

        // 合并包含x和y的集合，采用按秩合并
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) { // 如果x和y不在同一个集合中
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                count--;
            }
        }


        public int getRootCount() {
            return count;
        }
    }


    /**
     * LeetCode #2956 找到两个数组中的公共元素
     * @date 2024/7/16
     *
     * @param nums1 第一个数组，不为空且长度至少为1
     * @param nums2 第二个数组，不为空且长度至少为1
     * @return 返回一个包含两个整数的数组，第一个整数表示nums1中包含nums2元素的个数，第二个整数表示nums2中包含nums1元素的个数
     */
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        int[] ans = new int[2];
        int cnt1 = 0;   // 统计nums1出现在nums2中的元素个数
        Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        for (int num : nums1) {
            if (set2.contains(num)) {
                cnt1++;
            }
        }
        ans[0] = cnt1;  // 统计nums1出现在nums2中的元素个数
        int cnt2 = 0;
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        for (int num : nums2) {
            if (set1.contains(num)) {
                cnt2++;
            }
        }
        ans[1] = cnt2;
        return ans;
    }


    /**
     * LeetCode #839 计算字符串数组中相似组的数量
     * @date 2024/7/16
     *
     * @param strs 字符串数组，每个字符串代表一个元素
     * @return 返回相似组的数量
     */
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        DisjointSetUnion dsu = new DisjointSetUnion(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 如果两个字符串相似，则将它们合并到同一个组中
                if (isSimilar(strs[i], strs[j])) {
                    dsu.union(i, j);
                }
            }
        }
        // 返回DSU中根节点的数量，即相似组的数量
        return dsu.getRootCount();
    }


    public boolean isSimilar(String s1, String s2) {
        int diff = 0;
        int pos1 = -1, pos2 = -1;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
                if (diff > 2) {
                    return false;
                }
                if (pos1 == -1) {
                    pos1 = i;
                } else {
                    pos2 = i;
                }
            }
        }
        return diff == 0 ||
                diff == 2 && s1.charAt(pos1) == s2.charAt(pos2) && s1.charAt(pos2) == s2.charAt(pos1);
    }


    /**
     * LeetCode #88 合并两个有序数组
     * @date 2024/7/17
     *
     * @param nums1 第一个有序数组，合并后的元素将存储在这个数组中
     * @param m nums1中原有的元素个数
     * @param nums2 第二个有序数组，其元素将被合并到nums1中
     * @param n nums2中原有的元素个数
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 复制nums1中的前m个元素到nums1Back，以保留原数组内容
        int[] nums1Back = Arrays.copyOf(nums1, m);
        int p1 = 0, p2 = 0, p = 0;
        while (p1 < m && p2 < n) {
            if (nums1Back[p1] < nums2[p2]) {
                nums1[p++] = nums1Back[p1++];
            } else {
                nums1[p++] = nums2[p2++];
            }
        }
        // 如果nums1Back中还有剩余元素，将其添加到nums1中
        while (p1 < m) {
            nums1[p++] = nums1Back[p1++];
        }
        // 如果nums2中还有剩余元素，将其添加到nums1中
        while (p2 < n) {
            nums1[p++] = nums2[p2++];
        }
    }


    /**
     * LeetCode #27 移除元素
     * @date 2024/7/17
     *
     * @param nums 原始数组，将在原地进行修改
     * @param val 需要从数组中移除的值
     * @return 移除指定值后的新数组长度
     */
    public int removeElement(int[] nums, int val) {
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        return left;
    }


    /**
     * LeetCode #2740 找出分区值
     *
     * @param nums 输入的整数数组
     * @return 返回数组分割后的最小差值
     */
    public int findValueOfPartition(int[] nums) {
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {
            ans = Math.min(ans, Math.abs(nums[i] - nums[i + 1]));
        }
        return ans;
    }

}
