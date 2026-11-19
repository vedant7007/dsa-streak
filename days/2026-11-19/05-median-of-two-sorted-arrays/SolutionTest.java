import java.util.*;

public class SolutionTest {
    static double brute(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        Arrays.sort(c);
        int n = c.length;
        return n % 2 == 1 ? c[n / 2] : (c[n / 2 - 1] + c[n / 2]) / 2.0;
    }

    static void check(int[] a, int[] b, double exp) {
        double got = new Solution().findMedianSortedArrays(a, b);
        if (Math.abs(got - exp) > 1e-5) throw new AssertionError(Arrays.toString(a) + " " + Arrays.toString(b) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{1, 3}, new int[]{2}, 2.0);
        check(new int[]{1, 2}, new int[]{3, 4}, 2.5);
        check(new int[]{}, new int[]{1}, 1.0);
        check(new int[]{2}, new int[]{}, 2.0);
        check(new int[]{0, 0}, new int[]{0, 0}, 0.0);
        check(new int[]{-1000000}, new int[]{1000000}, 0.0);
        check(new int[]{1, 2, 3, 4, 5}, new int[]{6}, 3.5);
        check(new int[]{-5, -3, -1}, new int[]{-4, -2}, -3.0);
        Random rnd = new Random(4);
        for (int it = 0; it < 200; it++) {
            int n = rnd.nextInt(6), m = rnd.nextInt(6);
            if (n + m == 0) m = 1;
            int[] a = new int[n], b = new int[m];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(21) - 10;
            for (int i = 0; i < m; i++) b[i] = rnd.nextInt(21) - 10;
            Arrays.sort(a);
            Arrays.sort(b);
            check(a, b, brute(a, b));
        }
        System.out.println("all median tests ok");
    }
}
