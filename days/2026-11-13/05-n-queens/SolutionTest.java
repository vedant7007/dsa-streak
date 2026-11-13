import java.util.*;

public class SolutionTest {
    static boolean valid(List<String> b, int n) {
        if (b.size() != n) return false;
        int[] q = new int[n];
        for (int r = 0; r < n; r++) {
            String s = b.get(r);
            if (s.length() != n || s.indexOf('Q') < 0 || s.indexOf('Q') != s.lastIndexOf('Q')) return false;
            if (s.replace("Q", "").replace(".", "").length() != 0) return false;
            q[r] = s.indexOf('Q');
        }
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (q[i] == q[j] || Math.abs(q[i] - q[j]) == j - i) return false;
        return true;
    }

    static List<String> sorted(List<List<String>> x) {
        List<String> s = new ArrayList<>();
        for (List<String> b : x) s.add(String.join("|", b));
        Collections.sort(s);
        return s;
    }

    public static void main(String[] args) {
        int[] counts = {0, 1, 0, 0, 2, 10, 4, 40, 92, 352};
        List<List<String>> ex1 = List.of(List.of(".Q..", "...Q", "Q...", "..Q."), List.of("..Q.", "Q...", "...Q", ".Q.."));
        List<List<String>> got4 = new Solution().solveNQueens(4);
        if (!sorted(got4).equals(sorted(ex1))) throw new AssertionError("n=4 expected " + ex1 + " got " + got4);
        List<List<String>> got1 = new Solution().solveNQueens(1);
        if (!got1.equals(List.of(List.of("Q")))) throw new AssertionError("n=1 expected [[Q]] got " + got1);
        for (int n = 1; n <= 9; n++) {
            List<List<String>> res = new Solution().solveNQueens(n);
            if (res.size() != counts[n]) throw new AssertionError("n=" + n + " expected " + counts[n] + " boards got " + res.size());
            if (new HashSet<>(res).size() != res.size()) throw new AssertionError("n=" + n + " has duplicate boards");
            for (List<String> b : res) if (!valid(b, n)) throw new AssertionError("n=" + n + " invalid board " + b);
        }
        System.out.println("all n-queens tests ok");
    }
}
