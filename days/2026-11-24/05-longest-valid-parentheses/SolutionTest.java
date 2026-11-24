import java.util.*;

public class SolutionTest {
    static boolean valid(String s) {
        int bal = 0;
        for (char c : s.toCharArray()) {
            bal += c == '(' ? 1 : -1;
            if (bal < 0) return false;
        }
        return bal == 0;
    }

    static int brute(String s) {
        int best = 0;
        for (int i = 0; i < s.length(); i++)
            for (int j = i + 2; j <= s.length(); j += 2)
                if (valid(s.substring(i, j))) best = Math.max(best, j - i);
        return best;
    }

    static void check(String s, int exp) {
        int got = new Solution().longestValidParentheses(s);
        if (got != exp) throw new AssertionError("\"" + s + "\" expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check("(()", 2);
        check(")()())", 4);
        check("", 0);
        check("(", 0);
        check(")))(((", 0);
        check("()(())", 6);
        check("()(()", 2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 15000; i++) sb.append("()");
        check(sb.toString(), 30000);
        Random rnd = new Random(32);
        for (int it = 0; it < 200; it++) {
            int n = rnd.nextInt(16);
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < n; i++) t.append(rnd.nextBoolean() ? '(' : ')');
            check(t.toString(), brute(t.toString()));
        }
        System.out.println("all longest valid parentheses tests ok");
    }
}
