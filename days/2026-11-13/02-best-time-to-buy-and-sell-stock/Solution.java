class Solution {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE, ans = 0;
        for (int p : prices) {
            if (p < min) min = p;
            else ans = Math.max(ans, p - min);
        }
        return ans;
    }
}
