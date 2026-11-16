import java.util.*;

public class SolutionTest {
    static char[][] grid(String... rows) {
        char[][] g = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) g[i] = rows[i].toCharArray();
        return g;
    }

    static boolean bruteGo(char[][] b, String w, int i, int j, int k, boolean[][] vis) {
        if (i < 0 || j < 0 || i >= b.length || j >= b[0].length || vis[i][j] || b[i][j] != w.charAt(k)) return false;
        if (k == w.length() - 1) return true;
        vis[i][j] = true;
        int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] x : d) if (bruteGo(b, w, i + x[0], j + x[1], k + 1, vis)) { vis[i][j] = false; return true; }
        vis[i][j] = false;
        return false;
    }

    static boolean brute(char[][] b, String w) {
        for (int i = 0; i < b.length; i++) for (int j = 0; j < b[0].length; j++)
            if (bruteGo(b, w, i, j, 0, new boolean[b.length][b[0].length])) return true;
        return false;
    }

    static void check(char[][] b, String w, boolean exp) {
        String s = Arrays.deepToString(b);
        boolean got = new Solution().exist(b, w);
        if (got != exp) throw new AssertionError("board=" + s + " word=" + w + " expected " + exp + " got " + got);
        if (!Arrays.deepToString(b).equals(s)) throw new AssertionError("board was not restored for word " + w);
    }

    public static void main(String[] args) {
        String[] ex = {"ABCE", "SFCS", "ADEE"};
        check(grid(ex), "ABCCED", true);
        check(grid(ex), "SEE", true);
        check(grid(ex), "ABCB", false);
        check(grid("a"), "a", true);
        check(grid("a"), "b", false);
        check(grid("aa"), "aaa", false);
        check(grid("aaaaaa", "aaaaaa", "aaaaaa", "aaaaaa", "aaaaaa", "aaaaab"), "aaaaaaaaaaaaaab", true);
        check(grid("ab", "cd"), "abdc", true);
        check(grid("ab", "cd"), "abcd", false);
        Random rnd = new Random(79);
        for (int it = 0; it < 200; it++) {
            int m = 1 + rnd.nextInt(4), n = 1 + rnd.nextInt(4);
            char[][] b = new char[m][n];
            for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) b[i][j] = (char) ('a' + rnd.nextInt(3));
            int len = 1 + rnd.nextInt(6);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) sb.append((char) ('a' + rnd.nextInt(3)));
            String w = sb.toString();
            check(b, w, brute(b, w));
        }
        System.out.println("all word search tests ok");
    }
}
