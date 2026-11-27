import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
}

class Support {
    // LeetCode style level order with nulls, trailing nulls trimmed
    static List<Integer> serialize(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t = q.poll();
            if (t == null) { out.add(null); continue; }
            out.add(t.val);
            q.add(t.left);
            q.add(t.right);
        }
        while (!out.isEmpty() && out.get(out.size() - 1) == null) out.remove(out.size() - 1);
        return out;
    }

    static void pre(TreeNode t, List<Integer> out) {
        if (t == null) return;
        out.add(t.val); pre(t.left, out); pre(t.right, out);
    }

    static void in(TreeNode t, List<Integer> out) {
        if (t == null) return;
        in(t.left, out); out.add(t.val); in(t.right, out);
    }
}
