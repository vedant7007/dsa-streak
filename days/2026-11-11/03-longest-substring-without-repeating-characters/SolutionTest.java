import java.util.*;

public class SolutionTest {
    static void check(String s, int expected, String label) {
        int got = new Solution().lengthOfLongestSubstring(s);
        if (got != expected) throw new AssertionError(label + ": expected " + expected + " but got " + got + " for \"" + s + "\"");
    }

    static int brute(String s) {
        int best = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> seen = new HashSet<>();
            for (int j = i; j < s.length() && seen.add(s.charAt(j)); j++) best = Math.max(best, j - i + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        check("abcabcbb", 3, "Example 1");
        check("bbbbb", 1, "Example 2");
        check("pwwkew", 3, "Example 3");
        check("", 0, "empty string");
        check(" ", 1, "single space");
        check("abba", 2, "left pointer must not move back");
        check("dvdf", 3, "restart after middle repeat");
        check("a1 !A~", 6, "digits, symbols, spaces, case");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50000; i++) sb.append((char) (32 + i % 95));
        check(sb.toString(), 95, "max length 5*10^4 cycling 95 printable chars");
        Random rnd = new Random(7);
        String alpha = "abcde ";
        for (int t = 0; t < 200; t++) {
            int n = rnd.nextInt(12);
            StringBuilder b = new StringBuilder();
            for (int i = 0; i < n; i++) b.append(alpha.charAt(rnd.nextInt(alpha.length())));
            String s = b.toString();
            check(s, brute(s), "random #" + t);
        }
        System.out.println("All tests passed");
    }
}
