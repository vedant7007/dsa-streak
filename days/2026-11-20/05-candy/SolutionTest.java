import java.util.*;

public class SolutionTest {
    static int best;

    static void go(int[] r, int[] c, int i) {
        int n = r.length;
        if (i == n) {
            for (int j = 0; j < n; j++) {
                if (j > 0 && r[j] > r[j - 1] && c[j] <= c[j - 1]) return;
                if (j + 1 < n && r[j] > r[j + 1] && c[j] <= c[j + 1]) return;
            }
            int s = 0;
            for (int x : c) s += x;
            best = Math.min(best, s);
            return;
        }
        for (int v = 1; v <= n; v++) { c[i] = v; go(r, c, i + 1); }
    }

    static int brute(int[] r) {
        best = Integer.MAX_VALUE;
        go(r, new int[r.length], 0);
        return best;
    }

    static void check(int[] r, int exp) {
        int got = new Solution().candy(r);
        if (got != exp) throw new AssertionError(Arrays.toString(r) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{1, 0, 2}, 5);
        check(new int[]{1, 2, 2}, 4);
        check(new int[]{5}, 1);
        check(new int[]{3, 3, 3, 3}, 4);
        check(new int[]{1, 2, 3, 4, 5}, 15);
        check(new int[]{5, 4, 3, 2, 1}, 15);
        check(new int[]{1, 3, 4, 5, 2}, 11);
        check(new int[]{0, 0}, 2);
        int[] big = new int[50000];
        for (int i = 0; i < big.length; i++) big[i] = i;
        check(big, (int) (50000L * 50001 / 2));
        Random rnd = new Random(135);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(6);
            int[] r = new int[n];
            for (int i = 0; i < n; i++) r[i] = rnd.nextInt(4);
            check(r, brute(r));
        }
        System.out.println("all candy tests ok");
    }
}
