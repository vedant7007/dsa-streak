import java.util.*;

public class SolutionTest {
    static int[][] brute(int[][] a) {
        int n = a.length;
        int[][] b = new int[n][n];
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) b[j][n - 1 - i] = a[i][j];
        return b;
    }

    static int[][] copy(int[][] a) {
        int[][] b = new int[a.length][];
        for (int i = 0; i < a.length; i++) b[i] = a[i].clone();
        return b;
    }

    static void check(int[][] a, int[][] exp) {
        int[][] m = copy(a);
        new Solution().rotate(m);
        if (!Arrays.deepEquals(m, exp))
            throw new AssertionError(Arrays.deepToString(a) + " expected " + Arrays.deepToString(exp) + " got " + Arrays.deepToString(m));
    }

    public static void main(String[] args) {
        check(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, new int[][]{{7, 4, 1}, {8, 5, 2}, {9, 6, 3}});
        check(new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}},
              new int[][]{{15, 13, 2, 5}, {14, 3, 4, 1}, {12, 6, 8, 9}, {16, 7, 10, 11}});
        check(new int[][]{{1}}, new int[][]{{1}});
        check(new int[][]{{1, 2}, {3, 4}}, new int[][]{{3, 1}, {4, 2}});
        check(new int[][]{{-1000, 1000}, {0, -5}}, new int[][]{{0, -1000}, {-5, 1000}});
        check(new int[][]{{7, 7}, {7, 7}}, new int[][]{{7, 7}, {7, 7}});
        Random rnd = new Random(48);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(20);
            int[][] a = new int[n][n];
            for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) a[i][j] = rnd.nextInt(2001) - 1000;
            check(a, brute(a));
        }
        System.out.println("all rotate image tests ok");
    }
}
