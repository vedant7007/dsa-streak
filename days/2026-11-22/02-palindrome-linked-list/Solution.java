class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode prev = null;
        while (slow != null) {
            ListNode nxt = slow.next;
            slow.next = prev;
            prev = slow;
            slow = nxt;
        }
        ListNode l = head, r = prev;
        while (r != null) {
            if (l.val != r.val) return false;
            l = l.next;
            r = r.next;
        }
        return true;
    }
}
