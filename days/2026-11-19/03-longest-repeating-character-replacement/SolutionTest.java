import java.util.*;

public class SolutionTest {
    static int brute(String s, int k) {
        int best = 0;
        for (int i = 0; i < s.length(); i++)
            for (int j = i; j < s.length(); j++) {
                int[] c = new int[26];
                int mx = 0;
                for (int x = i; x <= j; x++) mx = Math.max(mx, ++c[s.charAt(x) - 'A']);
                if (j - i + 1 - mx <= k) best = Math.max(best, j - i + 1);
            }
        return best;
    }

    static void check(String s, int k, int exp) {
        int got = new Solution().characterReplacement(s, k);
        if (got != exp) throw new AssertionError(s + " k=" + k + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check("ABAB", 2, 4);
        check("AABABBA", 1, 4);
        check("A", 0, 1);
        check("AAAA", 0, 4);
        check("ABCD", 0, 1);
        check("ABCD", 10, 4);
        check("ABBB", 0, 3);
        Random rnd = new Random(424);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(10);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) sb.append((char) ('A' + rnd.nextInt(3)));
            int k = rnd.nextInt(n + 1);
            check(sb.toString(), k, brute(sb.toString(), k));
        }
        System.out.println("all character replacement tests ok");
    }
}
