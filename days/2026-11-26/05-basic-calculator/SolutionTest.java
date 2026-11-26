import java.util.*;

public class SolutionTest {
    // recursive descent reference evaluator
    static String e;
    static int p;

    static long expr() {
        long v = term();
        while (p < e.length() && (e.charAt(p) == '+' || e.charAt(p) == '-')) {
            char op = e.charAt(p++);
            long t = term();
            v = op == '+' ? v + t : v - t;
        }
        return v;
    }

    static long term() {
        char c = e.charAt(p);
        if (c == '-') { p++; return -term(); }
        if (c == '(') { p++; long v = expr(); p++; return v; }
        long v = 0;
        while (p < e.length() && Character.isDigit(e.charAt(p))) v = v * 10 + (e.charAt(p++) - '0');
        return v;
    }

    static int brute(String s) {
        e = s.replace(" ", "");
        p = 0;
        return (int) expr();
    }

    static Random r = new Random(23);

    static String gen(int d) {
        StringBuilder sb = new StringBuilder();
        int k = 1 + r.nextInt(3);
        for (int i = 0; i < k; i++) {
            if (i > 0) sb.append(r.nextBoolean() ? " + " : "-");
            else if (r.nextInt(4) == 0) sb.append("-");
            if (d > 0 && r.nextInt(3) == 0) sb.append("(").append(gen(d - 1)).append(")");
            else sb.append(r.nextInt(100));
        }
        return sb.toString();
    }

    static void check(String s, int exp, String name) {
        int got = new Solution().calculate(s);
        if (got != exp) throw new AssertionError(name + ": \"" + s + "\" expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check("1 + 1", 2, "example 1");
        check(" 2-1 + 2 ", 3, "example 2");
        check("(1+(4+5+2)-3)+(6+8)", 23, "example 3");
        check("7", 7, "single number");
        check("-(2 + 3)", -5, "unary minus on group");
        check("-1", -1, "unary minus number");
        check("1-(-2)", 3, "minus a negative");
        check("2147483647", 2147483647, "max int");
        check("1 - (5 - (3 - 10))", -11, "nested minus");
        check("((((((((((5))))))))))", 5, "deep parens");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50000; i++) sb.append(i % 2 == 0 ? "1+" : "(2-1)-");
        sb.append("0");
        check(sb.toString(), brute(sb.toString()), "long input");
        for (int t = 0; t < 200; t++) {
            String s = gen(3);
            check(s, brute(s), "random");
        }
        System.out.println("basic-calculator: all tests passed");
    }
}
