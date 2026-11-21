import java.util.*;

public class SolutionTest {
    static int[] brute(int[] a, int k) {
        int[] r = new int[a.length - k + 1];
        for (int i = 0; i + k <= a.length; i++) {
            int m = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) m = Math.max(m, a[j]);
            r[i] = m;
        }
        return r;
    }

    static void check(int[] a, int k, int[] exp) {
        int[] got = new Solution().maxSlidingWindow(a, k);
        if (!Arrays.equals(got, exp))
            throw new AssertionError(Arrays.toString(a) + " k=" + k + " expected " + Arrays.toString(exp) + " got " + Arrays.toString(got));
    }

    public static void main(String[] args) {
        check(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3, new int[]{3, 3, 5, 5, 6, 7});
        check(new int[]{1}, 1, new int[]{1});
        check(new int[]{4, 2, 7}, 3, new int[]{7});
        check(new int[]{-1, -2, -3}, 1, new int[]{-1, -2, -3});
        check(new int[]{2, 2, 2, 2}, 2, new int[]{2, 2, 2});
        check(new int[]{9, 8, 7, 6, 5}, 2, new int[]{9, 8, 7, 6});
        int n = 100000;
        int[] big = new int[n];
        for (int i = 0; i < n; i++) big[i] = (i * 7919) % 20001 - 10000;
        check(big, 500, brute(big, 500));
        Random rnd = new Random(239);
        for (int it = 0; it < 200; it++) {
            int m = 1 + rnd.nextInt(15);
            int[] a = new int[m];
            for (int i = 0; i < m; i++) a[i] = rnd.nextInt(11) - 5;
            int k = 1 + rnd.nextInt(m);
            check(a, k, brute(a, k));
        }
        System.out.println("all sliding window maximum tests ok");
    }
}
