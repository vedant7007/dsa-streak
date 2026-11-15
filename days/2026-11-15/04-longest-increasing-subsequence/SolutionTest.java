import java.util.*;

public class SolutionTest {
    static int brute(int[] a) {
        int n = a.length, ans = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) if (a[j] < a[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    static void check(int[] a, int exp) {
        int got = new Solution().lengthOfLIS(a.clone());
        if (got != exp) throw new AssertionError("nums=" + Arrays.toString(a) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{10, 9, 2, 5, 3, 7, 101, 18}, 4);
        check(new int[]{0, 1, 0, 3, 2, 3}, 4);
        check(new int[]{7, 7, 7, 7, 7, 7, 7}, 1);
        check(new int[]{5}, 1);
        check(new int[]{5, 4, 3, 2, 1}, 1);
        check(new int[]{-10000, 0, 10000}, 3);
        int[] big = new int[2500];
        for (int i = 0; i < 2500; i++) big[i] = i - 1250;
        check(big, 2500);
        Random rnd = new Random(300);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(15);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(11) - 5;
            check(a, brute(a));
        }
        System.out.println("all longest increasing subsequence tests ok");
    }
}
