import java.util.*;

public class SolutionTest {
    static int brute(int[] a, int i) {
        if (i >= a.length) return 0;
        return Math.max(brute(a, i + 1), a[i] + brute(a, i + 2));
    }

    static void check(int[] a, int exp) {
        int got = new Solution().rob(a);
        if (got != exp) throw new AssertionError(Arrays.toString(a) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{1, 2, 3, 1}, 4);
        check(new int[]{2, 7, 9, 3, 1}, 12);
        check(new int[]{5}, 5);
        check(new int[]{0, 0, 0}, 0);
        check(new int[]{2, 1, 1, 2}, 4);
        check(new int[]{400, 400}, 400);
        int[] big = new int[100];
        Arrays.fill(big, 400);
        check(big, 50 * 400);
        Random rnd = new Random(198);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(15);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(401);
            check(a, brute(a, 0));
        }
        System.out.println("all house robber tests ok");
    }
}
