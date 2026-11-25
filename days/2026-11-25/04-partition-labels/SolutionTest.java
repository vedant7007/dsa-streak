import java.util.*;

public class SolutionTest {
    static List<Integer> brute(String s) {
        // cut after i if no letter in s[0..i] appears in s[i+1..]
        List<Integer> res = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            boolean ok = true;
            for (int a = start; a <= i && ok; a++)
                if (s.indexOf(s.charAt(a), i + 1) != -1) ok = false;
            if (ok) { res.add(i - start + 1); start = i + 1; }
        }
        return res;
    }

    static void check(String s, List<Integer> exp, String name) {
        List<Integer> got = new Solution().partitionLabels(s);
        if (!got.equals(exp)) throw new AssertionError(name + ": expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check("ababcbacadefegdehijhklij", List.of(9, 7, 8), "example 1");
        check("eccbbbbdec", List.of(10), "example 2");
        check("a", List.of(1), "single char");
        check("abc", List.of(1, 1, 1), "all distinct");
        check("aaaa", List.of(4), "all same");
        check("abca", List.of(4), "wrap around");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) sb.append((char) ('a' + i % 26));
        check(sb.toString(), List.of(500), "max length repeating");
        Random r = new Random(5);
        for (int t = 0; t < 200; t++) {
            int n = 1 + r.nextInt(15);
            StringBuilder x = new StringBuilder();
            for (int i = 0; i < n; i++) x.append((char) ('a' + r.nextInt(5)));
            check(x.toString(), brute(x.toString()), "random " + x);
        }
        System.out.println("partition-labels: all tests passed");
    }
}
