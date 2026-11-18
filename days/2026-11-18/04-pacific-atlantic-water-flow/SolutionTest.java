import java.util.*;

public class SolutionTest {
    static List<String> norm(List<List<Integer>> a) {
        List<String> s = new ArrayList<>();
        for (List<Integer> p : a) s.add(p.get(0) + "," + p.get(1));
        Collections.sort(s);
        return s;
    }

    static boolean reach(int[][] h, int si, int sj, boolean pacific) {
        int m = h.length, n = h[0].length;
        boolean[][] vis = new boolean[m][n];
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{si, sj});
        vis[si][sj] = true;
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
        while (!q.isEmpty()) {
            int[] c = q.poll();
            for (int d = 0; d < 4; d++) {
                int x = c[0] + dx[d], y = c[1] + dy[d];
                if (pacific && (x < 0 || y < 0)) return true;
                if (!pacific && (x >= m || y >= n)) return true;
                if (x < 0 || y < 0 || x >= m || y >= n || vis[x][y] || h[x][y] > h[c[0]][c[1]]) continue;
                vis[x][y] = true;
                q.add(new int[]{x, y});
            }
        }
        return false;
    }

    static List<List<Integer>> brute(int[][] h) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < h.length; i++)
            for (int j = 0; j < h[0].length; j++)
                if (reach(h, i, j, true) && reach(h, i, j, false)) res.add(Arrays.asList(i, j));
        return res;
    }

    static void check(int[][] h, List<List<Integer>> exp) {
        List<String> got = norm(new Solution().pacificAtlantic(h)), e = norm(exp);
        if (!got.equals(e)) throw new AssertionError("heights=" + Arrays.deepToString(h) + " expected " + e + " got " + got);
    }

    static List<List<Integer>> L(int[][] a) {
        List<List<Integer>> r = new ArrayList<>();
        for (int[] x : a) r.add(Arrays.asList(x[0], x[1]));
        return r;
    }

    public static void main(String[] args) {
        check(new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}},
              L(new int[][]{{0, 4}, {1, 3}, {1, 4}, {2, 2}, {3, 0}, {3, 1}, {4, 0}}));
        check(new int[][]{{1}}, L(new int[][]{{0, 0}}));
        check(new int[][]{{0}}, L(new int[][]{{0, 0}}));
        check(new int[][]{{2, 2}, {2, 2}}, L(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}}));
        check(new int[][]{{1, 2, 3}}, L(new int[][]{{0, 0}, {0, 1}, {0, 2}}));
        check(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}, L(new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 2}, {2, 0}, {2, 1}, {2, 2}}));
        int[][] big = new int[200][200];
        for (int i = 0; i < 200; i++) for (int j = 0; j < 200; j++) big[i][j] = 100000;
        if (new Solution().pacificAtlantic(big).size() != 40000) throw new AssertionError("flat 200x200 should return all cells");
        Random rnd = new Random(417);
        for (int it = 0; it < 200; it++) {
            int m = 1 + rnd.nextInt(6), n = 1 + rnd.nextInt(6);
            int[][] h = new int[m][n];
            for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) h[i][j] = rnd.nextInt(5);
            check(h, brute(h));
        }
        System.out.println("all pacific atlantic tests ok");
    }
}
