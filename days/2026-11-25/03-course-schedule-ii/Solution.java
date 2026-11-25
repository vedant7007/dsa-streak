import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        int[] indeg = new int[numCourses];
        for (int[] p : prerequisites) {
            adj.get(p[1]).add(p[0]);
            indeg[p[0]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) if (indeg[i] == 0) q.add(i);
        int[] res = new int[numCourses];
        int k = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            res[k++] = cur;
            for (int nxt : adj.get(cur)) {
                if (--indeg[nxt] == 0) q.add(nxt);
            }
        }
        return k == numCourses ? res : new int[0];
    }
}
