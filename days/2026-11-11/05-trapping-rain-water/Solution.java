class Solution {
    public int trap(int[] height) {
        int l = 0, r = height.length - 1;
        int lmax = 0, rmax = 0, ans = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                lmax = Math.max(lmax, height[l]);
                ans += lmax - height[l];
                l++;
            } else {
                rmax = Math.max(rmax, height[r]);
                ans += rmax - height[r];
                r--;
            }
        }
        return ans;
    }
}
