import java.util.*;

class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        int n = intervals.length, m = queries.length;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> queries[a] - queries[b]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[] ans = new int[m];
        int i = 0;
        for (int k : idx) {
            int q = queries[k];
            while (i < n && intervals[i][0] <= q) {
                pq.add(new int[]{intervals[i][1] - intervals[i][0] + 1, intervals[i][1]});
                i++;
            }
            while (!pq.isEmpty() && pq.peek()[1] < q) pq.poll();
            ans[k] = pq.isEmpty() ? -1 : pq.peek()[0];
        }
        return ans;
    }
}
