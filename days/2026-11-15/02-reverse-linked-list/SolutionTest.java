import java.util.*;

public class SolutionTest {
    static void check(int[] in, int[] exp) {
        List<Integer> got = Support.toList(new Solution().reverseList(Support.build(in)));
        List<Integer> e = new ArrayList<>();
        for (int x : exp) e.add(x);
        if (!got.equals(e)) throw new AssertionError("head=" + Arrays.toString(in) + " expected " + e + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1});
        check(new int[]{1, 2}, new int[]{2, 1});
        check(new int[]{}, new int[]{});
        check(new int[]{7}, new int[]{7});
        check(new int[]{-5000, 5000, -5000}, new int[]{-5000, 5000, -5000});
        check(new int[]{3, 3, 3}, new int[]{3, 3, 3});
        Random rnd = new Random(206);
        for (int t = 0; t < 200; t++) {
            int n = rnd.nextInt(30);
            int[] a = new int[n], r = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(10001) - 5000;
            for (int i = 0; i < n; i++) r[i] = a[n - 1 - i];
            check(a, r);
        }
        int[] big = new int[5000], rb = new int[5000];
        for (int i = 0; i < 5000; i++) { big[i] = i; rb[i] = 4999 - i; }
        check(big, rb);
        System.out.println("all reverse linked list tests ok");
    }
}
