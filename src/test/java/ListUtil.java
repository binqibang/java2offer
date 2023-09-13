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
        ListNode iter = head;
        while (iter != null) {
            if (iter.next != null) {
                System.out.print(iter.val + " -> ");
            } else {
                System.out.print(iter.val);
            }
            iter = iter.next;
        }
        System.out.println();
    }
}