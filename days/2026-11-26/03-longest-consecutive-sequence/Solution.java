import java.util.*;

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        int ans = 0;
        for (int x : set) {
            if (set.contains(x - 1)) continue;
            int cur = x, len = 1;
            while (set.contains(cur + 1)) {
                cur++;
                len++;
            }
            ans = Math.max(ans, len);
        }
        return ans;
    }
}
