import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] cnt = new int[n + 1];
        long min = Long.MAX_VALUE;
        for (int[] it : items) {
            cnt[it[0]]++;
            min = Math.min(min, it[1]);
        }
        long[][] a = new long[n][2];
        for (int i = 0; i < n; i++) {
            int f = items[i][0];
            long d = -1;
            for (int m = f; m <= n; m += f) d += cnt[m];
            a[i][0] = items[i][1];
            a[i][1] = d;
        }
        Arrays.sort(a, (x, y) -> Long.compare(x[0], y[0]));
        long rem = budget, k = 0;
        for (long[] x : a) {
            long p = x[0], d = x[1];
            if (p > 2 * min) break;
            if (d == 0) continue;
            long t = Math.min(d, rem / p);
            k += t;
            rem -= t * p;
            if (t < d) break;
        }
        return (int) (2 * k + rem / min);
    }
}
