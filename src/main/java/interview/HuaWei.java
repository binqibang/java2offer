package interview;

import java.util.*;

public class HuaWei {

    /**
     * 字符串重排为ASCII码顺序
     */
    public static String reorder(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }


    /**
     * 求出现次数最多的元素，如果出现次数一样的返回最大值
     */
    public static int mostRepeatedNum(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> {
            if (a.getValue() == b.getValue()) {
                return b.getKey() - a.getKey();
            } else {
                return b.getValue() - a.getValue();
            }
        });
        System.out.println(list);
        return list.get(0).getKey();
    }

    /**
     * 大数加法
     */
    public static String addStrings(String num1, String num2) {
        int p1 = num1.length() - 1, p2 = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (p1 >= 0 || p2 >= 0) {
            int v1 = p1 >= 0 ? num1.charAt(p1) - '0' : 0;
            int v2 = p2 >= 0 ? num2.charAt(p2) - '0' : 0;
            int sum = v1 + v2 + carry;
            sb.append(sum % 10);
            carry = sum / 10;
            p1--;
            p2--;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }


    /**
     * 大数减法
     */
    public static String subStrings(String num1, String num2) {
        char sign = '+';
        if (!compare(num1, num2)) {
            sign = '-';
            String tmp = num1;
            num1 = num2;
            num2 = tmp;
        }
        int borrow = 0;
        StringBuilder sb = new StringBuilder();
        // 从后往前逐位相减
        int p1 = num1.length() - 1, p2 = num2.length() - 1;
        while (p1 >= 0 || p2 >= 0) {
            int v1 = p1 >= 0 ? num1.charAt(p1) - '0' : 0;
            int v2 = p2 >= 0 ? num2.charAt(p2) - '0' : 0;
            int diff = v1 - v2 - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            sb.append(diff);
            p1--;
            p2--;
        }
        sb.reverse();
        // 去除无用`0`
        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        if (sign == '+') {
            return sb.toString();
        } else {
            return sign + sb.toString();
        }
    }

    private static boolean compare(String num1, String num2) {
        if (num1.length() == num2.length()) {
            return num1.compareTo(num2) > 0;
        } else {
            return num1.length() > num2.length();
        }
    }


    /**
     * 无重复字符最长子串
     */
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> indices = new HashMap<>();
        int left = 0, ans = 0;
        int maxIdx = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (indices.containsKey(ch)) {
                int lastIdx = indices.get(ch);
                if (lastIdx + 1 > left) {
                    left = lastIdx + 1;
                }
            }
            indices.put(ch, right);
            if (ans < right - left + 1) {
                ans = right - left + 1;
                maxIdx = left;
            }
        }
        System.out.println(s.substring(maxIdx, maxIdx + ans));
        return ans;
    }


    /**
     * 三数之和
     */
    public static List<List<Integer>> threeSum(int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> triples = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            // 避免重复答案
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    triples.add(List.of(nums[i], nums[left], nums[right]));
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    while (left < right && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return triples;
    }


    /**
     * 最接近的三数之和
     */
    public static int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 10000000;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 和为目标值，直接返回
                if (sum == target) {
                    return target;
                }
                // 比较和目标值的差绝对值
                if (Math.abs(sum - target) < Math.abs(ans - target)) {
                    ans = sum;
                }
                if (sum > target) {
                    while (j < k && nums[k - 1] == nums[k]) {
                        k--;
                    }
                    k--;
                } else {
                    while (j < k && nums[j + 1] == nums[j]) {
                        j++;
                    }
                    j++;
                }
            }
        }
        return ans;
    }

    /**
     * 有效的括号，输出括号对数
     */
    public static int isValid(String s) {
        int n = s.length();
        if (n % 2 != 0) {
            return -1;
        }
        Map<Character, Character> pairs = Map.of(
                '[', ']',
                '(', ')',
                '{', '}'
        );
        int count = 0;
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            // 左括号直接入栈
            if (pairs.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty() || pairs.get(stack.pop()) != c) {
                    return -1;
                }
                count++;
            }
        }
        if (stack.size() == 0) {
            return count;
        } else {
            return -1;
        }
    }


    /**
     * 最长元音子串
     */
    public static String longestVowelSubstring(String s) {
        String vowels = "aeiouAEIOU";
        int maxLen = 0, curLen = 0;
        int start = -1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (vowels.indexOf(ch) != -1) {
                curLen++;
                if (curLen > maxLen) {
                    maxLen = curLen;
                    start = i - maxLen + 1;
                }
            } else {
                curLen = 0;
            }
        }
        return start == -1 ? "" : s.substring(start, start + maxLen);
    }


    public static String longestWord(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        String longest = "";
        for (String word : words) {
            if (trie.search(word)) {
                if (word.length() > longest.length() || (word.length() == longest.length() && word.compareTo(longest) < 0)) {
                    longest = word;
                }
            }
        }
        return longest;
    }


    static class Trie {
        Trie[] children;
        boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new Trie();
                }
                node = node.children[idx];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = this;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null || !node.children[idx].isEnd) {
                    return false;
                }
                node = node.children[idx];
            }
            return node != null && node.isEnd;
        }
    }


    /**
     * 字母异位词分组
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> group = new HashMap<>();
        for (String s : strs) {
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            String key = Arrays.toString(count);
            if (!group.containsKey(key)) {
                group.put(key, new ArrayList<>());
            }
            group.get(key).add(s);
        }
        return new ArrayList<>(group.values());
    }


    /**
     * 两个相同字符之间的最长子字符串的长度
     */
    public static int maxLengthBetweenEqualCharacters(String s) {
        int[] firstIdx = new int[26];
        Arrays.fill(firstIdx, -1);
        int maxLen = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (firstIdx[c - 'a'] < 0) {
                firstIdx[c - 'a'] = i;
            } else {
                maxLen = Math.max(maxLen, i - firstIdx[c - 'a'] - 1);
            }
        }
        return maxLen;
    }


    public static int compress(char[] chars) {
        int n = chars.length;
        int write = 0, read = 0;
        while (read < n) {
            char curr = chars[read];
            int count = 0;
            while (read < n && chars[read] == curr) {
                count++;
                read++;
            }
            chars[write++] = curr;
            if (count > 1) {
                String s = String.valueOf(count);
                for (char c : s.toCharArray()) {
                    chars[write++] = c;
                }
            }
        }
        System.out.println(Arrays.toString(chars));
        return write;
    }


    public static void main(String[] args) {
        String s = "huawei";
        System.out.println(reorder(s));

        int[] nums = {1, 2, 2, 3, 4, 5, 5, 6};
        System.out.println(mostRepeatedNum(nums));

        String num1 = "6854646", num2 = "1641654654684";
        System.out.println(addStrings(num1, num2));
        System.out.println(subStrings(num1, num2));

        String s1 = "hwciuwehvfbaofiheui";
        System.out.println(lengthOfLongestSubstring(s1));

        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums1, -2));

        int[] nums2 = {-1, 2, 1, -4, 8, 3, 6};
        int target = 8;
        System.out.println(threeSumClosest(nums2, target));

        String s2 = "()[]{}";
        System.out.println(isValid(s2));

        System.out.println(longestVowelSubstring(s1));

        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));

        char[] chars = {'a', 'a', 'b', 'd', 'd', 'c'};
        System.out.println(compress(chars));
    }
}
