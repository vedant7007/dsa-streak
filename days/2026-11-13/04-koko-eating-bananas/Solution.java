class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = 0;
        for (int p : piles) r = Math.max(r, p);
        while (l < r) {
            int mid = l + (r - l) / 2;
            long hrs = 0;
            for (int p : piles) hrs += (p + mid - 1) / mid;
            if (hrs <= h) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}
