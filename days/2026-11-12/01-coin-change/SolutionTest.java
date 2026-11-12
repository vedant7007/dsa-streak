import java.util.*;

public class SolutionTest {
    static void check(int[] coins, int amount, int exp) {
        int got = new Solution().coinChange(coins, amount);
        if (got != exp) throw new AssertionError("coins=" + Arrays.toString(coins) + " amount=" + amount + " expected " + exp + " got " + got);
    }

    static int brute(int[] coins, int amount, int[] memo) {
        if (amount == 0) return 0;
        if (memo[amount] != -2) return memo[amount];
        int best = -1;
        for (int c : coins) {
            if (c <= amount) {
                int r = brute(coins, amount - c, memo);
                if (r >= 0 && (best < 0 || r + 1 < best)) best = r + 1;
            }
        }
        return memo[amount] = best;
    }

    public static void main(String[] args) {
        check(new int[]{1, 2, 5}, 11, 3);
        check(new int[]{2}, 3, -1);
        check(new int[]{1}, 0, 0);
        check(new int[]{2}, 1, -1);
        check(new int[]{186, 419, 83, 408}, 6249, 20);
        check(new int[]{1}, 10000, 10000);
        check(new int[]{2147483647}, 2, -1);
        check(new int[]{3, 7}, 14, 2);
        Random rnd = new Random(7);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(4);
            int[] coins = new int[n];
            for (int i = 0; i < n; i++) coins[i] = 1 + rnd.nextInt(12);
            int amount = rnd.nextInt(60);
            int[] memo = new int[amount + 1];
            Arrays.fill(memo, -2);
            check(coins, amount, brute(coins, amount, memo));
        }
        System.out.println("all coin change tests ok");
    }
}
