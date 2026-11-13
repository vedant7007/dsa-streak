import java.util.*;

class Solution {
    List<List<String>> res = new ArrayList<>();
    char[][] board;
    boolean[] col, d1, d2;

    public List<List<String>> solveNQueens(int n) {
        board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');
        col = new boolean[n];
        d1 = new boolean[2 * n];
        d2 = new boolean[2 * n];
        solve(0, n);
        return res;
    }

    void solve(int r, int n) {
        if (r == n) {
            List<String> cur = new ArrayList<>();
            for (char[] row : board) cur.add(new String(row));
            res.add(cur);
            return;
        }
        for (int c = 0; c < n; c++) {
            if (col[c] || d1[r + c] || d2[r - c + n]) continue;
            col[c] = d1[r + c] = d2[r - c + n] = true;
            board[r][c] = 'Q';
            solve(r + 1, n);
            board[r][c] = '.';
            col[c] = d1[r + c] = d2[r - c + n] = false;
        }
    }
}
