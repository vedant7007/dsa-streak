class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int len = 0;
        for (int x : nums) {
            int l = 0, r = len;
            while (l < r) {
                int mid = (l + r) / 2;
                if (tails[mid] < x) l = mid + 1;
                else r = mid;
            }
            tails[l] = x;
            if (l == len) len++;
        }
        return len;
    }
}
