import java.util.Random;

public class SolutionTest {
    static void check(long got, long expected, String label) {
        if (got != expected) throw new AssertionError(label + ": expected " + expected + " but got " + got);
    }

    // brute force: try every path recursively
    static long brute(int pos, int n, int[] costs) {
        if (pos == n) return 0;
        long best = Long.MAX_VALUE;
        for (int jump = 1; jump <= 3 && pos + jump <= n; jump++) {
            int next = pos + jump;
            best = Math.min(best, costs[next - 1] + (long) jump * jump + brute(next, n, costs));
        }
        return best;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        check(s.climbStairs(4, new int[]{1, 2, 3, 4}), 13, "Example 1");
        check(s.climbStairs(4, new int[]{5, 1, 6, 2}), 11, "Example 2");
        check(s.climbStairs(3, new int[]{9, 8, 3}), 12, "Example 3");
        // edge cases
        check(s.climbStairs(1, new int[]{7}), 8, "single step");
        check(s.climbStairs(2, new int[]{1, 1}), 4, "two small steps (2 + 2) beat one jump of 2 (1 + 4)");
        check(s.climbStairs(3, new int[]{10000, 10000, 1}), 10, "one jump of 3 skips expensive steps");
        int n = 100000;
        int[] big = new int[n];
        java.util.Arrays.fill(big, 10000);
        long[] dp = new long[n + 1];
        for (int j = 1; j <= n; j++) {
            dp[j] = Long.MAX_VALUE;
            for (int k = 1; k <= 3 && j - k >= 0; k++) dp[j] = Math.min(dp[j], dp[j - k] + big[j - 1] + k * k);
        }
        check(s.climbStairs(n, big), dp[n], "max size");
        Random rnd = new Random(42);
        for (int t = 0; t < 200; t++) {
            int m = 1 + rnd.nextInt(12);
            int[] c = new int[m];
            for (int i = 0; i < m; i++) c[i] = 1 + rnd.nextInt(20);
            check(s.climbStairs(m, c), brute(0, m, c), "random #" + t);
        }
        System.out.println("All tests passed");
    }
}
