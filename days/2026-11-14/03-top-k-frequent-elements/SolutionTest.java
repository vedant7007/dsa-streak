import java.util.*;

public class SolutionTest {
    static void check(int[] nums, int k, int[] exp) {
        int[] got = new Solution().topKFrequent(nums.clone(), k);
        int[] e = exp.clone();
        Arrays.sort(got);
        Arrays.sort(e);
        if (!Arrays.equals(got, e)) throw new AssertionError("nums=" + Arrays.toString(nums) + " k=" + k + " expected " + Arrays.toString(e) + " got " + Arrays.toString(got));
    }

    public static void main(String[] args) {
        check(new int[]{1, 1, 1, 2, 2, 3}, 2, new int[]{1, 2});
        check(new int[]{1}, 1, new int[]{1});
        check(new int[]{1, 2, 1, 2, 1, 2, 3, 1, 3, 2}, 2, new int[]{1, 2});
        check(new int[]{-1, -1, -2}, 1, new int[]{-1});
        check(new int[]{4, 5, 6}, 3, new int[]{4, 5, 6});
        check(new int[]{7, 7, 7, 7}, 1, new int[]{7});
        int[] big = new int[100000];
        for (int i = 0; i < big.length; i++) big[i] = i % 3 == 0 ? 10000 : (i % 3 == 1 ? -10000 : i);
        check(big, 2, new int[]{10000, -10000});
        Random rnd = new Random(347);
        int done = 0;
        while (done < 200) {
            int n = 1 + rnd.nextInt(30);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(11) - 5;
            Map<Integer, Integer> c = new HashMap<>();
            for (int x : a) c.merge(x, 1, Integer::sum);
            List<Integer> keys = new ArrayList<>(c.keySet());
            keys.sort((x, y) -> c.get(y) - c.get(x));
            int k = 1 + rnd.nextInt(keys.size());
            if (k < keys.size() && c.get(keys.get(k - 1)).equals(c.get(keys.get(k)))) continue;
            int[] exp = new int[k];
            for (int i = 0; i < k; i++) exp[i] = keys.get(i);
            check(a, k, exp);
            done++;
        }
        System.out.println("all top k frequent tests ok");
    }
}
