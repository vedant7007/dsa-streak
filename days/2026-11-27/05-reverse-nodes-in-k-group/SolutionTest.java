import java.util.*;

public class SolutionTest {
    static List<Integer> brute(int[] a, int k) {
        List<Integer> out = new ArrayList<>();
        int i = 0;
        for (; i + k <= a.length; i += k) for (int j = i + k - 1; j >= i; j--) out.add(a[j]);
        for (; i < a.length; i++) out.add(a[i]);
        return out;
    }

    static void check(int[] a, int k, List<Integer> exp) {
        ListNode h = Support.build(a);
        ListNode[] nodes = new ListNode[a.length];
        ListNode t = h;
        for (int i = 0; i < a.length; i++) { nodes[i] = t; t = t.next; }
        ListNode res = new Solution().reverseKGroup(h, k);
        List<Integer> got = Support.toList(res);
        if (!got.equals(exp)) throw new AssertionError("a=" + Arrays.toString(a) + " k=" + k + " expected " + exp + " got " + got);
        Set<ListNode> seen = Collections.newSetFromMap(new IdentityHashMap<>());
        for (ListNode x = res; x != null; x = x.next) seen.add(x);
        for (ListNode x : nodes) if (!seen.contains(x)) throw new AssertionError("nodes were recreated, not relinked");
    }

    public static void main(String[] args) {
        check(new int[]{1, 2, 3, 4, 5}, 2, Arrays.asList(2, 1, 4, 3, 5));
        check(new int[]{1, 2, 3, 4, 5}, 3, Arrays.asList(3, 2, 1, 4, 5));
        check(new int[]{7}, 1, Arrays.asList(7));
        check(new int[]{1, 2, 3}, 1, Arrays.asList(1, 2, 3));
        check(new int[]{1, 2, 3, 4}, 4, Arrays.asList(4, 3, 2, 1));
        check(new int[]{0, 0, 1, 1, 0, 0}, 3, Arrays.asList(1, 0, 0, 0, 0, 1));
        int[] big = new int[5000];
        for (int i = 0; i < 5000; i++) big[i] = i % 1001;
        check(big, 7, brute(big, 7));
        Random r = new Random(11);
        for (int t = 0; t < 200; t++) {
            int n = 1 + r.nextInt(12);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = r.nextInt(1001);
            int k = 1 + r.nextInt(n);
            check(a, k, brute(a, k));
        }
        System.out.println("all tests passed");
    }
}
