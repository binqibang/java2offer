package algorithm;

import java.util.*;

public class Hot100 {

    /**
     * LeetCode #1: 两数之和
     * 给定一个整数数组nums和一个目标值target，请找出数组中和为目标值的两个数，并返回它们的数组索引
     *
     * @param nums   整数数组，包含需要查找的两个数
     * @param target 目标值，两个数之和应等于该值
     * @return 返回一个包含两个索引的整数数组，表示两个数在原数组中的位置
     */
    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                ans[0] = i;
                ans[1] = map.get(target - nums[i]);
            }
            map.put(nums[i], i);
        }
        return ans;
    }


    /**
     * LeetCode #2: 两数相加
     * 给定两个非空链表，代表两个非负整数，数字以逆序方式存储
     * 每个节点只包含一个数字，将两数相加，以同样的形式返回结果
     *
     * @param l1 第一个链表节点，代表第一个整数
     * @param l2 第二个链表节点，代表第二个整数
     * @return 返回一个新的链表节点，代表两个整数相加的结果
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 2 4 3
        // 5 6 4
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode();
        ListNode prev = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            int sum = v1 + v2 + carry;
            ListNode curr = new ListNode(sum % 10);
            carry = sum / 10;
            prev.next = curr;
            prev = curr;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return dummy.next;
    }


    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null, curr = head, next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    /**
     * LeetCode #3: 计算给定字符串中最长无重复字符的子串的长度
     *
     * @param s 输入的字符串
     * @return 返回最长无重复字符子串的长度
     */
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            char ch = s.charAt(r);
            if (map.containsKey(ch)) {
                l = Math.max(map.get(ch) + 1, l);
            }
            map.put(ch, r);
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }


    /**
     * LeetCode #3: 返回给定字符串中的最长回文子串。
     *
     * @param s 输入的字符串
     * @return 最长回文子串
     */
    public String longestPalindromeSubstring(String s) {
        int n = s.length();
        if (n == 1) {
            return s;
        }
        int maxLen = 1, maxStart = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int currLen = 1;
            int l = i - 1, r = i + 1;
            while (l >= 0 && s.charAt(l) == c) {
                l--;
                currLen++;
            }
            while (r < n && s.charAt(r) == c) {
                r++;
                currLen++;
            }
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
                currLen += 2;
            }
            if (currLen > maxLen) {
                maxLen = currLen;
                maxStart = l + 1;
            }
        }
        return s.substring(maxStart, maxStart + maxLen);
    }


    /**
     * LeetCode #15: 三数之和
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0
     * 找出所有满足条件且不重复的三元组。
     *
     * @param nums 输入的整数数组
     * @return 返回所有不重复的三元组
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // -1, 0, 1, 2, -1, -4
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        if (n < 3) {
            return ans;
        }
        Arrays.sort(nums);
        // -4 -1 -1 0 1 2
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = n - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> list = List.of(nums[i], nums[j], nums[k]);
                    ans.add(list);
                    while (j < k && nums[j + 1] == nums[j]) {
                        j++;
                    }
                    while (j < k && nums[k - 1] == nums[k]) {
                        k--;
                    }
                    j++;
                    k--;
                } else if (nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return ans;
    }


    /**
     * LeetCode #17
     */
    public List<String> letterCombinations(String digits) {
        Map<Integer, String> map = Map.of(
                2, "abc",
                3, "def",
                4, "ghi",
                5, "jkl",
                6, "mno",
                7, "pqrs",
                8, "tuv",
                9, "wxyz"
        );
        List<String> ans = new ArrayList<>();
        backtrack(digits, map, 0, new StringBuilder(), ans);
        return ans;
    }

    private void backtrack(String digits, Map<Integer, String> map, int idx, StringBuilder sb, List<String> ans) {
        if (idx == digits.length()) {
            ans.add(sb.toString());
            return;
        }
        String seq = map.get(digits.charAt(idx) - '0');
        for (int i = 0; i < seq.length(); i++) {
            sb.append(seq.charAt(i));
            backtrack(digits, map, idx + 1, sb, ans);
            sb.deleteCharAt(idx);
        }
    }


    /**
     * LeetCode #19
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
            // `n`越界
            if (fast == null) {
                return head;
            }
        }
        ListNode slow = dummy;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }


    /**
     * LeetCode #20
     */
    public boolean isValid(String s) {
        Map<Character, Character> pairs = Map.of(
                '(', ')',
                '[', ']',
                '{', '}'
        );
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (pairs.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty() || pairs.get(stack.pop()) != c) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * LeetCode #21
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode dummy = new ListNode();
        ListNode prev = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }
        prev.next = list1 == null ? list2 : list1;
        return dummy.next;
    }


    /**
     * LeetCode #22
     */
    public List<String> generateParenthesis(int n) {
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        sb.append("()");
        backtrack1(n, 1, sb, set);
        return new ArrayList<>(set);
    }

    private void backtrack1(int n, int idx, StringBuilder sb, Set<String> ans) {
        if (idx == n) {
            ans.add(sb.toString());
            return;
        }
        int length = sb.length();
        for (int i = 0; i <= length / 2; i++) {
            sb.insert(i, "()");
            backtrack1(n, idx + 1, sb, ans);
            sb.delete(i, i + 2);
        }
    }


    /**
     * LeetCode #23
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }


    /**
     * LeetCode #33
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                ans = mid;
            }
            // left = mid
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return ans;
    }


    /**
     * LeetCode #34
     */
    public int[] searchRange(int[] nums, int target) {
        int l = binarySearch(nums, target, true);
        int r = binarySearch(nums, target, false);
        return new int[]{l, r};
    }

    private int binarySearch(int[] nums, int target, boolean isLeft) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                index = mid;
                if (isLeft) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return index;
    }


    /**
     * LeetCode #39
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int idx = 0;
        List<Integer> comb = new ArrayList<>();
        backtrack2(candidates, target, idx, comb, ans);
        return ans;
    }

    private void backtrack2(int[] candidates, int target, int idx, List<Integer> comb, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(comb));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                comb.add(candidates[i]);
                backtrack2(candidates, target - candidates[i], i, comb, ans);
                comb.remove(comb.size() - 1);
            }
        }
    }


    /**
     * LeetCode #46
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        int idx = 0;
        List<Integer> perm = new ArrayList<>();
        backtrack3(nums, idx, perm, ans, used);
        return ans;
    }

    private void backtrack3(int[] nums, int idx, List<Integer> perm, List<List<Integer>> ans, boolean[] used) {
        if (idx == nums.length) {
            ans.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                perm.add(nums[i]);
                used[i] = true;
                backtrack3(nums, idx + 1, perm, ans, used);
                used[i] = false;
                perm.remove(perm.size() - 1);
            }
        }
    }


    /**
     * LeetCode #77
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        int idx = 1;
        List<Integer> comb = new ArrayList<>();
        backtrack4(n, k, idx, comb, ans);
        return ans;
    }

    private void backtrack4(int n, int k, int idx, List<Integer> comb, List<List<Integer>> ans) {
        if (comb.size() == k) {
            ans.add(new ArrayList<>(comb));
            return;
        }
        for (int i = idx; i <= n; i++) {
            comb.add(i);
            backtrack4(n, k, i + 1, comb, ans);
            comb.remove(comb.size() - 1);
        }
    }

    /**
     * LeetCode #105
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            index.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1, 0, inorder.length - 1, index);
    }

    private TreeNode build(int[] preorder, int pStart, int pEnd,
                           int iStart, int iEnd, Map<Integer, Integer> index) {
        if (pStart > pEnd || iStart > iEnd) {
            return null;
        }
        int rootVal = preorder[pStart];
        int rootIdx = index.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        int leftLen = rootIdx - iStart;
        root.left = build(preorder, pStart + 1, pStart + leftLen, iStart, rootIdx - 1, index);
        root.right = build(preorder, pStart + leftLen + 1, pEnd, rootIdx + 1, iEnd, index);
        return root;
    }


    /**
     * LeetCode #114
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (prev != null) {
                prev.left = null;
                prev.right = curr;
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
            prev = curr;
        }
    }


    /**
     * LeetCode #43
     */
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int m = num1.length();
        int n = num2.length();
        int[] ans = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int val1 = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int val2 = num2.charAt(j) - '0';
                ans[i + j + 1] += val1 * val2;
            }
        }
        for (int i = m + n - 1; i > 0; i--) {
            ans[i - 1] += ans[i] / 10;
            ans[i] %= 10;
        }
        int idx = ans[0] == 0 ? 1 : 0;
        StringBuilder sb = new StringBuilder();
        while (idx < m + n) {
            sb.append(ans[idx]);
            idx++;
        }
        return sb.toString();
    }


    /**
     * LeetCode #62
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * 大数除法，腾讯一面
     */
    public String divString(String dividend, int divisor) {
        int n = dividend.length();
        StringBuilder sb = new StringBuilder();
        int reminder = 0;
        for (int i = 0; i < n; i++) {
            int val = dividend.charAt(i) - '0' + 10 * reminder;
            int factor = val / divisor;
            reminder = val % divisor;
            sb.append(factor);
        }
        if (reminder == 0) {
            sb.append(".00");
        } else {
            sb.append(String.format("%.2f", (double) reminder / divisor).substring(1));
        }
        int i = 0;
        while (sb.charAt(i) == '0') {
            sb.deleteCharAt(i);
        }
        return sb.toString();
    }


    /**
     * LeetCode #150: 计算给定的逆波兰表达式（RPN）的值
     * 逆波兰表达式中，每个操作数可以是单个数字，每个运算符包括加法、减法、乘法和除法
     * 表达式中的元素顺序遵循逆波兰表达式的规则，即运算符位于其操作数之后
     *
     * @param tokens 一个字符串数组，表示逆波兰表达式的各个部分，包括操作数和运算符
     * @return 表达式的计算结果作为整数返回
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> numStack = new ArrayDeque<>();
        for (String token : tokens) {
            if (isNumber(token)) {
                numStack.push(Integer.parseInt(token));
            } else {
                int a = numStack.pop();
                int b = numStack.pop();
                switch (token) {
                    case "+":
                        numStack.push(a + b);
                        break;
                    case "-":
                        numStack.push(b - a);
                        break;
                    case "*":
                        numStack.push(a * b);
                        break;
                    case "/":
                        numStack.push(b / a);
                        break;
                    default:
                }
            }
        }
        return numStack.pop();
    }

    private boolean isNumber(String token) {
        return token.length() != 1 || (token.charAt(0) != '+' &&
                token.charAt(0) != '-' && token.charAt(0) != '*' && token.charAt(0) != '/');
    }

    /**
     * LeetCode #227: 计算一个算术表达式的值。
     * 仅支持加法、减法、乘法和除法，并且不考虑括号。
     *
     * @param s 表达式字符串，包括数字和运算符，不含括号。
     * @return 表达式的计算结果。
     */
    public int calculate(String s) {
        // 定义运算符的优先级，加减为1（低优先级），乘除为2（高优先级）
        Map<Character, Integer> priority = Map.of(
                '+', 1,
                '-', 1,
                '*', 2,
                '/', 2
        );
        // 移除表达式中的空格以避免解析错误
        s = s.replaceAll(" ", "");
        int n = s.length();
        Deque<Integer> nums = new ArrayDeque<>();
        // 防止开头为负数
        nums.push(0);
        Deque<Character> ops = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                ops.push(ch);
            } else if (ch == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    char top = ops.peek();
                    if (top != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pop();
                        break;
                    }
                }
            } else {
                if (Character.isDigit(ch)) {
                    // 构造完整的数字
                    int num = 0;
                    int j = i;
                    while (j < n && Character.isDigit(s.charAt(j))) {
                        num = num * 10 + s.charAt(j) - '0';
                        j++;
                    }
                    nums.push(num);
                    // 更新索引位置
                    i = j - 1;
                } else {
                    // (- 转化为 (0-
                    if (i > 0 && (s.charAt(i - 1) == '(' || s.charAt(i - 1) == '+'
                            || s.charAt(i - 1) == '-' || s.charAt(i - 1) == '*')) {
                        nums.push(0);
                    }
                    // 如果运算符栈不为空并且栈顶运算符的优先级大于等于当前运算符
                    while (!ops.isEmpty() && ops.peek() != '(' && priority.get(ops.peek()) >= priority.get(ch)) {
                        // 执行计算
                        calc(nums, ops);
                    }
                    ops.push(ch);
                }
            }
        }
        // 处理剩余的运算符
        while (!ops.isEmpty() && ops.peek() != '(') {
            calc(nums, ops);
        }
        return nums.pop();
    }


    private void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.size() < 2 || ops.isEmpty()) {
            return;
        }
        int a = nums.pop();
        int b = nums.pop();
        char op = ops.pop();
        int ans = 0;
        switch (op) {
            case '+':
                ans = a + b;
                break;
            case '-':
                ans = b - a;
                break;
            case '*':
                ans = a * b;
                break;
            case '/':
                ans = b / a;
                break;
            default:
        }
        nums.push(ans);
    }


    public int compare (String version1, String version2) {
        String[] ss1 = version1.split("\\.");
        String[] ss2 = version2.split("\\.");
        int len1 = ss1.length, len2 = ss2.length;
        for (int i = 0; i < len1 || i < len2; i++) {
            int v1 = i < len1 ? Integer.parseInt(ss1[i]) : 0;
            int v2 = i < len2 ? Integer.parseInt(ss2[i]) : 0;
            if (v1 > v2) {
                return 1;
            } else if (v1 < v2) {
                return -1;
            }
        }
        return 0;
    }



    /**
     * 定义一个LRU(Least Recently Used)缓存策略的类
     */
    static class MyLRUCache {
        // 缓存容量和当前大小
        private int capacity, size;
        // 虚拟头结点和尾结点，便于操作双向链表
        private DLinkedNode head, tail;
        // 使用HashMap存储键值对，键为缓存键，值为双向链表节点
        private Map<Integer, DLinkedNode> cache;

        /**
         * 构造函数，初始化LRU缓存
         * @param capacity 缓存的容量
         */
        public MyLRUCache(int capacity) {
            this.capacity = capacity;
            size = 0;
            cache = new HashMap<>();
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        /**
         * 获取给定键对应的值如果键不存在，返回-1
         * @param k 键
         * @return 对应的值或-1（如果键不存在）
         */
        public int get(int k) {
            DLinkedNode node = cache.get(k);
            if (node == null) {
                return -1;
            }
            // 将访问的节点移动到链表头部，以维护最近使用顺序
            moveFirst(node);
            return node.value;
        }

        /**
         * 将给定节点移动到链表头部
         * @param node 要移动的节点
         */
        private void moveFirst(DLinkedNode node) {
            // 先从链表中移除节点，再将其添加到链表头部
            remove(node);
            addFirst(node);
        }

        /**
         * 将节点添加到链表头部
         * @param node 要添加的节点
         */
        private void addFirst(DLinkedNode node) {
            node.next = head.next;
            head.next.prev = node;
            node.prev = head;
            head.next = node;
        }

        /**
         * 从链表中移除节点
         * @param node 要移除的节点
         */
        private void remove(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        /**
         * 设置或更新给定键的值如果键不存在，插入键值对；如果键存在，更新其值
         * @param k 键
         * @param v 值
         */
        public void set(int k, int v) {
            DLinkedNode query = cache.get(k);
            if (query == null) {
                DLinkedNode node = new DLinkedNode(k, v);
                cache.put(k, node);
                addFirst(node);
                size++;
                // 如果超出容量，移除最久未使用的节点
                if (size > capacity) {
                    cache.remove(tail.prev.key);
                    remove(tail.prev);
                    size--;
                }
            } else {
                query.value = v;
                // 更新节点位置，移动到链表头部
                moveFirst(query);
            }
        }
    }

    // 双向链表节点类
    static class DLinkedNode {
        int key, value;
        DLinkedNode prev, next;

        public DLinkedNode() {}

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

