class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[j] = minimum total cost to stand on step j (step 0 costs nothing)
        long[] dp = new long[n + 1];
        for (int j = 1; j <= n; j++) {
            dp[j] = Long.MAX_VALUE;
            // the previous step can be j-1, j-2 or j-3 (never below step 0)
            for (int jump = 1; jump <= 3 && j - jump >= 0; jump++) {
                long cost = dp[j - jump] + costs[j - 1] + (long) jump * jump;
                dp[j] = Math.min(dp[j], cost);
            }
        }
        return (int) dp[n];
    }
}
