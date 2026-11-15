import java.util.*;

public class SolutionTest {
    static int brute(int[][] a) {
        int n = a.length, best = 0;
        for (int mask = 0; mask < (1 << n); mask++) {
            boolean ok = true;
            for (int i = 0; i < n && ok; i++) if ((mask >> i & 1) == 1)
                for (int j = i + 1; j < n && ok; j++) if ((mask >> j & 1) == 1)
                    if (Math.max(a[i][0], a[j][0]) < Math.min(a[i][1], a[j][1])) ok = false;
            if (ok) best = Math.max(best, Integer.bitCount(mask));
        }
        return n - best;
    }

    static int[][] copy(int[][] a) {
        int[][] c = new int[a.length][];
        for (int i = 0; i < a.length; i++) c[i] = a[i].clone();
        return c;
    }

    static void check(int[][] a, int exp) {
        int got = new Solution().eraseOverlapIntervals(copy(a));
        if (got != exp) throw new AssertionError("intervals=" + Arrays.deepToString(a) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}}, 1);
        check(new int[][]{{1, 2}, {1, 2}, {1, 2}}, 2);
        check(new int[][]{{1, 2}, {2, 3}}, 0);
        check(new int[][]{{5, 9}}, 0);
        check(new int[][]{{-50000, 50000}, {-3, -1}, {0, 2}}, 1);
        check(new int[][]{{1, 100}, {11, 22}, {1, 11}, {2, 12}}, 2);
        Random rnd = new Random(435);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(10);
            int[][] a = new int[n][2];
            for (int i = 0; i < n; i++) {
                int s = rnd.nextInt(15) - 5;
                a[i][0] = s;
                a[i][1] = s + 1 + rnd.nextInt(6);
            }
            check(a, brute(a));
        }
        System.out.println("all non-overlapping intervals tests ok");
    }
}
