import java.util.*;

public class SolutionTest {
    static int[] brute(int[] a) {
        int[] r = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            int p = 1;
            for (int j = 0; j < a.length; j++) if (j != i) p *= a[j];
            r[i] = p;
        }
        return r;
    }

    static void check(int[] a, int[] exp) {
        int[] got = new Solution().productExceptSelf(a.clone());
        if (!Arrays.equals(got, exp))
            throw new AssertionError("nums=" + Arrays.toString(a) + " expected " + Arrays.toString(exp) + " got " + Arrays.toString(got));
    }

    public static void main(String[] args) {
        check(new int[]{1, 2, 3, 4}, new int[]{24, 12, 8, 6});
        check(new int[]{-1, 1, 0, -3, 3}, new int[]{0, 0, 9, 0, 0});
        check(new int[]{2, 3}, new int[]{3, 2});
        check(new int[]{0, 0}, new int[]{0, 0});
        check(new int[]{-2, -2, -2}, new int[]{4, 4, 4});
        check(new int[]{0, 4, 5}, new int[]{20, 0, 0});
        int[] big = new int[100000];
        Arrays.fill(big, 1);
        int[] e = new int[100000];
        Arrays.fill(e, 1);
        check(big, e);
        Random rnd = new Random(238);
        for (int k = 0; k < 200; k++) {
            int n = 2 + rnd.nextInt(7);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(9) - 4;
            check(a, brute(a));
        }
        System.out.println("all product except self tests ok");
    }
}
