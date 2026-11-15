import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Support {
    static ListNode build(int[] a) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int x : a) { cur.next = new ListNode(x); cur = cur.next; }
        return dummy.next;
    }

    static List<Integer> toList(ListNode head) {
        List<Integer> res = new ArrayList<>();
        for (ListNode c = head; c != null; c = c.next) res.add(c.val);
        return res;
    }
}
