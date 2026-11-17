import java.util.*;

public class SolutionTest {
    static Set<List<Integer>> norm(List<List<Integer>> g, String ctx) {
        Set<List<Integer>> s = new HashSet<>();
        for (List<Integer> t : g) {
            List<Integer> c = new ArrayList<>(t); Collections.sort(c);
            if (!s.add(c)) throw new AssertionError(ctx + " duplicate triplet " + c);
        }
        return s;
    }

    static List<List<Integer>> brute(int[] a) {
        Set<List<Integer>> s = new HashSet<>();
        for (int i = 0; i < a.length; i++)
            for (int j = i + 1; j < a.length; j++)
                for (int k = j + 1; k < a.length; k++)
                    if (a[i] + a[j] + a[k] == 0) {
                        List<Integer> t = new ArrayList<>(List.of(a[i], a[j], a[k])); Collections.sort(t); s.add(t);
                    }
        return new ArrayList<>(s);
    }

    static void check(int[] in, List<List<Integer>> exp) {
        String ctx = "nums=" + Arrays.toString(in);
        Set<List<Integer>> got = norm(new Solution().threeSum(in.clone()), ctx);
        Set<List<Integer>> e = norm(exp, ctx);
        if (!got.equals(e)) throw new AssertionError(ctx + " expected " + e + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{-1, 0, 1, 2, -1, -4}, List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)));
        check(new int[]{0, 1, 1}, List.of());
        check(new int[]{0, 0, 0}, List.of(List.of(0, 0, 0)));
        check(new int[]{0, 0, 0, 0, 0}, List.of(List.of(0, 0, 0)));
        check(new int[]{-2, 0, 1, 1, 2}, List.of(List.of(-2, 0, 2), List.of(-2, 1, 1)));
        check(new int[]{1, 2, 3}, List.of());
        check(new int[]{-100000, 50000, 50000}, List.of(List.of(-100000, 50000, 50000)));
        Random rnd = new Random(15);
        for (int t = 0; t < 200; t++) {
            int n = 3 + rnd.nextInt(15);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(11) - 5;
            check(a, brute(a));
        }
        int[] big = new int[3000];
        for (int i = 0; i < 3000; i++) big[i] = i - 1500;
        new Solution().threeSum(big);
        System.out.println("all 3sum tests ok");
    }
}
