import java.util.*;

public class SolutionTest {
    static void check(int n, int[] exp) {
        int[] got = new Solution().countBits(n);
        if (!Arrays.equals(got, exp)) throw new AssertionError("n=" + n + " expected " + Arrays.toString(exp) + " got " + Arrays.toString(got));
    }

    public static void main(String[] args) {
        check(2, new int[]{0, 1, 1});
        check(5, new int[]{0, 1, 1, 2, 1, 2});
        check(0, new int[]{0});
        check(1, new int[]{0, 1});
        check(8, new int[]{0, 1, 1, 2, 1, 2, 2, 3, 1});
        int[] big = new Solution().countBits(100000);
        for (int i = 0; i <= 100000; i++)
            if (big[i] != Integer.bitCount(i)) throw new AssertionError("i=" + i + " expected " + Integer.bitCount(i) + " got " + big[i]);
        Random rnd = new Random(7);
        for (int it = 0; it < 200; it++) {
            int n = rnd.nextInt(500);
            int[] got = new Solution().countBits(n);
            if (got.length != n + 1) throw new AssertionError("wrong length for n=" + n);
            for (int i = 0; i <= n; i++)
                if (got[i] != Integer.bitCount(i)) throw new AssertionError("n=" + n + " i=" + i);
        }
        System.out.println("all counting bits tests ok");
    }
}
