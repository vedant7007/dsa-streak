class Solution {
    public String longestPalindrome(String s) {
        int st = 0, len = 0;
        for (int i = 0; i < s.length(); i++) {
            int a = expand(s, i, i), b = expand(s, i, i + 1);
            int m = Math.max(a, b);
            if (m > len) {
                len = m;
                st = i - (m - 1) / 2;
            }
        }
        return s.substring(st, st + len);
    }

    int expand(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }
}
