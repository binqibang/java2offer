package algorithm;

public class ListUtil {
    private static ListNode addFirst(ListNode head, int val) {
        ListNode curr = new ListNode(val);
        curr.next = head;
        return curr;
    }

    public static ListNode createList(int[] values) {
        int n = values.length;
        ListNode head = new ListNode(values[n-1]);
        for (int i = n - 2; i >= 0 ; i--) {
            head = addFirst(head, values[i]);
        }
        return head;
    }

    public static void printList(ListNode head) {
        String s = toString(head);
        System.out.println(s);
    }

    public static String toString(ListNode head) {
        ListNode iter = head;
        StringBuilder sb = new StringBuilder();
        while (iter != null) {
            if (iter.next != null) {
                sb.append(iter.val).append(" -> ");
            } else {
                sb.append(iter.val);
            }
            iter = iter.next;
        }
        return sb.toString();
    }
}