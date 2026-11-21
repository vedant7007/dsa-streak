import java.util.*;

public class SolutionTest {
    static int brute(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : a) if (map.merge(x, 1, Integer::sum) > a.length / 2) return x;
        throw new IllegalStateException("no majority");
    }

    static void check(int[] a, int exp) {
        int got = new Solution().majorityElement(a);
        if (got != exp) throw new AssertionError(Arrays.toString(a) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{3, 2, 3}, 3);
        check(new int[]{2, 2, 1, 1, 1, 2, 2}, 2);
        check(new int[]{7}, 7);
        check(new int[]{-1000000000, -1000000000, 1000000000}, -1000000000);
        check(new int[]{5, 5, 5, 5}, 5);
        check(new int[]{1, 2, 1, 3, 1}, 1);
        Random rnd = new Random(169);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(15);
            int maj = rnd.nextInt(7) - 3;
            int need = n / 2 + 1 + rnd.nextInt(n - n / 2);
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < need; i++) l.add(maj);
            while (l.size() < n) { int v = rnd.nextInt(7) - 3; if (v != maj) l.add(v); }
            Collections.shuffle(l, rnd);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = l.get(i);
            check(a, brute(a));
        }
        System.out.println("all majority element tests ok");
    }
}
