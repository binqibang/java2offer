package algorithm;

import java.util.*;

/**
 * LeetCode Weekly Contest
 * @author binqibang
 */
public class Weekly {
    /**
     * Weekly-contest-406 #1
     * <br>
     * 获取字符串s中最小的字典序排列。
     *
     * @param s 输入的字符串，包含数字字符。
     * @return 返回最小字典序排列的字符串。
     */
    public String getSmallestString(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; i < n - 1; i++) {
            int a = s.charAt(i) - '0';
            int b = s.charAt(i + 1) - '0';
            // 如果当前数字比下一个数字大且它们的奇偶性相同，则进行交换操作
            if (a > b && (a % 2 == b % 2)) {
                sb.append(b);
                sb.append(a);
                sb.append(s.substring(i + 2));
                break;
            } else {
                sb.append(a);
            }
        }
        // 如果遍历到了最后一个字符，则将最后一个字符添加到结果字符串中
        if (i == n - 1) {
            sb.append(s.charAt(n - 1));
        }
        return sb.toString();
    }



    /**
     * Weekly-contest-406 #2
     * <br>
     * 根据给定的整数数组和一个链表头节点，创建一个新的链表，新链表中不包含数组中出现的元素。
     *
     * @param nums 整数数组，用于标记需要从链表中移除的元素。
     * @param head 原始链表的头节点。
     * @return 新链表的头节点，新链表中不包含数组中出现的元素。
     */
    public ListNode modifiedList(int[] nums, ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        // prev用于跟踪当前节点的前一个节点，以便在需要时删除当前节点
        ListNode prev = dummy;
        // curr用于遍历原始链表
        ListNode curr = head;
        while (curr != null) {
            // 如果当前节点的值在数组中出现，则删除该节点
            if (set.contains(curr.val)) {
                prev.next = curr.next;
            } else {
                // 如果当前节点的值不在数组中，更新prev为当前节点
                prev = curr;
            }
            // 移动到下一个节点
            curr = curr.next;
        }
        return dummy.next;
    }


    /**
     * Weekly-contest-406 #3, 4
     * <br>
     * 计算将一个m*n的矩形切割成小矩形的最小成本。
     * 切割线可以是水平或垂直的，每条切割线的成本由其位置决定。
     *
     * @param m 矩形的行数
     * @param n 矩形的列数
     * @param horizontalCut 水平切割线的成本数组
     * @param verticalCut 垂直切割线的成本数组
     * @return 返回切割的最小成本
     */
    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        int cost = 0;

        // 降序排序
        Arrays.sort(horizontalCut);
        for (int i = 0; i < horizontalCut.length / 2; i++) {
            int temp = horizontalCut[i];
            horizontalCut[i] = horizontalCut[horizontalCut.length - 1 - i];
            horizontalCut[horizontalCut.length - 1 - i] = temp;
        }
        Arrays.sort(verticalCut);
        for (int i = 0; i < verticalCut.length / 2; i++) {
            int temp = verticalCut[i];
            verticalCut[i] = verticalCut[verticalCut.length - 1 - i];
            verticalCut[verticalCut.length - 1 - i] = temp;
        }

        int i = 0, j = 0;   // 当前横、竖切割点
        int h = 0, v = 0;   // 当前分块数
        while (i < m - 1 && j < n - 1) {
            if (horizontalCut[i] < verticalCut[j]) {
                // 如果水平切割线位置更小，进行水平切割
                // 水平切一刀后，剩下每个分块都需要垂直切
                cost += (h + 1) * verticalCut[j];
                j++;
                v++;
            } else {
                // 如果垂直切割线位置更小，进行垂直切割
                // 垂直切一刀后，剩下每个分块都需要水平切
                cost += (v + 1) * horizontalCut[i];
                i++;
                h++;
            }
        }

        // 处理剩余的水平切割线
        while (i < m - 1) {
            cost += (v + 1) * horizontalCut[i];
            i++;
        }

        // 处理剩余的垂直切割线
        while (j < n - 1) {
            cost += (h + 1) * verticalCut[j];
            j++;
        }

        return cost;
    }


    public int[] getFinalState(int[] nums, int k, int multiplier) {
        // nums = [2,1,3,5,6], k = 5, multiplier = 2
        for (int i = 0; i < k; i++) {
            int min = getMinIdx(nums);
            nums[min] = nums[min] * multiplier;
        }
        return nums;
    }

    private int getMinIdx(int[] nums) {
        int minVal = nums[0];
        int minIdx = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < minVal) {
                minIdx = i;
                minVal = nums[i];
            }
        }
        return minIdx;
    }


    public int countPairs(int[] nums) {
        int count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int num1 = nums[i];
                int num2 = nums[j];
                if (check(String.valueOf(num1), String.valueOf(num2))) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean check(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 == len2) {
            StringBuilder diff1 = new StringBuilder();
            StringBuilder diff2 = new StringBuilder();
            for (int i = 0; i < len1; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    diff1.append(s1.charAt(i));
                    diff2.append(s2.charAt(i));
                }
            }
            if (diff1.length() == 0) {
                return true;
            }
            if (diff1.length() == 2) {
                return diff1.charAt(0) == diff2.charAt(1) && diff1.charAt(1) == diff2.charAt(0);
            }
            return false;
        } else if (len1 < len2) {
            StringBuilder sb = new StringBuilder(s1);
            sb.insert(0, "0".repeat(len2 - len1));
            return check(sb.toString(), s2);
        } else {
            StringBuilder sb = new StringBuilder(s2);
            sb.insert(0, "0".repeat(len1 - len2));
            return check(s1, sb.toString());
        }
    }

}
