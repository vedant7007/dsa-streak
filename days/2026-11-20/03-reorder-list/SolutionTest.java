import java.util.*;

public class SolutionTest {
    static List<Integer> brute(int[] a) {
        List<Integer> out = new ArrayList<>();
        int l = 0, r = a.length - 1;
        while (l <= r) {
            out.add(a[l++]);
            if (l <= r) out.add(a[r--]);
        }
        return out;
    }

    static void check(int[] a, List<Integer> exp) {
        ListNode h = Support.build(a);
        new Solution().reorderList(h);
        List<Integer> got = Support.toList(h);
        if (!got.equals(exp)) throw new AssertionError(Arrays.toString(a) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{1, 2, 3, 4}, List.of(1, 4, 2, 3));
        check(new int[]{1, 2, 3, 4, 5}, List.of(1, 5, 2, 4, 3));
        check(new int[]{7}, List.of(7));
        check(new int[]{1, 2}, List.of(1, 2));
        check(new int[]{1, 2, 3}, List.of(1, 3, 2));
        check(new int[]{5, 5, 5, 5}, List.of(5, 5, 5, 5));
        Random rnd = new Random(143);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(15);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = 1 + rnd.nextInt(1000);
            check(a, brute(a));
        }
        int[] big = new int[50000];
        for (int i = 0; i < big.length; i++) big[i] = 1 + i % 1000;
        check(big, brute(big));
        System.out.println("all reorder list tests ok");
    }
}
