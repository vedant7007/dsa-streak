import java.util.*;

public class SolutionTest {
    static int[] brute(int[] t) {
        int n = t.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (t[j] > t[i]) { a[i] = j - i; break; }
        return a;
    }

    static void check(int[] t, int[] exp) {
        int[] got = new Solution().dailyTemperatures(t.clone());
        if (!Arrays.equals(got, exp))
            throw new AssertionError("t=" + Arrays.toString(t) + " expected " + Arrays.toString(exp) + " got " + Arrays.toString(got));
    }

    public static void main(String[] args) {
        check(new int[]{73, 74, 75, 71, 69, 72, 76, 73}, new int[]{1, 1, 4, 2, 1, 1, 0, 0});
        check(new int[]{30, 40, 50, 60}, new int[]{1, 1, 1, 0});
        check(new int[]{30, 60, 90}, new int[]{1, 1, 0});
        check(new int[]{50}, new int[]{0});
        check(new int[]{70, 70, 70}, new int[]{0, 0, 0});
        check(new int[]{100, 90, 80, 70}, new int[]{0, 0, 0, 0});
        int[] big = new int[100000];
        Arrays.fill(big, 30);
        big[99999] = 100;
        int[] e = new int[100000];
        for (int i = 0; i < 99999; i++) e[i] = 99999 - i;
        check(big, e);
        Random rnd = new Random(13);
        for (int k = 0; k < 200; k++) {
            int n = 1 + rnd.nextInt(30);
            int[] t = new int[n];
            for (int i = 0; i < n; i++) t[i] = 30 + rnd.nextInt(71);
            check(t, brute(t));
        }
        System.out.println("all daily temperatures tests ok");
    }
}
