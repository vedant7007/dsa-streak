import java.util.*;

public class SolutionTest {
    static int brute(int[] a) {
        int ans = 0;
        for (int i = 0; i < a.length; i++) {
            int mn = Integer.MAX_VALUE;
            for (int j = i; j < a.length; j++) {
                mn = Math.min(mn, a[j]);
                ans = Math.max(ans, mn * (j - i + 1));
            }
        }
        return ans;
    }

    static void check(int[] a, int exp) {
        int got = new Solution().largestRectangleArea(a.clone());
        if (got != exp) throw new AssertionError("heights=" + Arrays.toString(a) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{2, 1, 5, 6, 2, 3}, 10);
        check(new int[]{2, 4}, 4);
        check(new int[]{0}, 0);
        check(new int[]{7}, 7);
        check(new int[]{3, 3, 3, 3}, 12);
        check(new int[]{1, 2, 3, 4, 5}, 9);
        check(new int[]{5, 4, 3, 2, 1}, 9);
        int[] big = new int[100000];
        Arrays.fill(big, 10000);
        check(big, 1000000000);
        Random rnd = new Random(84);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(15);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(8);
            check(a, brute(a));
        }
        System.out.println("all largest rectangle in histogram tests ok");
    }
}
