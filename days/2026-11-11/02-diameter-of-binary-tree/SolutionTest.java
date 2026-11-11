import java.util.*;

public class SolutionTest {
    static void check(Integer[] arr, int expected, String label) {
        int got = new Solution().diameterOfBinaryTree(Support.build(arr));
        if (got != expected) throw new AssertionError(label + ": expected " + expected + " but got " + got);
    }

    static int bruteFrom(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        Map<TreeNode, TreeNode> par = new HashMap<>();
        Deque<TreeNode> st = new ArrayDeque<>();
        st.push(root);
        while (!st.isEmpty()) {
            TreeNode c = st.pop();
            nodes.add(c);
            if (c.left != null) { par.put(c.left, c); st.push(c.left); }
            if (c.right != null) { par.put(c.right, c); st.push(c.right); }
        }
        int best = 0;
        for (TreeNode src : nodes) {
            Map<TreeNode, Integer> dist = new HashMap<>();
            Deque<TreeNode> q = new ArrayDeque<>();
            dist.put(src, 0);
            q.add(src);
            while (!q.isEmpty()) {
                TreeNode c = q.poll();
                best = Math.max(best, dist.get(c));
                for (TreeNode nb : new TreeNode[]{c.left, c.right, par.get(c)}) {
                    if (nb != null && !dist.containsKey(nb)) { dist.put(nb, dist.get(c) + 1); q.add(nb); }
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        check(new Integer[]{1, 2, 3, 4, 5}, 3, "Example 1");
        check(new Integer[]{1, 2}, 1, "Example 2");
        check(new Integer[]{1}, 0, "single node");
        check(new Integer[]{-1, -2, null, -3, null, -4}, 3, "left chain with negatives");
        check(new Integer[]{1, 2, null, 3, 4, 5, null, null, 6, 7, null, null, 8}, 6, "longest path not through root");
        TreeNode root = new TreeNode(0), cur = root;
        for (int i = 1; i < 10000; i++) { cur.right = new TreeNode(i); cur = cur.right; }
        int[] big = new int[1];
        Thread th = new Thread(null, () -> big[0] = new Solution().diameterOfBinaryTree(root), "deep", 1 << 26);
        th.start();
        try { th.join(); } catch (InterruptedException e) { throw new RuntimeException(e); }
        if (big[0] != 9999) throw new AssertionError("10^4 chain: expected 9999 but got " + big[0]);
        Random rnd = new Random(3);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(15);
            List<TreeNode> nodes = new ArrayList<>();
            TreeNode r = new TreeNode(rnd.nextInt(201) - 100);
            nodes.add(r);
            while (nodes.size() < n) {
                TreeNode p = nodes.get(rnd.nextInt(nodes.size()));
                TreeNode c = new TreeNode(rnd.nextInt(201) - 100);
                if (rnd.nextBoolean()) { if (p.left == null) { p.left = c; nodes.add(c); } }
                else if (p.right == null) { p.right = c; nodes.add(c); }
            }
            int exp = bruteFrom(r), got = new Solution().diameterOfBinaryTree(r);
            if (got != exp) throw new AssertionError("random #" + t + ": expected " + exp + " but got " + got);
        }
        System.out.println("All tests passed");
    }
}
