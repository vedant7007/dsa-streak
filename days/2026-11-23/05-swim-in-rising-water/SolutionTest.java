import java.util.*;

public class SolutionTest {
    static boolean reach(int[][] g, int t) {
        int n = g.length;
        if (g[0][0] > t) return false;
        boolean[][] vis = new boolean[n][n];
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});
        vis[0][0] = true;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!q.isEmpty()) {
            int[] c = q.poll();
            if (c[0] == n - 1 && c[1] == n - 1) return true;
            for (int[] d : dirs) {
                int r = c[0] + d[0], cc = c[1] + d[1];
                if (r >= 0 && cc >= 0 && r < n && cc < n && !vis[r][cc] && g[r][cc] <= t) { vis[r][cc] = true; q.add(new int[]{r, cc}); }
            }
        }
        return false;
    }

    static int brute(int[][] g) {
        int t = 0;
        while (!reach(g, t)) t++;
        return t;
    }

    static int[][] perm(int n, Random rnd) {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < n * n; i++) l.add(i);
        Collections.shuffle(l, rnd);
        int[][] g = new int[n][n];
        for (int i = 0; i < n * n; i++) g[i / n][i % n] = l.get(i);
        return g;
    }

    static void check(int[][] g, int exp) {
        int got = new Solution().swimInWater(g);
        if (got != exp) throw new AssertionError(Arrays.deepToString(g) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[][]{{0, 2}, {1, 3}}, 3);
        check(new int[][]{{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}}, 16);
        check(new int[][]{{0}}, 0);
        check(new int[][]{{3, 2}, {0, 1}}, 3);
        check(new int[][]{{0, 3}, {2, 1}}, 2);
        Random rnd = new Random(778);
        int[][] big = perm(50, rnd);
        check(big, brute(big));
        for (int it = 0; it < 200; it++) {
            int[][] g = perm(1 + rnd.nextInt(6), rnd);
            check(g, brute(g));
        }
        System.out.println("all swim in rising water tests ok");
    }
}
