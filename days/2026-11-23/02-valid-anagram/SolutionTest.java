import java.util.*;

public class SolutionTest {
    static boolean brute(String s, String t) {
        char[] a = s.toCharArray(), b = t.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }

    static void check(String s, String t, boolean exp) {
        boolean got = new Solution().isAnagram(s, t);
        if (got != exp) throw new AssertionError("\"" + s + "\",\"" + t + "\" expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check("anagram", "nagaram", true);
        check("rat", "car", false);
        check("a", "a", true);
        check("a", "b", false);
        check("ab", "a", false);
        check("aab", "abb", false);
        StringBuilder x = new StringBuilder(), y = new StringBuilder();
        for (int i = 0; i < 50000; i++) { x.append((char) ('a' + i % 26)); y.append((char) ('z' - i % 26)); }
        check(x.toString(), y.toString(), brute(x.toString(), y.toString()));
        check(x.toString(), x.reverse().toString(), true);
        Random rnd = new Random(242);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(8);
            StringBuilder a = new StringBuilder();
            for (int i = 0; i < n; i++) a.append((char) ('a' + rnd.nextInt(3)));
            String s = a.toString(), t;
            if (rnd.nextBoolean()) {
                List<Character> l = new ArrayList<>();
                for (char c : s.toCharArray()) l.add(c);
                Collections.shuffle(l, rnd);
                StringBuilder b = new StringBuilder();
                for (char c : l) b.append(c);
                t = b.toString();
            } else {
                StringBuilder b = new StringBuilder();
                int m = 1 + rnd.nextInt(8);
                for (int i = 0; i < m; i++) b.append((char) ('a' + rnd.nextInt(3)));
                t = b.toString();
            }
            check(s, t, brute(s, t));
        }
        System.out.println("all valid anagram tests ok");
    }
}
