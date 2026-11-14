import java.util.*;

public class SolutionTest {
    static char[][] g(String... rows) {
        char[][] res = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) res[i] = rows[i].toCharArray();
        return res;
    }

    static int brute(char[][] a) {
        int m = a.length, n = a[0].length;
        int[] p = new int[m * n];
        for (int i = 0; i < p.length; i++) p[i] = i;
        int land = 0, unions = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (a[i][j] != '1') continue;
                land++;
                if (i + 1 < m && a[i + 1][j] == '1' && union(p, i * n + j, (i + 1) * n + j)) unions++;
                if (j + 1 < n && a[i][j + 1] == '1' && union(p, i * n + j, i * n + j + 1)) unions++;
            }
        return land - unions;
    }

    static int find(int[] p, int x) { return p[x] == x ? x : (p[x] = find(p, p[x])); }

    static boolean union(int[] p, int a, int b) {
        int x = find(p, a), y = find(p, b);
        if (x == y) return false;
        p[x] = y;
        return true;
    }

    static void check(char[][] grid, int exp) {
        String s = Arrays.deepToString(grid);
        int got = new Solution().numIslands(grid);
        if (got != exp) throw new AssertionError("grid=" + s + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(g("11110", "11010", "11000", "00000"), 1);
        check(g("11000", "11000", "00100", "00011"), 3);
        check(g("0"), 0);
        check(g("1"), 1);
        check(g("101", "010", "101"), 5);
        check(g("111", "101", "111"), 1);
        char[][] big = new char[300][300];
        for (char[] row : big) Arrays.fill(row, '1');
        check(big, 1);
        char[][] chk = new char[300][300];
        for (int i = 0; i < 300; i++) for (int j = 0; j < 300; j++) chk[i][j] = (i + j) % 2 == 0 ? '1' : '0';
        check(chk, 45000);
        Random rnd = new Random(200);
        for (int t = 0; t < 200; t++) {
            int m = 1 + rnd.nextInt(8), n = 1 + rnd.nextInt(8);
            char[][] a = new char[m][n];
            for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) a[i][j] = rnd.nextInt(2) == 0 ? '0' : '1';
            int exp = brute(a);
            check(a, exp);
        }
        System.out.println("all number of islands tests ok");
    }
}
