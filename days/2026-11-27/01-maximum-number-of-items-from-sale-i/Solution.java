class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] cnt = new int[1501];
        int min = Integer.MAX_VALUE;
        for (int[] it : items) {
            cnt[it[0]]++;
            min = Math.min(min, it[1]);
        }
        int[] dp = new int[budget + 1];
        for (int[] it : items) {
            int f = it[0], p = it[1];
            int g = -1;
            for (int m = f; m <= 1500; m += f) g += cnt[m];
            for (int b = budget; b >= p; b--) {
                dp[b] = Math.max(dp[b], dp[b - p] + 1 + g);
            }
        }
        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            ans = Math.max(ans, dp[b] + (budget - b) / min);
        }
        return ans;
    }
}
