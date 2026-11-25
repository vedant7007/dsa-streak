import java.util.*;

public class SolutionTest {
    static int[] brute(int[][] iv, int[] qs) {
        int[] r = new int[qs.length];
        for (int k = 0; k < qs.length; k++) {
            int best = -1;
            for (int[] a : iv)
                if (a[0] <= qs[k] && qs[k] <= a[1]) {
                    int len = a[1] - a[0] + 1;
                    if (best == -1 || len < best) best = len;
                }
            r[k] = best;
        }
        return r;
    }

    static int[][] copy(int[][] a) {
        int[][] c = new int[a.length][];
        for (int i = 0; i < a.length; i++) c[i] = a[i].clone();
        return c;
    }

    static void check(int[][] iv, int[] qs, int[] exp, String name) {
        int[] got = new Solution().minInterval(copy(iv), qs.clone());
        if (!Arrays.equals(got, exp)) throw new AssertionError(name + ": expected " + Arrays.toString(exp) + " got " + Arrays.toString(got));
    }

    public static void main(String[] args) {
        check(new int[][]{{1, 4}, {2, 4}, {3, 6}, {4, 4}}, new int[]{2, 3, 4, 5}, new int[]{3, 3, 1, 4}, "example 1");
        check(new int[][]{{2, 3}, {2, 5}, {1, 8}, {20, 25}}, new int[]{2, 19, 5, 22}, new int[]{2, -1, 4, 6}, "example 2");
        check(new int[][]{{1, 1}}, new int[]{1}, new int[]{1}, "single point interval");
        check(new int[][]{{5, 10}}, new int[]{1, 11}, new int[]{-1, -1}, "no cover");
        check(new int[][]{{1, 10}, {1, 10}}, new int[]{3, 3, 3}, new int[]{10, 10, 10}, "duplicates");
        check(new int[][]{{1, 10000000}}, new int[]{10000000}, new int[]{10000000}, "max value");
        Random r = new Random(9);
        for (int t = 0; t < 200; t++) {
            int n = 1 + r.nextInt(6), m = 1 + r.nextInt(6);
            int[][] iv = new int[n][2];
            for (int i = 0; i < n; i++) { int a = 1 + r.nextInt(15), b = a + r.nextInt(6); iv[i] = new int[]{a, b}; }
            int[] qs = new int[m];
            for (int i = 0; i < m; i++) qs[i] = 1 + r.nextInt(22);
            check(iv, qs, brute(iv, qs), "random " + Arrays.deepToString(iv) + " q=" + Arrays.toString(qs));
        }
        System.out.println("minimum-interval: all tests passed");
    }
}
