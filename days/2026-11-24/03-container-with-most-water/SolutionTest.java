import java.util.*;

public class SolutionTest {
    static int brute(int[] a) {
        int best = 0;
        for (int i = 0; i < a.length; i++)
            for (int j = i + 1; j < a.length; j++) best = Math.max(best, Math.min(a[i], a[j]) * (j - i));
        return best;
    }

    static void check(int[] a, int exp) {
        int got = new Solution().maxArea(a);
        if (got != exp) throw new AssertionError(Arrays.toString(a) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}, 49);
        check(new int[]{1, 1}, 1);
        check(new int[]{0, 0}, 0);
        check(new int[]{5, 5, 5, 5}, 15);
        check(new int[]{1, 2, 3, 4, 5}, 6);
        int[] big = new int[100000];
        Arrays.fill(big, 10000);
        check(big, 10000 * 99999);
        Random rnd = new Random(11);
        for (int it = 0; it < 200; it++) {
            int n = 2 + rnd.nextInt(15);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(10);
            check(a, brute(a));
        }
        System.out.println("all container tests ok");
    }
}
