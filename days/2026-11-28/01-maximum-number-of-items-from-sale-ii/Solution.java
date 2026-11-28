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
        long[] mul = new long[n + 1];
        for (int f = 1; f <= n; f++) {
            if (cnt[f] == 0) continue;
            for (int m = f; m <= n; m += f) mul[f] += cnt[m];
        }
        long[][] a = new long[n][2];
        for (int i = 0; i < n; i++) {
            a[i][0] = items[i][1];
            a[i][1] = mul[items[i][0]] - 1;
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
