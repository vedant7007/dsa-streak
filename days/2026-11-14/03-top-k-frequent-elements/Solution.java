import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        List<Integer>[] b = new List[nums.length + 1];
        for (int x : cnt.keySet()) {
            int f = cnt.get(x);
            if (b[f] == null) b[f] = new ArrayList<>();
            b[f].add(x);
        }
        int[] res = new int[k];
        int idx = 0;
        for (int f = nums.length; f > 0 && idx < k; f--) {
            if (b[f] == null) continue;
            for (int x : b[f]) {
                if (idx == k) break;
                res[idx++] = x;
            }
        }
        return res;
    }
}
