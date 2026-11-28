import java.util.*;

public class SolutionTest {
    static void check(int[][] adj) {
        List<Node> orig = new ArrayList<>();
        Node start = Support.build(adj, orig);
        Node copy = new Solution().cloneGraph(start);
        List<List<Integer>> exp = new ArrayList<>();
        for (int[] a : adj) { List<Integer> l = new ArrayList<>(); for (int x : a) l.add(x); exp.add(l); }
        Set<Node> seen = new HashSet<>();
        List<List<Integer>> got = Support.toAdj(copy, seen);
        if (!got.equals(exp)) throw new AssertionError("adj=" + Arrays.deepToString(adj) + " got " + got);
        Set<Node> o = Collections.newSetFromMap(new IdentityHashMap<>());
        o.addAll(orig);
        for (Node c : seen) if (o.contains(c)) throw new AssertionError("clone shares node " + c.val + " with original");
        if (start != null && copy == start) throw new AssertionError("returned the original node");
    }

    public static void main(String[] args) {
        check(new int[][]{{2, 4}, {1, 3}, {2, 4}, {1, 3}});
        check(new int[][]{{}});
        check(new int[][]{});
        check(new int[][]{{2}, {1}});
        check(new int[][]{{2, 3}, {1, 3}, {1, 2}});
        int n = 100;
        int[][] full = new int[n][];
        for (int i = 0; i < n; i++) {
            full[i] = new int[n - 1];
            int k = 0;
            for (int j = 1; j <= n; j++) if (j != i + 1) full[i][k++] = j;
        }
        check(full);
        Random r = new Random(5);
        for (int t = 0; t < 200; t++) {
            int m = 1 + r.nextInt(8);
            List<Set<Integer>> g = new ArrayList<>();
            for (int i = 0; i < m; i++) g.add(new TreeSet<>());
            for (int i = 1; i < m; i++) { int p = r.nextInt(i); g.get(i).add(p + 1); g.get(p).add(i + 1); }
            for (int e = 0; e < m; e++) { int a = r.nextInt(m), b = r.nextInt(m); if (a != b) { g.get(a).add(b + 1); g.get(b).add(a + 1); } }
            int[][] adj = new int[m][];
            for (int i = 0; i < m; i++) adj[i] = g.get(i).stream().mapToInt(Integer::intValue).toArray();
            check(adj);
        }
        System.out.println("all tests passed");
    }
}
