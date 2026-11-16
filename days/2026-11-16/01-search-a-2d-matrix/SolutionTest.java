import java.util.*;

public class SolutionTest {
    static boolean brute(int[][] a, int t) {
        for (int[] row : a) for (int x : row) if (x == t) return true;
        return false;
    }

    static void check(int[][] a, int t, boolean exp) {
        boolean got = new Solution().searchMatrix(a, t);
        if (got != exp) throw new AssertionError("matrix=" + Arrays.deepToString(a) + " target=" + t + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        int[][] ex = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        check(ex, 3, true);
        check(ex, 13, false);
        check(new int[][]{{5}}, 5, true);
        check(new int[][]{{5}}, -5, false);
        check(ex, 1, true);
        check(ex, 60, true);
        check(ex, 0, false);
        check(ex, 61, false);
        check(new int[][]{{-10000, -5}, {0, 10000}}, -10000, true);
        check(new int[][]{{1}, {3}, {5}}, 3, true);
        Random rnd = new Random(1116);
        for (int it = 0; it < 200; it++) {
            int m = 1 + rnd.nextInt(6), n = 1 + rnd.nextInt(6);
            int[][] a = new int[m][n];
            int v = rnd.nextInt(21) - 10;
            for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) { a[i][j] = v; v += 1 + rnd.nextInt(3); }
            int t = rnd.nextInt(60) - 15;
            check(a, t, brute(a, t));
        }
        System.out.println("all search a 2d matrix tests ok");
    }
}
