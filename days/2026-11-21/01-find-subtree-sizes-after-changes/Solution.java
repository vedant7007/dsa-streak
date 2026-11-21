import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) adj[parent[i]].add(i);
        int[] np = parent.clone();
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int[] prev = new int[n], order = new int[n], idx = new int[n], st = new int[n];
        int top = 0, k = 0;
        last[s.charAt(0) - 'a'] = 0;
        prev[0] = -1;
        order[k++] = 0;
        st[top++] = 0;
        while (top > 0) {
            int x = st[top - 1];
            if (idx[x] < adj[x].size()) {
                int y = adj[x].get(idx[x]++);
                int c = s.charAt(y) - 'a';
                if (last[c] != -1) np[y] = last[c];
                prev[y] = last[c];
                last[c] = y;
                order[k++] = y;
                st[top++] = y;
            } else {
                last[s.charAt(x) - 'a'] = prev[x];
                top--;
            }
        }
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        for (int i = n - 1; i > 0; i--) ans[np[order[i]]] += ans[order[i]];
        return ans;
    }
}
