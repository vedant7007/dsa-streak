import java.util.*;

public class SolutionTest {
    static int brute(int[] a) {
        long best = Long.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            long p = 1;
            for (int j = i; j < a.length; j++) {
                p *= a[j];
                best = Math.max(best, p);
            }
        }
        return (int) best;
    }

    static void check(int[] a, int exp, String name) {
        int got = new Solution().maxProduct(a);
        if (got != exp) throw new AssertionError(name + ": expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{2, 3, -2, 4}, 6, "example 1");
        check(new int[]{-2, 0, -1}, 0, "example 2");
        check(new int[]{-5}, -5, "single negative");
        check(new int[]{0}, 0, "single zero");
        check(new int[]{-2, 3, -4}, 24, "two negatives");
        check(new int[]{-2, -3, 0, -1, -1}, 6, "zeros split");
        check(new int[]{-1, -1, -1}, 1, "odd negatives");
        int[] big = new int[20000];
        Arrays.fill(big, 1);
        big[100] = 10;
        big[19999] = -10;
        check(big, 10, "max size mostly ones");
        Random r = new Random(17);
        for (int t = 0; t < 200; t++) {
            int n = 1 + r.nextInt(8);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = r.nextInt(21) - 10;
            check(a, brute(a), "random " + Arrays.toString(a));
        }
        System.out.println("maximum-product-subarray: all tests passed");
    }
}
