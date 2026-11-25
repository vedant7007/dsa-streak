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
    // builds a tree from LeetCode level order, null = missing node
    static TreeNode build(Integer... vals) {
        if (vals.length == 0 || vals[0] == null) return null;
        TreeNode root = new TreeNode(vals[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < vals.length) {
            TreeNode t = q.poll();
            if (i < vals.length && vals[i] != null) { t.left = new TreeNode(vals[i]); q.add(t.left); }
            i++;
            if (i < vals.length && vals[i] != null) { t.right = new TreeNode(vals[i]); q.add(t.right); }
            i++;
        }
        return root;
    }
}
