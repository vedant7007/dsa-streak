import java.util.*;

public class SolutionTest {
    static int brute(int[][] g) {
        int n = g.length, c = 0;
        boolean[] vis = new boolean[n];
        for (int s = 0; s < n; s++) {
            if (vis[s]) continue;
            c++;
            Deque<Integer> st = new ArrayDeque<>();
            st.push(s);
            vis[s] = true;
            while (!st.isEmpty()) {
                int u = st.pop();
                for (int v = 0; v < n; v++) if (g[u][v] == 1 && !vis[v]) { vis[v] = true; st.push(v); }
            }
        }
        return c;
    }

    static void check(int[][] g, int exp) {
        int got = new Solution().findCircleNum(g);
        if (got != exp) throw new AssertionError(Arrays.deepToString(g) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}, 2);
        check(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}, 3);
        check(new int[][]{{1}}, 1);
        check(new int[][]{{1, 1}, {1, 1}}, 1);
        check(new int[][]{{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}}, 1);
        int n = 200;
        int[][] chain = new int[n][n];
        for (int i = 0; i < n; i++) { chain[i][i] = 1; if (i + 1 < n) { chain[i][i + 1] = 1; chain[i + 1][i] = 1; } }
        check(chain, 1);
        int[][] id = new int[n][n];
        for (int i = 0; i < n; i++) id[i][i] = 1;
        check(id, 200);
        Random rnd = new Random(547);
        for (int it = 0; it < 200; it++) {
            int m = 1 + rnd.nextInt(10);
            int[][] g = new int[m][m];
            for (int i = 0; i < m; i++) {
                g[i][i] = 1;
                for (int j = i + 1; j < m; j++) if (rnd.nextInt(5) == 0) { g[i][j] = 1; g[j][i] = 1; }
            }
            check(g, brute(g));
        }
        System.out.println("all provinces tests ok");
    }
}
