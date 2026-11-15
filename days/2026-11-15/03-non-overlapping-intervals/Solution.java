import java.util.*;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int cnt = 0, end = Integer.MIN_VALUE;
        for (int[] in : intervals) {
            if (in[0] >= end) end = in[1];
            else cnt++;
        }
        return cnt;
    }
}
