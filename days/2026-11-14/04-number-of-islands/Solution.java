import java.util.*;

class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length, cnt = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != '1') continue;
                cnt++;
                grid[i][j] = '0';
                q.add(new int[]{i, j});
                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    for (int[] d : dirs) {
                        int x = cur[0] + d[0], y = cur[1] + d[1];
                        if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] != '1') continue;
                        grid[x][y] = '0';
                        q.add(new int[]{x, y});
                    }
                }
            }
        }
        return cnt;
    }
}
