class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
        int n = nums1.length, m = nums2.length;
        int half = (n + m + 1) / 2;
        int l = 0, r = n;
        while (l <= r) {
            int i = (l + r) / 2;
            int j = half - i;
            int a1 = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int a2 = i == n ? Integer.MAX_VALUE : nums1[i];
            int b1 = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int b2 = j == m ? Integer.MAX_VALUE : nums2[j];
            if (a1 <= b2 && b1 <= a2) {
                if ((n + m) % 2 == 1) return Math.max(a1, b1);
                return (Math.max(a1, b1) + Math.min(a2, b2)) / 2.0;
            } else if (a1 > b2) {
                r = i - 1;
            } else {
                l = i + 1;
            }
        }
        return 0;
    }
}
