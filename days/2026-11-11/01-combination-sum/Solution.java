import java.util.*;

class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, 0, target, new ArrayList<>());
        return res;
    }

    void dfs(int[] a, int i, int rem, List<Integer> cur) {
        if (rem == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int j = i; j < a.length; j++) {
            if (a[j] > rem) break;
            cur.add(a[j]);
            dfs(a, j, rem - a[j], cur);
            cur.remove(cur.size() - 1);
        }
    }
}
