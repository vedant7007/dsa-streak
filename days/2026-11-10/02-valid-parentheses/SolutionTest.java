import java.util.Random;

public class SolutionTest {
    static void check(boolean got, boolean expected, String label) {
        if (got != expected) throw new AssertionError(label + ": expected " + expected + " but got " + got);
    }

    // brute force: repeatedly delete adjacent matching pairs
    static boolean brute(String s) {
        String prev;
        do {
            prev = s;
            s = s.replace("()", "").replace("[]", "").replace("{}", "");
        } while (!s.equals(prev));
        return s.isEmpty();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        check(sol.isValid("()"), true, "Example 1");
        check(sol.isValid("()[]{}"), true, "Example 2");
        check(sol.isValid("(]"), false, "Example 3");
        check(sol.isValid("([])"), true, "Example 4");
        check(sol.isValid("([)]"), false, "Example 5");
        // edge cases
        check(sol.isValid("("), false, "single opener");
        check(sol.isValid("]"), false, "single closer");
        check(sol.isValid("(("), false, "only openers");
        check(sol.isValid("))(("), false, "closers before openers");
        check(sol.isValid("{[()()]}"), true, "deep nesting");
        StringBuilder big = new StringBuilder();
        for (int i = 0; i < 5000; i++) big.append('(');
        for (int i = 0; i < 5000; i++) big.append(')');
        check(sol.isValid(big.toString()), true, "max length 10^4");
        Random rnd = new Random(7);
        String chars = "()[]{}";
        for (int t = 0; t < 200; t++) {
            int len = 1 + rnd.nextInt(10);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) sb.append(chars.charAt(rnd.nextInt(6)));
            String in = sb.toString();
            check(sol.isValid(in), brute(in), "random \"" + in + "\"");
        }
        System.out.println("All tests passed");
    }
}
