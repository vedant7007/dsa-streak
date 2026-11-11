import java.util.*;

public class SolutionTest {
    static void check(int[] h, int expected, String label) {
        int got = new Solution().trap(h.clone());
        if (got != expected) throw new AssertionError(label + ": expected " + expected + " but got " + got + " for " + Arrays.toString(h));
    }

    static int brute(int[] h) {
        int total = 0;
        for (int i = 0; i < h.length; i++) {
            int lm = 0, rm = 0;
            for (int j = 0; j <= i; j++) lm = Math.max(lm, h[j]);
            for (int j = i; j < h.length; j++) rm = Math.max(rm, h[j]);
            total += Math.min(lm, rm) - h[i];
        }
        return total;
    }

    public static void main(String[] args) {
        check(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6, "Example 1");
        check(new int[]{4, 2, 0, 3, 2, 5}, 9, "Example 2");
        check(new int[]{5}, 0, "single bar");
        check(new int[]{1, 2, 3, 4}, 0, "strictly increasing");
        check(new int[]{3, 3, 3}, 0, "all equal");
        check(new int[]{5, 0, 5}, 5, "simple bowl");
        check(new int[]{0, 0, 0}, 0, "all zero");
        int[] big = new int[20000];
        big[0] = 100000; big[19999] = 100000;
        check(big, 100000 * 19998, "max size, tall walls at ends");
        Random rnd = new Random(9);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(12);
            int[] h = new int[n];
            for (int i = 0; i < n; i++) h[i] = rnd.nextInt(6);
            check(h, brute(h), "random #" + t);
        }
        System.out.println("All tests passed");
    }
}
