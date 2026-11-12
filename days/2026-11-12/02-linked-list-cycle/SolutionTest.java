import java.util.*;

public class SolutionTest {
    static void check(int[] vals, int pos, boolean exp) {
        boolean got = new Solution().hasCycle(Support.build(vals, pos));
        if (got != exp) throw new AssertionError("vals=" + Arrays.toString(vals) + " pos=" + pos + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{3, 2, 0, -4}, 1, true);
        check(new int[]{1, 2}, 0, true);
        check(new int[]{1}, -1, false);
        check(new int[]{}, -1, false);
        check(new int[]{1}, 0, true);
        check(new int[]{5, 5, 5, 5}, -1, false);
        check(new int[]{-1, -2, -3}, 2, true);
        int[] big = new int[10000];
        check(big, -1, false);
        check(big, 9999, true);
        Random rnd = new Random(3);
        for (int t = 0; t < 200; t++) {
            int n = rnd.nextInt(30);
            int[] v = new int[n];
            for (int i = 0; i < n; i++) v[i] = rnd.nextInt(21) - 10;
            int pos = n == 0 ? -1 : rnd.nextInt(n + 1) - 1;
            check(v, pos, pos >= 0);
        }
        System.out.println("all linked list cycle tests ok");
    }
}
