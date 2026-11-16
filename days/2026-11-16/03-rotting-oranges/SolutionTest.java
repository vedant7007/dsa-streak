import java.util.*;

public class SolutionTest {
    static int[][] copy(int[][] g) {
        int[][] c = new int[g.length][];
        for (int i = 0; i < g.length; i++) c[i] = g[i].clone();
        return c;
    }

    static int brute(int[][] g0) {
        int[][] g = copy(g0);
        int m = g.length, n = g[0].length, t = 0;
        while (true) {
            boolean any = false;
            int[][] nx = copy(g);
            for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) {
                if (g[i][j] != 1) continue;
                if ((i > 0 && g[i - 1][j] == 2) || (i < m - 1 && g[i + 1][j] == 2) || (j > 0 && g[i][j - 1] == 2) || (j < n - 1 && g[i][j + 1] == 2)) { nx[i][j] = 2; any = true; }
            }
            if (!any) break;
            g = nx;
            t++;
        }
        for (int[] row : g) for (int x : row) if (x == 1) return -1;
        return t;
    }

    static void check(int[][] g, int exp) {
        String s = Arrays.deepToString(g);
        int got = new Solution().orangesRotting(copy(g));
        if (got != exp) throw new AssertionError("grid=" + s + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}, 4);
        check(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}, -1);
        check(new int[][]{{0, 2}}, 0);
        check(new int[][]{{0}}, 0);
        check(new int[][]{{1}}, -1);
        check(new int[][]{{2}}, 0);
        check(new int[][]{{2, 1, 1, 1, 1, 1, 1, 1, 1, 2}}, 4);
        check(new int[][]{{1, 1}, {1, 1}}, -1);
        Random rnd = new Random(994);
        for (int it = 0; it < 200; it++) {
            int m = 1 + rnd.nextInt(6), n = 1 + rnd.nextInt(6);
            int[][] g = new int[m][n];
            for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) { int r = rnd.nextInt(10); g[i][j] = r < 2 ? 0 : r < 3 ? 2 : 1; }
            check(g, brute(g));
        }
        System.out.println("all rotting oranges tests ok");
    }
}
