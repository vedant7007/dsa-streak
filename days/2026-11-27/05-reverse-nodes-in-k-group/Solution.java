class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        while (true) {
            ListNode end = prev;
            for (int i = 0; i < k && end != null; i++) end = end.next;
            if (end == null) break;
            ListNode start = prev.next, nxt = end.next;
            ListNode p = nxt, cur = start;
            while (cur != nxt) {
                ListNode t = cur.next;
                cur.next = p;
                p = cur;
                cur = t;
            }
            prev.next = end;
            prev = start;
        }
        return dummy.next;
    }
}
