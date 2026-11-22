import java.util.*;

public class SolutionTest {
    static int brute(int[] a) {
        Set<Integer> set = new HashSet<>();
        for (int x : a) set.add(x);
        int k = 1;
        while (set.contains(k)) k++;
        return k;
    }

    static void check(int[] a, int exp) {
        int[] copy = a.clone();
        int got = new Solution().firstMissingPositive(copy);
        if (got != exp) throw new AssertionError(Arrays.toString(a) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{1, 2, 0}, 3);
        check(new int[]{3, 4, -1, 1}, 2);
        check(new int[]{7, 8, 9, 11, 12}, 1);
        check(new int[]{1}, 2);
        check(new int[]{2}, 1);
        check(new int[]{1, 1, 1}, 2);
        check(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, 1);
        check(new int[]{2, 2, 1, 1}, 3);
        int n = 100000;
        int[] big = new int[n];
        for (int i = 0; i < n; i++) big[i] = n - i;
        check(big, n + 1);
        Random rnd = new Random(41);
        for (int it = 0; it < 200; it++) {
            int m = 1 + rnd.nextInt(12);
            int[] a = new int[m];
            for (int i = 0; i < m; i++) a[i] = rnd.nextInt(m + 4) - 3;
            check(a, brute(a));
        }
        System.out.println("all first missing positive tests ok");
    }
}
