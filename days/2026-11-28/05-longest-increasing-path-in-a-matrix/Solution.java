class Solution {
    int[][] dp;
    int m, n;
    int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        dp = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                ans = Math.max(ans, dfs(matrix, i, j));
        return ans;
    }

    int dfs(int[][] a, int i, int j) {
        if (dp[i][j] != 0) return dp[i][j];
        int best = 1;
        for (int d = 0; d < 4; d++) {
            int x = i + dx[d], y = j + dy[d];
            if (x >= 0 && y >= 0 && x < m && y < n && a[x][y] > a[i][j])
                best = Math.max(best, 1 + dfs(a, x, y));
        }
        return dp[i][j] = best;
    }
}
