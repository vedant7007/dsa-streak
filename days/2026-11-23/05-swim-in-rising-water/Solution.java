import java.util.*;

class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] vis = new boolean[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{grid[0][0], 0, 0});
        vis[0][0] = true;
        int ans = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            ans = Math.max(ans, cur[0]);
            if (cur[1] == n - 1 && cur[2] == n - 1) return ans;
            for (int[] d : dirs) {
                int r = cur[1] + d[0], c = cur[2] + d[1];
                if (r < 0 || c < 0 || r >= n || c >= n || vis[r][c]) continue;
                vis[r][c] = true;
                pq.add(new int[]{grid[r][c], r, c});
            }
        }
        return ans;
    }
}
