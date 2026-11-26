import java.util.*;

public class SolutionTest {
    static int brute(int[] a, int t) {
        for (int i = 0; i < a.length; i++) if (a[i] == t) return i;
        return -1;
    }

    static void check(int[] a, int t, int exp, String name) {
        int got = new Solution().search(a, t);
        if (got != exp) throw new AssertionError(name + ": expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{-1, 0, 3, 5, 9, 12}, 9, 4, "example 1");
        check(new int[]{-1, 0, 3, 5, 9, 12}, 2, -1, "example 2");
        check(new int[]{5}, 5, 0, "single found");
        check(new int[]{5}, -5, -1, "single missing");
        check(new int[]{-9999, -5, 0}, -9999, 0, "first element negative");
        check(new int[]{1, 2, 3, 4}, 4, 3, "last element");
        check(new int[]{1, 2, 3, 4}, 100, -1, "above range");
        int[] big = new int[10000];
        for (int i = 0; i < big.length; i++) big[i] = 2 * i - 9999;
        check(big, big[7777], 7777, "max size");
        Random r = new Random(11);
        for (int t = 0; t < 200; t++) {
            int n = 1 + r.nextInt(12);
            TreeSet<Integer> s = new TreeSet<>();
            while (s.size() < n) s.add(r.nextInt(41) - 20);
            int[] a = new int[n];
            int i = 0;
            for (int x : s) a[i++] = x;
            int tg = r.nextInt(41) - 20;
            check(a, tg, brute(a, tg), "random " + Arrays.toString(a) + " t=" + tg);
        }
        System.out.println("binary-search: all tests passed");
    }
}
