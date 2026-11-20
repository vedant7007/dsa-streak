class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Support {
    static ListNode build(int[] vals) {
        ListNode dummy = new ListNode(0), t = dummy;
        for (int v : vals) { t.next = new ListNode(v); t = t.next; }
        return dummy.next;
    }

    static java.util.List<Integer> toList(ListNode h) {
        java.util.List<Integer> out = new java.util.ArrayList<>();
        while (h != null) { out.add(h.val); h = h.next; }
        return out;
    }
}
