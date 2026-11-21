import java.util.*;

class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        dfs(nums, new ArrayList<>(), new boolean[nums.length]);
        return res;
    }

    void dfs(int[] nums, List<Integer> cur, boolean[] vis) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (vis[i]) continue;
            vis[i] = true;
            cur.add(nums[i]);
            dfs(nums, cur, vis);
            cur.remove(cur.size() - 1);
            vis[i] = false;
        }
    }
}
