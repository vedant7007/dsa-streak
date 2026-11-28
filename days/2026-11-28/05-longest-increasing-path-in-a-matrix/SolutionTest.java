import java.util.*;

public class SolutionTest {
    static int brute(int[][] a, int i, int j) {
        int best = 1;
        int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] e : d) {
            int x = i + e[0], y = j + e[1];
            if (x >= 0 && y >= 0 && x < a.length && y < a[0].length && a[x][y] > a[i][j])
                best = Math.max(best, 1 + brute(a, x, y));
        }
        return best;
    }

    static int brute(int[][] a) {
        int ans = 0;
        for (int i = 0; i < a.length; i++) for (int j = 0; j < a[0].length; j++) ans = Math.max(ans, brute(a, i, j));
        return ans;
    }

    static void check(int[][] a, int exp) {
        int got = new Solution().longestIncreasingPath(a);
        if (got != exp) throw new AssertionError("matrix=" + Arrays.deepToString(a) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}, 4);
        check(new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}}, 4);
        check(new int[][]{{1}}, 1);
        check(new int[][]{{7, 7}, {7, 7}}, 1);
        check(new int[][]{{1, 2, 3, 4, 5}}, 5);
        check(new int[][]{{0}, {Integer.MAX_VALUE}}, 2);
        int[][] snake = new int[200][200];
        int v = 0;
        for (int i = 0; i < 200; i++)
            for (int j = 0; j < 200; j++) snake[i][i % 2 == 0 ? j : 199 - j] = v++;
        check(snake, 40000);
        Random r = new Random(9);
        for (int t = 0; t < 200; t++) {
            int m = 1 + r.nextInt(4), n = 1 + r.nextInt(4);
            int[][] a = new int[m][n];
            for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) a[i][j] = r.nextInt(6);
            check(a, brute(a));
        }
        System.out.println("all tests passed");
    }
}
