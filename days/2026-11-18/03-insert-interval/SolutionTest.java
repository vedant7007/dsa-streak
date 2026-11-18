import java.util.*;

public class SolutionTest {
    static String str(int[][] a) { return Arrays.deepToString(a); }

    static int[][] brute(int[][] a, int[] b) {
        List<int[]> all = new ArrayList<>();
        for (int[] x : a) all.add(x.clone());
        all.add(b.clone());
        all.sort((x, y) -> x[0] - y[0]);
        List<int[]> res = new ArrayList<>();
        for (int[] x : all) {
            if (!res.isEmpty() && res.get(res.size() - 1)[1] >= x[0])
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], x[1]);
            else res.add(x);
        }
        return res.toArray(new int[0][]);
    }

    static void check(int[][] a, int[] b, int[][] exp) {
        int[][] got = new Solution().insert(a, b);
        if (!str(got).equals(str(exp))) throw new AssertionError("intervals=" + str(a) + " new=" + Arrays.toString(b) + " expected " + str(exp) + " got " + str(got));
    }

    public static void main(String[] args) {
        check(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5}, new int[][]{{1, 5}, {6, 9}});
        check(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8}, new int[][]{{1, 2}, {3, 10}, {12, 16}});
        check(new int[][]{}, new int[]{5, 7}, new int[][]{{5, 7}});
        check(new int[][]{{1, 5}}, new int[]{2, 3}, new int[][]{{1, 5}});
        check(new int[][]{{1, 5}}, new int[]{6, 8}, new int[][]{{1, 5}, {6, 8}});
        check(new int[][]{{3, 5}}, new int[]{0, 1}, new int[][]{{0, 1}, {3, 5}});
        check(new int[][]{{1, 5}}, new int[]{5, 7}, new int[][]{{1, 7}});
        check(new int[][]{{1, 2}, {4, 5}}, new int[]{0, 100000}, new int[][]{{0, 100000}});
        Random rnd = new Random(57);
        for (int it = 0; it < 200; it++) {
            int n = rnd.nextInt(6);
            List<int[]> list = new ArrayList<>();
            int cur = rnd.nextInt(3);
            for (int i = 0; i < n; i++) {
                int s = cur, e = s + rnd.nextInt(4);
                list.add(new int[]{s, e});
                cur = e + 1 + rnd.nextInt(3);
            }
            int[][] a = list.toArray(new int[0][]);
            int s = rnd.nextInt(25), e = s + rnd.nextInt(8);
            int[] b = {s, e};
            int[][] exp = brute(a, b);
            check(a, b, exp);
        }
        System.out.println("all insert interval tests ok");
    }
}
