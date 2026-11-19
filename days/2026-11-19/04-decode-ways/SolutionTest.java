import java.util.*;

public class SolutionTest {
    static int brute(String s, int i) {
        if (i == s.length()) return 1;
        if (s.charAt(i) == '0') return 0;
        int res = brute(s, i + 1);
        if (i + 1 < s.length() && Integer.parseInt(s.substring(i, i + 2)) <= 26) res += brute(s, i + 2);
        return res;
    }

    static void check(String s, int exp) {
        int got = new Solution().numDecodings(s);
        if (got != exp) throw new AssertionError(s + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check("12", 2);
        check("226", 3);
        check("06", 0);
        check("0", 0);
        check("1", 1);
        check("10", 1);
        check("27", 1);
        check("100", 0);
        check("2101", 1);
        check("11106", 2);
        Random rnd = new Random(91);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(12);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) sb.append((char) ('0' + (rnd.nextInt(4) == 0 ? 0 : rnd.nextInt(10))));
            check(sb.toString(), brute(sb.toString(), 0));
        }
        System.out.println("all decode ways tests ok");
    }
}
