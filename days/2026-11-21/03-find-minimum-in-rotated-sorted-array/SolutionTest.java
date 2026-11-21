import java.util.*;

public class SolutionTest {
    static void check(int[] a, int exp) {
        int got = new Solution().findMin(a);
        if (got != exp) throw new AssertionError(Arrays.toString(a) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{3, 4, 5, 1, 2}, 1);
        check(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
        check(new int[]{11, 13, 15, 17}, 11);
        check(new int[]{1}, 1);
        check(new int[]{2, 1}, 1);
        check(new int[]{-5000, 5000}, -5000);
        check(new int[]{5000, -5000}, -5000);
        Random rnd = new Random(153);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(20);
            TreeSet<Integer> set = new TreeSet<>();
            while (set.size() < n) set.add(rnd.nextInt(10001) - 5000);
            int[] s = new int[n];
            int i = 0;
            for (int v : set) s[i++] = v;
            int k = rnd.nextInt(n);
            int[] a = new int[n];
            for (int j = 0; j < n; j++) a[j] = s[(j + k) % n];
            check(a, s[0]);
        }
        System.out.println("all find minimum in rotated sorted array tests ok");
    }
}
