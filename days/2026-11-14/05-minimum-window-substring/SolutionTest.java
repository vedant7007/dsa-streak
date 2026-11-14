import java.util.*;

public class SolutionTest {
    static boolean covers(String w, String t) {
        int[] c = new int[128];
        for (char ch : w.toCharArray()) c[ch]++;
        for (char ch : t.toCharArray()) if (--c[ch] < 0) return false;
        return true;
    }

    static int bruteLen(String s, String t) {
        for (int len = 1; len <= s.length(); len++)
            for (int i = 0; i + len <= s.length(); i++)
                if (covers(s.substring(i, i + len), t)) return len;
        return 0;
    }

    static void check(String s, String t, String exp) {
        String got = new Solution().minWindow(s, t);
        if (!got.equals(exp)) throw new AssertionError("s=" + s + " t=" + t + " expected \"" + exp + "\" got \"" + got + "\"");
    }

    public static void main(String[] args) {
        check("ADOBECODEBANC", "ABC", "BANC");
        check("a", "a", "a");
        check("a", "aa", "");
        check("ab", "b", "b");
        check("aa", "aa", "aa");
        check("abc", "d", "");
        check("aAbB", "AB", "AbB");
        check("bba", "ab", "ba");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) sb.append('x');
        String big = sb.toString() + "yz";
        check(big, "zy", "yz");
        Random rnd = new Random(76);
        for (int it = 0; it < 200; it++) {
            int m = 1 + rnd.nextInt(14), n = 1 + rnd.nextInt(4);
            StringBuilder a = new StringBuilder(), b = new StringBuilder();
            for (int i = 0; i < m; i++) a.append((char) ('a' + rnd.nextInt(3)));
            for (int i = 0; i < n; i++) b.append((char) ('a' + rnd.nextInt(3)));
            String s = a.toString(), t = b.toString();
            int len = bruteLen(s, t);
            String got = new Solution().minWindow(s, t);
            if (got.length() != len || (len > 0 && (!s.contains(got) || !covers(got, t))))
                throw new AssertionError("random s=" + s + " t=" + t + " expected length " + len + " got \"" + got + "\"");
        }
        System.out.println("all minimum window substring tests ok");
    }
}
