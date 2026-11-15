import java.util.*;

public class SolutionTest {
    static boolean brute(int[] a) {
        boolean[] ok = new boolean[a.length];
        ok[0] = true;
        for (int i = 0; i < a.length; i++)
            if (ok[i]) for (int j = i + 1; j <= i + a[i] && j < a.length; j++) ok[j] = true;
        return ok[a.length - 1];
    }

    static void check(int[] a, boolean exp) {
        boolean got = new Solution().canJump(a);
        if (got != exp) throw new AssertionError("nums=" + Arrays.toString(a) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{2, 3, 1, 1, 4}, true);
        check(new int[]{3, 2, 1, 0, 4}, false);
        check(new int[]{0}, true);
        check(new int[]{0, 1}, false);
        check(new int[]{1, 0}, true);
        check(new int[]{2, 0, 0}, true);
        int[] big = new int[10000];
        Arrays.fill(big, 1);
        check(big, true);
        big[5000] = 0;
        check(big, false);
        Random rnd = new Random(55);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(12);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(4);
            check(a, brute(a));
        }
        System.out.println("all jump game tests ok");
    }
}
