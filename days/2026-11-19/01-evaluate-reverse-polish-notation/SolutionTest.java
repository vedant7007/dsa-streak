import java.util.*;

public class SolutionTest {
    static void check(String[] t, int exp) {
        int got = new Solution().evalRPN(t);
        if (got != exp) throw new AssertionError(Arrays.toString(t) + " expected " + exp + " got " + got);
    }

    static int brute(int[] v, char[] ops) {
        int acc = v[0];
        for (int i = 0; i < ops.length; i++) {
            int b = v[i + 1];
            if (ops[i] == '+') acc += b;
            else if (ops[i] == '-') acc -= b;
            else if (ops[i] == '*') acc *= b;
            else acc /= b;
        }
        return acc;
    }

    public static void main(String[] args) {
        check(new String[]{"2", "1", "+", "3", "*"}, 9);
        check(new String[]{"4", "13", "5", "/", "+"}, 6);
        check(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}, 22);
        check(new String[]{"7"}, 7);
        check(new String[]{"-200"}, -200);
        check(new String[]{"-7", "2", "/"}, -3);
        check(new String[]{"3", "5", "-"}, -2);
        check(new String[]{"0", "5", "*"}, 0);
        Random rnd = new Random(150);
        char[] all = {'+', '-', '*', '/'};
        for (int it = 0; it < 200; it++) {
            int k = 1 + rnd.nextInt(5);
            int[] v = new int[k + 1];
            char[] ops = new char[k];
            for (int i = 0; i <= k; i++) v[i] = rnd.nextInt(21) - 10;
            for (int i = 0; i < k; i++) {
                ops[i] = all[rnd.nextInt(4)];
                if (ops[i] == '/' && v[i + 1] == 0) v[i + 1] = 3;
            }
            List<String> t = new ArrayList<>();
            t.add(String.valueOf(v[0]));
            for (int i = 0; i < k; i++) {
                t.add(String.valueOf(v[i + 1]));
                t.add(String.valueOf(ops[i]));
            }
            check(t.toArray(new String[0]), brute(v, ops));
        }
        System.out.println("all evalRPN tests ok");
    }
}
