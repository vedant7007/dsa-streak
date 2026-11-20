class Solution {
    public void reorderList(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode prev = null, cur = slow.next;
        slow.next = null;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        ListNode a = head, b = prev;
        while (b != null) {
            ListNode t1 = a.next, t2 = b.next;
            a.next = b;
            b.next = t1;
            a = t1;
            b = t2;
        }
    }
}
