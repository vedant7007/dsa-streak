import java.util.*;

public class SolutionTest {
    static List<Integer> brute(int[] a, int n) {
        List<Integer> out = new ArrayList<>();
        for (int i = 0; i < a.length; i++) if (i != a.length - n) out.add(a[i]);
        return out;
    }

    static void check(int[] a, int n, List<Integer> exp) {
        List<Integer> got = Support.toList(new Solution().removeNthFromEnd(Support.build(a), n));
        if (!got.equals(exp)) throw new AssertionError(Arrays.toString(a) + " n=" + n + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{1, 2, 3, 4, 5}, 2, Arrays.asList(1, 2, 3, 5));
        check(new int[]{1}, 1, Arrays.asList());
        check(new int[]{1, 2}, 1, Arrays.asList(1));
        check(new int[]{1, 2}, 2, Arrays.asList(2));
        check(new int[]{4, 4, 4}, 3, Arrays.asList(4, 4));
        int[] big = new int[30];
        for (int i = 0; i < 30; i++) big[i] = i;
        check(big, 30, brute(big, 30));
        Random rnd = new Random(19);
        for (int it = 0; it < 200; it++) {
            int sz = 1 + rnd.nextInt(30);
            int[] a = new int[sz];
            for (int i = 0; i < sz; i++) a[i] = rnd.nextInt(101);
            int n = 1 + rnd.nextInt(sz);
            check(a, n, brute(a, n));
        }
        System.out.println("all remove nth node tests ok");
    }
}
