import java.util.*;

class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) q.add(new int[]{i, j});
                else if (grid[i][j] == 1) fresh++;
            }
        }
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int ans = 0;
        while (!q.isEmpty() && fresh > 0) {
            int sz = q.size();
            for (int k = 0; k < sz; k++) {
                int[] cur = q.poll();
                for (int[] d : dirs) {
                    int x = cur[0] + d[0], y = cur[1] + d[1];
                    if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] != 1) continue;
                    grid[x][y] = 2;
                    fresh--;
                    q.add(new int[]{x, y});
                }
            }
            ans++;
        }
        return fresh == 0 ? ans : -1;
    }
}
