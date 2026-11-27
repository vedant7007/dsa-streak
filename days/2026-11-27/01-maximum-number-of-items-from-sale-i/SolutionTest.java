import java.util.*;

public class SolutionTest {
    static int[][] items;
    static int best;

    static void rec(int i, int left, int[] bought) {
        if (i == items.length) {
            int total = 0;
            for (int x = 0; x < items.length; x++) {
                total += bought[x];
                if (bought[x] > 0)
                    for (int y = 0; y < items.length; y++)
                        if (y != x && items[y][0] % items[x][0] == 0) total++;
            }
            best = Math.max(best, total);
            return;
        }
        for (int c = 0; c * items[i][1] <= left; c++) {
            bought[i] = c;
            rec(i + 1, left - c * items[i][1], bought);
        }
        bought[i] = 0;
    }

    static int brute(int[][] it, int budget) {
        items = it;
        best = 0;
        rec(0, budget, new int[it.length]);
        return best;
    }

    static void check(int[][] it, int budget, int exp) {
        int got = new Solution().maximumSaleItems(it, budget);
        if (got != exp) throw new AssertionError("items=" + Arrays.deepToString(it) + " budget=" + budget + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[][]{{6, 2}, {2, 6}, {3, 4}}, 9, 4);
        check(new int[][]{{2, 4}, {3, 2}, {4, 1}, {6, 4}, {12, 4}}, 8, 10);
        check(new int[][]{{1, 1}}, 1, 1);
        check(new int[][]{{5, 10}}, 3, 0);
        check(new int[][]{{2, 3}, {2, 3}, {2, 3}}, 3, 3);
        check(new int[][]{{1, 1500}, {7, 1}}, 1500, 1500);
        int[][] big = new int[1000][];
        for (int i = 0; i < 1000; i++) big[i] = new int[]{1, 1500};
        check(big, 1500, 1000);
        Random r = new Random(7);
        for (int t = 0; t < 200; t++) {
            int n = 1 + r.nextInt(4);
            int[][] it = new int[n][];
            for (int i = 0; i < n; i++) it[i] = new int[]{1 + r.nextInt(8), 1 + r.nextInt(5)};
            int b = 1 + r.nextInt(12);
            check(it, b, brute(it, b));
        }
        System.out.println("all tests passed");
    }
}
