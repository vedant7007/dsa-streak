import java.util.*;

public class SolutionTest {
    static void check(int[] a, int exp) {
        int got = new Solution().missingNumber(a);
        if (got != exp) throw new AssertionError(Arrays.toString(a) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{3, 0, 1}, 2);
        check(new int[]{0, 1}, 2);
        check(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}, 8);
        check(new int[]{0}, 1);
        check(new int[]{1}, 0);
        check(new int[]{1, 2, 3}, 0);
        int[] big = new int[10000];
        for (int i = 0; i < 10000; i++) big[i] = i < 4321 ? i : i + 1;
        check(big, 4321);
        Random rnd = new Random(268);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(12);
            int miss = rnd.nextInt(n + 1);
            List<Integer> l = new ArrayList<>();
            for (int v = 0; v <= n; v++) if (v != miss) l.add(v);
            Collections.shuffle(l, rnd);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = l.get(i);
            check(a, miss);
        }
        System.out.println("all missing number tests ok");
    }
}
