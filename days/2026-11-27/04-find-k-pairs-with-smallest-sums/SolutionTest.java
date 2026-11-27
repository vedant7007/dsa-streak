import java.util.*;

public class SolutionTest {
    // any order of pairs is accepted, and ties may pick different pairs with the same sum,
    // so we compare sorted sums against brute force and check each pair is really available
    static void verify(int[] a, int[] b, int k, List<List<Integer>> got) {
        String tag = "a=" + Arrays.toString(a) + " b=" + Arrays.toString(b) + " k=" + k + " got=" + got;
        if (got.size() != k) throw new AssertionError("wrong size: " + tag);
        List<Long> all = new ArrayList<>();
        Map<String, Integer> avail = new HashMap<>();
        for (int x : a) for (int y : b) {
            all.add((long) x + y);
            avail.merge(x + "," + y, 1, Integer::sum);
        }
        Collections.sort(all);
        List<Long> sums = new ArrayList<>();
        for (List<Integer> pr : got) {
            sums.add((long) pr.get(0) + pr.get(1));
            String key = pr.get(0) + "," + pr.get(1);
            int c = avail.getOrDefault(key, 0);
            if (c == 0) throw new AssertionError("pair not available: " + key + " " + tag);
            avail.put(key, c - 1);
        }
        Collections.sort(sums);
        if (!sums.equals(all.subList(0, k))) throw new AssertionError("sums mismatch: " + tag);
    }

    static void check(int[] a, int[] b, int k) {
        verify(a, b, k, new Solution().kSmallestPairs(a, b, k));
    }

    public static void main(String[] args) {
        List<List<Integer>> e1 = new Solution().kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3);
        if (!e1.equals(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(1, 4), Arrays.asList(1, 6)))) throw new AssertionError("example 1 got " + e1);
        List<List<Integer>> e2 = new Solution().kSmallestPairs(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 2);
        if (!e2.equals(Arrays.asList(Arrays.asList(1, 1), Arrays.asList(1, 1)))) throw new AssertionError("example 2 got " + e2);
        check(new int[]{5}, new int[]{-5}, 1);
        check(new int[]{-1000000000, 1000000000}, new int[]{-1000000000, 1000000000}, 4);
        check(new int[]{1, 2}, new int[]{3}, 2);
        check(new int[]{2, 2, 2}, new int[]{2, 2}, 6);
        int[] big1 = new int[100000], big2 = new int[100000];
        for (int i = 0; i < 100000; i++) { big1[i] = i; big2[i] = i; }
        if (new Solution().kSmallestPairs(big1, big2, 10000).size() != 10000) throw new AssertionError("big size");
        Random r = new Random(5);
        for (int t = 0; t < 200; t++) {
            int n = 1 + r.nextInt(6), m = 1 + r.nextInt(6);
            int[] a = new int[n], b = new int[m];
            for (int i = 0; i < n; i++) a[i] = r.nextInt(11) - 5;
            for (int i = 0; i < m; i++) b[i] = r.nextInt(11) - 5;
            Arrays.sort(a);
            Arrays.sort(b);
            check(a, b, 1 + r.nextInt(n * m));
        }
        System.out.println("all tests passed");
    }
}
