class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] q = new int[n];
        int[] ans = new int[n - k + 1];
        int h = 0, t = 0;
        for (int i = 0; i < n; i++) {
            if (h < t && q[h] <= i - k) h++;
            while (h < t && nums[q[t - 1]] <= nums[i]) t--;
            q[t++] = i;
            if (i >= k - 1) ans[i - k + 1] = nums[q[h]];
        }
        return ans;
    }
}
