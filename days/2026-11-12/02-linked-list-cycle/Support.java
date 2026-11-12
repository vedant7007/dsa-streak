class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; next = null; }
}

class Support {
    // builds a list from vals and links the tail to index pos (-1 means no cycle)
    static ListNode build(int[] vals, int pos) {
        if (vals.length == 0) return null;
        ListNode[] nodes = new ListNode[vals.length];
        for (int i = 0; i < vals.length; i++) nodes[i] = new ListNode(vals[i]);
        for (int i = 0; i + 1 < vals.length; i++) nodes[i].next = nodes[i + 1];
        if (pos >= 0) nodes[vals.length - 1].next = nodes[pos];
        return nodes[0];
    }
}
