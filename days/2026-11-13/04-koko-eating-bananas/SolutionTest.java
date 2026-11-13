import java.util.*;

public class SolutionTest {
    static int brute(int[] piles, int h) {
        for (int k = 1; ; k++) {
            long t = 0;
            for (int p : piles) t += (p + k - 1) / k;
            if (t <= h) return k;
        }
    }

    static void check(int[] piles, int h, int exp) {
        int got = new Solution().minEatingSpeed(piles.clone(), h);
        if (got != exp) throw new AssertionError("piles=" + Arrays.toString(piles) + " h=" + h + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{3, 6, 7, 11}, 8, 4);
        check(new int[]{30, 11, 23, 4, 20}, 5, 30);
        check(new int[]{30, 11, 23, 4, 20}, 6, 23);
        check(new int[]{1}, 1, 1);
        check(new int[]{1000000000}, 2, 500000000);
        check(new int[]{1000000000}, 1000000000, 1);
        check(new int[]{5, 5, 5}, 3, 5);
        int[] big = new int[10000];
        Arrays.fill(big, 1000000000);
        check(big, 10000, 1000000000);
        check(big, 1000000000, 10000);
        Random rnd = new Random(875);
        for (int k = 0; k < 200; k++) {
            int n = 1 + rnd.nextInt(8);
            int[] p = new int[n];
            for (int i = 0; i < n; i++) p[i] = 1 + rnd.nextInt(40);
            int h = n + rnd.nextInt(60);
            check(p, h, brute(p, h));
        }
        System.out.println("all koko tests ok");
    }
}
