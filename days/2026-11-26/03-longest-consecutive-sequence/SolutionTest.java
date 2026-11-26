import java.util.*;

public class SolutionTest {
    static int brute(int[] a) {
        int[] b = a.clone();
        Arrays.sort(b);
        int best = 0, len = 0;
        for (int i = 0; i < b.length; i++) {
            if (i > 0 && b[i] == b[i - 1]) continue;
            if (i > 0 && (long) b[i] - b[i - 1] == 1) len++;
            else len = 1;
            best = Math.max(best, len);
        }
        return best;
    }

    static void check(int[] a, int exp, String name) {
        int got = new Solution().longestConsecutive(a);
        if (got != exp) throw new AssertionError(name + ": expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{100, 4, 200, 1, 3, 2}, 4, "example 1");
        check(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}, 9, "example 2");
        check(new int[]{1, 0, 1, 2}, 3, "example 3");
        check(new int[]{}, 0, "empty");
        check(new int[]{7}, 1, "single");
        check(new int[]{5, 5, 5}, 1, "all duplicates");
        check(new int[]{-3, -1, -2, 0}, 4, "negatives");
        check(new int[]{-1000000000, 1000000000}, 1, "extremes");
        int[] big = new int[100000];
        for (int i = 0; i < big.length; i++) big[i] = big.length - i;
        check(big, 100000, "max size reversed run");
        Random r = new Random(3);
        for (int t = 0; t < 200; t++) {
            int n = r.nextInt(15);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = r.nextInt(21) - 10;
            check(a, brute(a), "random " + Arrays.toString(a));
        }
        System.out.println("longest-consecutive-sequence: all tests passed");
    }
}
