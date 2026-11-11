class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] last = new int[128];
        int l = 0, max = 0;
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            l = Math.max(l, last[c]);
            max = Math.max(max, r - l + 1);
            last[c] = r + 1;
        }
        return max;
    }
}
