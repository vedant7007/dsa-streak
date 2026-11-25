import java.util.*;

public class SolutionTest {
    static List<String> norm(List<List<Integer>> l) {
        List<String> out = new ArrayList<>();
        for (List<Integer> s : l) { List<Integer> c = new ArrayList<>(s); Collections.sort(c); out.add(c.toString()); }
        Collections.sort(out);
        return out;
    }

    static List<List<Integer>> brute(int[] a) {
        List<List<Integer>> r = new ArrayList<>();
        for (int mask = 0; mask < (1 << a.length); mask++) {
            List<Integer> s = new ArrayList<>();
            for (int i = 0; i < a.length; i++) if ((mask >> i & 1) == 1) s.add(a[i]);
            r.add(s);
        }
        return r;
    }

    static void check(int[] a, List<List<Integer>> exp, String name) {
        List<List<Integer>> got = new Solution().subsets(a);
        if (!norm(got).equals(norm(exp))) throw new AssertionError(name + ": expected " + exp + " got " + got);
        if (new HashSet<>(norm(got)).size() != got.size()) throw new AssertionError(name + ": duplicate subsets " + got);
    }

    public static void main(String[] args) {
        check(new int[]{1, 2, 3}, List.of(List.of(), List.of(1), List.of(2), List.of(1, 2), List.of(3), List.of(1, 3), List.of(2, 3), List.of(1, 2, 3)), "example 1");
        check(new int[]{0}, List.of(List.of(), List.of(0)), "example 2");
        check(new int[]{-10}, List.of(List.of(), List.of(-10)), "single negative");
        check(new int[]{-1, 5}, List.of(List.of(), List.of(-1), List.of(5), List.of(-1, 5)), "two with negative");
        int[] big = new int[10];
        for (int i = 0; i < 10; i++) big[i] = i - 5;
        List<List<Integer>> g = new Solution().subsets(big);
        if (g.size() != 1024) throw new AssertionError("max size: expected 1024 got " + g.size());
        Random rnd = new Random(7);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(8);
            LinkedHashSet<Integer> s = new LinkedHashSet<>();
            while (s.size() < n) s.add(rnd.nextInt(21) - 10);
            int[] a = s.stream().mapToInt(x -> x).toArray();
            check(a, brute(a), "random " + Arrays.toString(a));
        }
        System.out.println("subsets: all tests passed");
    }
}
