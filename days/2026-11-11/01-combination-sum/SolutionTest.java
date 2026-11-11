import java.util.*;

public class SolutionTest {
    static List<List<Integer>> norm(List<List<Integer>> x) {
        List<List<Integer>> out = new ArrayList<>();
        for (List<Integer> c : x) { List<Integer> s = new ArrayList<>(c); Collections.sort(s); out.add(s); }
        out.sort((a, b) -> a.toString().compareTo(b.toString()));
        return out;
    }

    static void check(int[] cand, int target, List<List<Integer>> expected, String label) {
        List<List<Integer>> got = norm(new Solution().combinationSum(cand.clone(), target));
        List<List<Integer>> exp = norm(expected);
        if (!got.equals(exp)) throw new AssertionError(label + ": expected " + exp + " but got " + got);
    }

    static void brute(int[] a, int i, int rem, List<Integer> cur, List<List<Integer>> out) {
        if (rem == 0) { out.add(new ArrayList<>(cur)); return; }
        if (i == a.length || rem < 0) return;
        for (int k = 0; k * a[i] <= rem; k++) {
            for (int t = 0; t < k; t++) cur.add(a[i]);
            brute(a, i + 1, rem - k * a[i], cur, out);
            for (int t = 0; t < k; t++) cur.remove(cur.size() - 1);
        }
    }

    public static void main(String[] args) {
        check(new int[]{2, 3, 6, 7}, 7, List.of(List.of(2, 2, 3), List.of(7)), "Example 1");
        check(new int[]{2, 3, 5}, 8, List.of(List.of(2, 2, 2, 2), List.of(2, 3, 3), List.of(3, 5)), "Example 2");
        check(new int[]{2}, 1, List.of(), "Example 3");
        check(new int[]{7, 3, 2}, 7, List.of(List.of(2, 2, 3), List.of(7)), "unsorted input");
        check(new int[]{5}, 5, List.of(List.of(5)), "single candidate exact");
        check(new int[]{40}, 39, List.of(), "candidate bigger than target");
        check(new int[]{2}, 40, List.of(Collections.nCopies(20, 2)), "max depth 20 twos");
        Random rnd = new Random(11);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(5);
            Set<Integer> s = new HashSet<>();
            while (s.size() < n) s.add(2 + rnd.nextInt(14));
            int[] a = new int[n];
            int idx = 0;
            for (int v : s) a[idx++] = v;
            int target = 1 + rnd.nextInt(30);
            List<List<Integer>> exp = new ArrayList<>();
            brute(a, 0, target, new ArrayList<>(), exp);
            check(a, target, exp, "random #" + t);
        }
        System.out.println("All tests passed");
    }
}
