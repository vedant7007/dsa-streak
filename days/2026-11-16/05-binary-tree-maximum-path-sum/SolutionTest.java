import java.util.*;

public class SolutionTest {
    static void collect(TreeNode t, TreeNode p, List<TreeNode> nodes, Map<TreeNode, TreeNode> par) {
        if (t == null) return;
        nodes.add(t);
        par.put(t, p);
        collect(t.left, t, nodes, par);
        collect(t.right, t, nodes, par);
    }

    static int brute(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        Map<TreeNode, TreeNode> par = new HashMap<>();
        collect(root, null, nodes, par);
        int best = Integer.MIN_VALUE;
        for (TreeNode a : nodes) for (TreeNode b : nodes) {
            Set<TreeNode> anc = new HashSet<>();
            for (TreeNode x = a; x != null; x = par.get(x)) anc.add(x);
            TreeNode lca = b;
            while (!anc.contains(lca)) lca = par.get(lca);
            int s = 0;
            for (TreeNode x = a; x != lca; x = par.get(x)) s += x.val;
            for (TreeNode x = b; x != lca; x = par.get(x)) s += x.val;
            s += lca.val;
            best = Math.max(best, s);
        }
        return best;
    }

    static void check(Integer[] a, int exp) {
        int got = new Solution().maxPathSum(Support.build(a));
        if (got != exp) throw new AssertionError("root=" + Arrays.toString(a) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new Integer[]{1, 2, 3}, 6);
        check(new Integer[]{-10, 9, 20, null, null, 15, 7}, 42);
        check(new Integer[]{-3}, -3);
        check(new Integer[]{-2, -1}, -1);
        check(new Integer[]{2, -1}, 2);
        check(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1}, 48);
        check(new Integer[]{1000, 1000, 1000}, 3000);
        Random rnd = new Random(124);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(12);
            Integer[] a = new Integer[n];
            a[0] = rnd.nextInt(21) - 10;
            for (int i = 1; i < n; i++) a[i] = rnd.nextInt(4) == 0 ? null : rnd.nextInt(21) - 10;
            TreeNode t = Support.build(a);
            int exp = brute(t);
            int got = new Solution().maxPathSum(t);
            if (got != exp) throw new AssertionError("root=" + Arrays.toString(a) + " expected " + exp + " got " + got);
        }
        System.out.println("all binary tree maximum path sum tests ok");
    }
}
