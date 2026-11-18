import java.util.*;

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        boolean[][] pac = new boolean[m][n], atl = new boolean[m][n];
        Queue<int[]> pq = new LinkedList<>(), aq = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            pac[i][0] = true;
            pq.add(new int[]{i, 0});
            atl[i][n - 1] = true;
            aq.add(new int[]{i, n - 1});
        }
        for (int j = 0; j < n; j++) {
            if (!pac[0][j]) {
                pac[0][j] = true;
                pq.add(new int[]{0, j});
            }
            if (!atl[m - 1][j]) {
                atl[m - 1][j] = true;
                aq.add(new int[]{m - 1, j});
            }
        }
        bfs(heights, pq, pac);
        bfs(heights, aq, atl);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (pac[i][j] && atl[i][j]) res.add(Arrays.asList(i, j));
        return res;
    }

    void bfs(int[][] h, Queue<int[]> q, boolean[][] vis) {
        int m = h.length, n = h[0].length;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int[] d : dirs) {
                int x = cur[0] + d[0], y = cur[1] + d[1];
                if (x < 0 || y < 0 || x >= m || y >= n || vis[x][y] || h[x][y] < h[cur[0]][cur[1]]) continue;
                vis[x][y] = true;
                q.add(new int[]{x, y});
            }
        }
    }
}
