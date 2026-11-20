import java.util.*;

public class SolutionTest {
    static int brute(int[] a) {
        int n = a.length, best = 0;
        for (int mask = 0; mask < (1 << n); mask++) {
            TreeSet<Integer> s = new TreeSet<>();
            for (int i = 0; i < n; i++) s.add(a[i] + ((mask >> i) & 1));
            int run = 0, prev = -5;
            for (int v : s) {
                run = (v == prev + 1) ? run + 1 : 1;
                prev = v;
                best = Math.max(best, run);
            }
        }
        return best;
    }

    static void check(int[] a, int exp) {
        int got = new Solution().maxSelectedElements(a.clone());
        if (got != exp) throw new AssertionError(Arrays.toString(a) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{2, 1, 5, 1, 1}, 3);
        check(new int[]{1, 4, 7, 10}, 1);
        check(new int[]{1}, 1);
        check(new int[]{5, 5}, 2);
        check(new int[]{1, 1, 1, 1}, 2);
        check(new int[]{1000000, 1000000, 999999}, 3);
        check(new int[]{1, 3, 5, 7}, 2);
        check(new int[]{1, 2, 3, 4, 5}, 5);
        Random rnd = new Random(3041);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(9);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = 1 + rnd.nextInt(8);
            check(a, brute(a));
        }
        System.out.println("all maximize consecutive elements tests ok");
    }
}
