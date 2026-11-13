import java.util.*;

public class SolutionTest {
    static int brute(int[] p) {
        int best = 0;
        for (int i = 0; i < p.length; i++)
            for (int j = i + 1; j < p.length; j++) best = Math.max(best, p[j] - p[i]);
        return best;
    }

    static void check(int[] p, int exp) {
        int got = new Solution().maxProfit(p.clone());
        if (got != exp) throw new AssertionError("prices=" + Arrays.toString(p) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{7, 1, 5, 3, 6, 4}, 5);
        check(new int[]{7, 6, 4, 3, 1}, 0);
        check(new int[]{5}, 0);
        check(new int[]{3, 3, 3}, 0);
        check(new int[]{0, 10000}, 10000);
        check(new int[]{2, 4, 1, 7}, 6);
        int[] big = new int[100000];
        for (int i = 0; i < big.length; i++) big[i] = i % 10001;
        check(big, 10000);
        Random rnd = new Random(121);
        for (int k = 0; k < 200; k++) {
            int n = 1 + rnd.nextInt(25);
            int[] p = new int[n];
            for (int i = 0; i < n; i++) p[i] = rnd.nextInt(50);
            check(p, brute(p));
        }
        System.out.println("all stock tests ok");
    }
}
