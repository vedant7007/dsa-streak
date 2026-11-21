import java.util.*;

public class SolutionTest {
    static List<String> norm(List<List<Integer>> l) {
        List<String> out = new ArrayList<>();
        for (List<Integer> p : l) out.add(p.toString());
        Collections.sort(out);
        return out;
    }

    static List<List<Integer>> brute(int[] a) {
        List<List<Integer>> out = new ArrayList<>();
        int n = a.length;
        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        while (true) {
            List<Integer> cur = new ArrayList<>();
            for (int i : p) cur.add(a[i]);
            out.add(cur);
            int i = n - 2;
            while (i >= 0 && p[i] > p[i + 1]) i--;
            if (i < 0) break;
            int j = n - 1;
            while (p[j] < p[i]) j--;
            int t = p[i]; p[i] = p[j]; p[j] = t;
            for (int l = i + 1, r = n - 1; l < r; l++, r--) { t = p[l]; p[l] = p[r]; p[r] = t; }
        }
        return out;
    }

    static void check(int[] a, List<List<Integer>> exp) {
        List<String> got = norm(new Solution().permute(a));
        List<String> e = norm(exp);
        if (!got.equals(e)) throw new AssertionError(Arrays.toString(a) + " expected " + e + " got " + got);
        if (new HashSet<>(got).size() != got.size()) throw new AssertionError("duplicate permutation for " + Arrays.toString(a));
    }

    static List<List<Integer>> L(int[]... rows) {
        List<List<Integer>> out = new ArrayList<>();
        for (int[] r : rows) { List<Integer> x = new ArrayList<>(); for (int v : r) x.add(v); out.add(x); }
        return out;
    }

    public static void main(String[] args) {
        check(new int[]{1, 2, 3}, L(new int[]{1, 2, 3}, new int[]{1, 3, 2}, new int[]{2, 1, 3}, new int[]{2, 3, 1}, new int[]{3, 1, 2}, new int[]{3, 2, 1}));
        check(new int[]{0, 1}, L(new int[]{0, 1}, new int[]{1, 0}));
        check(new int[]{1}, L(new int[]{1}));
        check(new int[]{-10, 10}, L(new int[]{-10, 10}, new int[]{10, -10}));
        int[] six = {-3, 5, 0, 9, -10, 2};
        if (new Solution().permute(six).size() != 720) throw new AssertionError("6 elements should give 720");
        check(six, brute(six));
        Random rnd = new Random(46);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(5);
            TreeSet<Integer> set = new TreeSet<>();
            while (set.size() < n) set.add(rnd.nextInt(21) - 10);
            List<Integer> vals = new ArrayList<>(set);
            Collections.shuffle(vals, rnd);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = vals.get(i);
            check(a, brute(a));
        }
        System.out.println("all permutations tests ok");
    }
}
