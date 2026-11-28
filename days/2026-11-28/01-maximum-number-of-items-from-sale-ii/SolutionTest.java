import java.util.*;

public class SolutionTest {
    static long brute(int[][] it, int budget) {
        int n = it.length;
        int[] d = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i != j && it[j][0] % it[i][0] == 0) d[i]++;
        long[] dp = new long[budget + 1];
        Arrays.fill(dp, Long.MIN_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            long[] nd = new long[budget + 1];
            Arrays.fill(nd, Long.MIN_VALUE);
            for (int b = 0; b <= budget; b++) {
                if (dp[b] == Long.MIN_VALUE) continue;
                for (int c = 0; b + (long) c * it[i][1] <= budget; c++) {
                    int nb = b + c * it[i][1];
                    nd[nb] = Math.max(nd[nb], dp[b] + c + Math.min(c, d[i]));
                }
            }
            dp = nd;
        }
        long best = 0;
        for (long v : dp) best = Math.max(best, v);
        return best;
    }

    static void check(int[][] it, int budget, long exp) {
        long got = new Solution().maximumSaleItems(it, budget);
        if (got != exp) throw new AssertionError("items=" + Arrays.deepToString(it) + " budget=" + budget + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[][]{{1, 6}, {2, 4}, {3, 5}}, 19, 5);
        check(new int[][]{{2, 8}, {1, 10}, {6, 6}, {4, 12}, {5, 20}, {5, 17}}, 35, 7);
        check(new int[][]{{1, 1}}, 1, 1);
        check(new int[][]{{1, 5}}, 3, 0);
        check(new int[][]{{1, 3}, {1, 3}, {1, 3}}, 9, 6);
        check(new int[][]{{1, 1}, {1, 1}}, 1000000000, 1000000002L);
        int n = 100000;
        int[][] big = new int[n][];
        for (int i = 0; i < n; i++) big[i] = new int[]{1, 1};
        check(big, 1000000000, 2000000000L);
        int[][] big2 = new int[n][];
        for (int i = 0; i < n; i++) big2[i] = new int[]{i + 1, 1000000000};
        check(big2, 1000000000, 2);
        Random r = new Random(11);
        for (int t = 0; t < 300; t++) {
            int m = 1 + r.nextInt(6);
            int[][] it = new int[m][];
            for (int i = 0; i < m; i++) it[i] = new int[]{1 + r.nextInt(m), 1 + r.nextInt(7)};
            int b = 1 + r.nextInt(25);
            check(it, b, brute(it, b));
        }
        System.out.println("all tests passed");
    }
}
