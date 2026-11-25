import java.util.*;

public class SolutionTest {
    static boolean valid(int n, int[][] pre, int[] ord) {
        if (ord.length != n) return false;
        int[] pos = new int[n];
        Arrays.fill(pos, -1);
        for (int i = 0; i < n; i++) {
            if (ord[i] < 0 || ord[i] >= n || pos[ord[i]] != -1) return false;
            pos[ord[i]] = i;
        }
        for (int[] p : pre) if (pos[p[1]] > pos[p[0]]) return false;
        return true;
    }

    static boolean possible(int n, int[][] pre) {
        // brute force: repeatedly remove a course with all prereqs taken
        boolean[] done = new boolean[n];
        for (int round = 0; round < n; round++) {
            boolean moved = false;
            for (int c = 0; c < n && !moved; c++) {
                if (done[c]) continue;
                boolean ok = true;
                for (int[] p : pre) if (p[0] == c && !done[p[1]]) ok = false;
                if (ok) { done[c] = true; moved = true; }
            }
            if (!moved) return false;
        }
        return true;
    }

    static void check(int n, int[][] pre, boolean canFinish, String name) {
        int[] got = new Solution().findOrder(n, pre);
        if (canFinish && !valid(n, pre, got)) throw new AssertionError(name + ": invalid order " + Arrays.toString(got));
        if (!canFinish && got.length != 0) throw new AssertionError(name + ": expected [] got " + Arrays.toString(got));
    }

    public static void main(String[] args) {
        check(2, new int[][]{{1, 0}}, true, "example 1");
        int[] e1 = new Solution().findOrder(2, new int[][]{{1, 0}});
        if (!Arrays.equals(e1, new int[]{0, 1})) throw new AssertionError("example 1 exact: got " + Arrays.toString(e1));
        check(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}, true, "example 2");
        check(1, new int[][]{}, true, "example 3");
        int[] e3 = new Solution().findOrder(1, new int[][]{});
        if (!Arrays.equals(e3, new int[]{0})) throw new AssertionError("example 3 exact: got " + Arrays.toString(e3));
        check(2, new int[][]{{1, 0}, {0, 1}}, false, "two-cycle");
        check(3, new int[][]{{0, 1}, {1, 2}, {2, 0}}, false, "three-cycle");
        check(5, new int[][]{}, true, "no edges");
        check(4, new int[][]{{1, 0}, {2, 1}, {3, 2}}, true, "chain");
        Random r = new Random(3);
        for (int t = 0; t < 200; t++) {
            int n = 1 + r.nextInt(6);
            Set<String> seen = new HashSet<>();
            List<int[]> es = new ArrayList<>();
            int m = r.nextInt(n * 2);
            for (int i = 0; i < m; i++) {
                int a = r.nextInt(n), b = r.nextInt(n);
                if (a == b || !seen.add(a + "," + b)) continue;
                es.add(new int[]{a, b});
            }
            int[][] pre = es.toArray(new int[0][]);
            check(n, pre, possible(n, pre), "random n=" + n + " " + Arrays.deepToString(pre));
        }
        System.out.println("course-schedule-ii: all tests passed");
    }
}
