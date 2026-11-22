import java.util.*;

public class SolutionTest {
    static int brute(int[] g, int[] c) {
        int n = g.length;
        for (int s = 0; s < n; s++) {
            int tank = 0;
            boolean ok = true;
            for (int k = 0; k < n; k++) {
                int i = (s + k) % n;
                tank += g[i] - c[i];
                if (tank < 0) { ok = false; break; }
            }
            if (ok) return s;
        }
        return -1;
    }

    static void check(int[] g, int[] c, int exp) {
        int got = new Solution().canCompleteCircuit(g, c);
        if (got != exp) throw new AssertionError((g.length > 20 ? "n=" + g.length : Arrays.toString(g) + " " + Arrays.toString(c)) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}, 3);
        check(new int[]{2, 3, 4}, new int[]{3, 4, 3}, -1);
        check(new int[]{5}, new int[]{4}, 0);
        check(new int[]{1}, new int[]{2}, -1);
        check(new int[]{0, 0, 0}, new int[]{0, 0, 0}, 0);
        check(new int[]{3, 1, 1}, new int[]{1, 2, 2}, 0);
        int n = 100000;
        int[] g = new int[n], c = new int[n];
        Arrays.fill(g, 10000);
        Arrays.fill(c, 10000);
        c[n - 2] = 10001;
        g[n - 1] = 10001;
        check(g, c, n - 1);
        Random rnd = new Random(134);
        int found = 0;
        for (int it = 0; it < 200; it++) {
            int m = 1 + rnd.nextInt(8);
            int[] a = new int[m], b = new int[m];
            for (int i = 0; i < m; i++) { a[i] = rnd.nextInt(6); b[i] = rnd.nextInt(6); }
            int exp = brute(a, b);
            if (exp >= 0) found++;
            check(a, b, exp);
        }
        if (found == 0) throw new AssertionError("random cases never had a valid start");
        System.out.println("all gas station tests ok");
    }
}
