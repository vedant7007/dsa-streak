import java.util.*;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int n = intervals.length, i = 0;
        while (i < n && intervals[i][1] < newInterval[0]) res.add(intervals[i++]);
        int l = newInterval[0], r = newInterval[1];
        while (i < n && intervals[i][0] <= r) {
            l = Math.min(l, intervals[i][0]);
            r = Math.max(r, intervals[i][1]);
            i++;
        }
        res.add(new int[]{l, r});
        while (i < n) res.add(intervals[i++]);
        return res.toArray(new int[0][]);
    }
}
