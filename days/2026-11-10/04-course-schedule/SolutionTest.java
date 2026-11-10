import java.util.*;

public class SolutionTest {
    static void check(boolean got, boolean expected, String label) {
        if (got != expected) throw new AssertionError(label + ": expected " + expected + " but got " + got);
    }

    // brute force: DFS cycle detection with colors
    static boolean brute(int n, int[][] pre) {
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());
        for (int[] p : pre) g.get(p[1]).add(p[0]);
        int[] color = new int[n];
        for (int i = 0; i < n; i++) if (color[i] == 0 && hasCycle(i, g, color)) return false;
        return true;
    }

    static boolean hasCycle(int u, List<List<Integer>> g, int[] color) {
        color[u] = 1;
        for (int v : g.get(u)) {
            if (color[v] == 1) return true;
            if (color[v] == 0 && hasCycle(v, g, color)) return true;
        }
        color[u] = 2;
        return false;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        check(s.canFinish(2, new int[][]{{1, 0}}), true, "Example 1");
        check(s.canFinish(2, new int[][]{{1, 0}, {0, 1}}), false, "Example 2");
        // edge cases
        check(s.canFinish(1, new int[][]{}), true, "single course, no prerequisites");
        check(s.canFinish(1, new int[][]{{0, 0}}), false, "self loop");
        check(s.canFinish(5, new int[][]{}), true, "no prerequisites");
        check(s.canFinish(4, new int[][]{{1, 0}, {2, 1}, {3, 2}, {1, 3}}), false, "cycle not touching course 0");
        check(s.canFinish(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}), true, "diamond");
        int big = 2000;
        int[][] chain = new int[big - 1][];
        for (int i = 1; i < big; i++) chain[i - 1] = new int[]{i, i - 1};
        check(s.canFinish(big, chain), true, "long chain of 2000");
        Random rnd = new Random(11);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(7);
            Set<String> seen = new HashSet<>();
            List<int[]> list = new ArrayList<>();
            int m = rnd.nextInt(n * 2 + 1);
            for (int i = 0; i < m; i++) {
                int a = rnd.nextInt(n), b = rnd.nextInt(n);
                if (seen.add(a + "," + b)) list.add(new int[]{a, b});
            }
            int[][] pre = list.toArray(new int[0][]);
            check(s.canFinish(n, pre), brute(n, pre), "random #" + t);
        }
        System.out.println("All tests passed");
    }
}
