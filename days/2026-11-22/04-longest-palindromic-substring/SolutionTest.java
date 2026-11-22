import java.util.*;

public class SolutionTest {
    static boolean isPal(String s) {
        return new StringBuilder(s).reverse().toString().equals(s);
    }

    static int bruteLen(String s) {
        int best = 0;
        for (int i = 0; i < s.length(); i++)
            for (int j = i + 1; j <= s.length(); j++)
                if (isPal(s.substring(i, j))) best = Math.max(best, j - i);
        return best;
    }

    static void check(String s, Set<String> ok) {
        String got = new Solution().longestPalindrome(s);
        if (!ok.contains(got)) throw new AssertionError(s + " expected one of " + ok + " got " + got);
    }

    static void checkLen(String s) {
        String got = new Solution().longestPalindrome(s);
        if (!s.contains(got) || !isPal(got) || got.length() != bruteLen(s))
            throw new AssertionError(s + " got " + got + " expected length " + bruteLen(s));
    }

    public static void main(String[] args) {
        check("babad", Set.of("bab", "aba"));
        check("cbbd", Set.of("bb"));
        check("a", Set.of("a"));
        check("ac", Set.of("a", "c"));
        check("aaaa", Set.of("aaaa"));
        check("forgeeksskeegfor", Set.of("geeksskeeg"));
        check("12321x", Set.of("12321"));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) sb.append('z');
        check(sb.toString(), Set.of(sb.toString()));
        Random rnd = new Random(5);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(12);
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < n; i++) t.append((char) ('a' + rnd.nextInt(3)));
            checkLen(t.toString());
        }
        System.out.println("all longest palindromic substring tests ok");
    }
}
