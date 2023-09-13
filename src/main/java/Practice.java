import java.util.*;

public class Practice {

    /**
     * LeetCode #1
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
     * LeetCode #2
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int sum = val1 + val2 + carry;
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
        if (carry != 0) {
            prev.next = new ListNode(carry);
        }
        return dummy.next;
    }


    /**
     * LeetCode #3
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int ans = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, right);
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }


    /**
     * LeetCode #5
     */
    public String longestPalindromeSubstring(String s) {
        int n = s.length();
        int maxLen = 0;
        int maxStart = 0;
        for (int i = 0; i < n; i++) {
            int left = i - 1;
            int right = i + 1;
            int currLen = 1;
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                left--;
                currLen++;
            }
            while (right < n && s.charAt(right) == s.charAt(i)) {
                right++;
                currLen++;
            }
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                currLen += 2;
            }
            if (currLen > maxLen) {
                maxLen = currLen;
                maxStart = left + 1;
            }
        }
        return s.substring(maxStart, maxStart + maxLen);
    }


    /**
     * LeetCode #15
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> curr = List.of(nums[i], nums[left], nums[right]);
                    ans.add(curr);
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
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
        return new int[] {l, r};
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
}
