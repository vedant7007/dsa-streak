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

    static List<String> brute(int n) {
        List<String> out = new ArrayList<>();
        for (int mask = 0; mask < (1 << (2 * n)); mask++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 2 * n - 1; i >= 0; i--) sb.append((mask >> i & 1) == 0 ? '(' : ')');
            if (valid(sb.toString())) out.add(sb.toString());
        }
        Collections.sort(out);
        return out;
    }

    static void check(int n, List<String> exp) {
        List<String> got = new ArrayList<>(new Solution().generateParenthesis(n));
        Collections.sort(got);
        List<String> e = new ArrayList<>(exp);
        Collections.sort(e);
        if (!got.equals(e)) throw new AssertionError("n=" + n + " expected " + e + " got " + got);
        if (new HashSet<>(got).size() != got.size()) throw new AssertionError("duplicates for n=" + n);
    }

    public static void main(String[] args) {
        check(3, Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()"));
        check(1, Arrays.asList("()"));
        check(2, Arrays.asList("(())", "()()"));
        int[] catalan = {1, 1, 2, 5, 14, 42, 132, 429, 1430};
        for (int n = 1; n <= 8; n++) {
            check(n, brute(n));
            int sz = new Solution().generateParenthesis(n).size();
            if (sz != catalan[n]) throw new AssertionError("n=" + n + " size " + sz + " expected " + catalan[n]);
        }
        System.out.println("all tests passed");
    }
}
