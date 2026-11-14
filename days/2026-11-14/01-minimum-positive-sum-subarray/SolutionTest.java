import java.util.*;

public class SolutionTest {
    static List<Integer> list(int... a) {
        List<Integer> res = new ArrayList<>();
        for (int x : a) res.add(x);
        return res;
    }

    static int brute(int[] a, int l, int r) {
        int best = -1;
        for (int len = l; len <= r; len++)
            for (int i = 0; i + len <= a.length; i++) {
                int s = 0;
                for (int k = i; k < i + len; k++) s += a[k];
                if (s > 0 && (best == -1 || s < best)) best = s;
            }
        return best;
    }

    static void check(int[] a, int l, int r, int exp) {
        int got = new Solution().minimumSumSubarray(list(a), l, r);
        if (got != exp) throw new AssertionError("nums=" + Arrays.toString(a) + " l=" + l + " r=" + r + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{3, -2, 1, 4}, 2, 3, 1);
        check(new int[]{-2, 2, -3, 1}, 2, 3, -1);
        check(new int[]{1, 2, 3, 4}, 2, 4, 3);
        check(new int[]{5}, 1, 1, 5);
        check(new int[]{-5}, 1, 1, -1);
        check(new int[]{0, 0, 0}, 1, 3, -1);
        check(new int[]{2, 2, 2}, 3, 3, 6);
        check(new int[]{-1000, 1000, -999}, 1, 3, 1);
        int[] big = new int[100];
        Arrays.fill(big, 1000);
        check(big, 100, 100, 100000);
        Random rnd = new Random(14);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(12);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(21) - 10;
            int l = 1 + rnd.nextInt(n);
            int r = l + rnd.nextInt(n - l + 1);
            check(a, l, r, brute(a, l, r));
        }
        System.out.println("all minimum positive sum subarray tests ok");
    }
}
