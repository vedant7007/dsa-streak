class Solution {
    public int characterReplacement(String s, int k) {
        int[] cnt = new int[26];
        int l = 0, max = 0, ans = 0;
        for (int r = 0; r < s.length(); r++) {
            max = Math.max(max, ++cnt[s.charAt(r) - 'A']);
            while (r - l + 1 - max > k) {
                cnt[s.charAt(l) - 'A']--;
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
