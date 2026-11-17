import java.util.*;

public class SolutionTest {
    static int brute(String a, String b) {
        if (a.isEmpty()) return b.length();
        if (b.isEmpty()) return a.length();
        if (a.charAt(0) == b.charAt(0)) return brute(a.substring(1), b.substring(1));
        int x = brute(a.substring(1), b.substring(1));
        int y = brute(a.substring(1), b);
        int z = brute(a, b.substring(1));
        return 1 + Math.min(x, Math.min(y, z));
    }

    static void check(String a, String b, int exp) {
        int got = new Solution().minDistance(a, b);
        if (got != exp) throw new AssertionError("word1=" + a + " word2=" + b + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check("horse", "ros", 3);
        check("intention", "execution", 5);
        check("", "", 0);
        check("", "abc", 3);
        check("abc", "", 3);
        check("same", "same", 0);
        check("a", "b", 1);
        check("aaaa", "aa", 2);
        Random rnd = new Random(72);
        for (int t = 0; t < 200; t++) {
            String a = rand(rnd, rnd.nextInt(7)), b = rand(rnd, rnd.nextInt(7));
            check(a, b, brute(a, b));
        }
        String big1 = rand(rnd, 500), big2 = rand(rnd, 500);
        int d = new Solution().minDistance(big1, big2);
        if (d < 0 || d > 500) throw new AssertionError("max size result out of range " + d);
        check(big1, big1, 0);
        System.out.println("all edit distance tests ok");
    }

    static String rand(Random rnd, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) sb.append((char) ('a' + rnd.nextInt(3)));
        return sb.toString();
    }
}
