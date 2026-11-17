import java.util.*;

public class SolutionTest {
    static void check(int[] a, int[] b) {
        List<Integer> e = new ArrayList<>();
        for (int x : a) e.add(x);
        for (int x : b) e.add(x);
        Collections.sort(e);
        List<Integer> got = Support.toList(new Solution().mergeTwoLists(Support.build(a), Support.build(b)));
        if (!got.equals(e)) throw new AssertionError("list1=" + Arrays.toString(a) + " list2=" + Arrays.toString(b) + " expected " + e + " got " + got);
    }

    static void checkExact(int[] a, int[] b, int[] exp) {
        List<Integer> e = new ArrayList<>();
        for (int x : exp) e.add(x);
        List<Integer> got = Support.toList(new Solution().mergeTwoLists(Support.build(a), Support.build(b)));
        if (!got.equals(e)) throw new AssertionError("expected " + e + " got " + got);
    }

    public static void main(String[] args) {
        checkExact(new int[]{1, 2, 4}, new int[]{1, 3, 4}, new int[]{1, 1, 2, 3, 4, 4});
        checkExact(new int[]{}, new int[]{}, new int[]{});
        checkExact(new int[]{}, new int[]{0}, new int[]{0});
        check(new int[]{5}, new int[]{});
        check(new int[]{-100, -100, 0}, new int[]{-100, 100});
        check(new int[]{1, 2, 3}, new int[]{4, 5, 6});
        check(new int[]{4, 5, 6}, new int[]{1, 2, 3});
        Random rnd = new Random(21);
        for (int t = 0; t < 200; t++) {
            int n = rnd.nextInt(51), m = rnd.nextInt(51);
            int[] a = new int[n], b = new int[m];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(201) - 100;
            for (int i = 0; i < m; i++) b[i] = rnd.nextInt(201) - 100;
            Arrays.sort(a); Arrays.sort(b);
            check(a, b);
        }
        System.out.println("all merge two sorted lists tests ok");
    }
}
