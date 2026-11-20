import java.util.*;

public class SolutionTest {
    static int brute(int[][] times, int n, int k) {
        int INF = 1 << 29;
        int[][] d = new int[n + 1][n + 1];
        for (int[] r : d) Arrays.fill(r, INF);
        for (int i = 1; i <= n; i++) d[i][i] = 0;
        for (int[] t : times) d[t[0]][t[1]] = Math.min(d[t[0]][t[1]], t[2]);
        for (int m = 1; m <= n; m++)
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= n; j++)
                    d[i][j] = Math.min(d[i][j], d[i][m] + d[m][j]);
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (d[k][i] >= INF) return -1;
            ans = Math.max(ans, d[k][i]);
        }
        return ans;
    }

    static void check(int[][] times, int n, int k, int exp) {
        int got = new Solution().networkDelayTime(times, n, k);
        if (got != exp) throw new AssertionError(Arrays.deepToString(times) + " n=" + n + " k=" + k + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2, 2);
        check(new int[][]{{1, 2, 1}}, 2, 1, 1);
        check(new int[][]{{1, 2, 1}}, 2, 2, -1);
        check(new int[][]{{1, 2, 0}}, 2, 1, 0);
        check(new int[][]{{1, 2, 10}, {1, 3, 1}, {3, 2, 1}}, 3, 1, 2);
        check(new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 4}}, 3, 1, 3);
        check(new int[][]{{2, 1, 5}}, 3, 2, -1);
        Random rnd = new Random(743);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(6);
            List<int[]> l = new ArrayList<>();
            for (int u = 1; u <= n; u++)
                for (int v = 1; v <= n; v++)
                    if (u != v && rnd.nextInt(3) == 0) l.add(new int[]{u, v, rnd.nextInt(11)});
            if (l.isEmpty()) continue;
            int[][] times = l.toArray(new int[0][]);
            int k = 1 + rnd.nextInt(n);
            check(times, n, k, brute(times, n, k));
        }
        System.out.println("all network delay time tests ok");
    }
}
