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
    // build from LeetCode level order array, null = missing child
    static TreeNode build(Integer[] a) {
        if (a.length == 0 || a[0] == null) return null;
        TreeNode root = new TreeNode(a[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < a.length) {
            TreeNode t = q.poll();
            if (i < a.length && a[i] != null) { t.left = new TreeNode(a[i]); q.add(t.left); }
            i++;
            if (i < a.length && a[i] != null) { t.right = new TreeNode(a[i]); q.add(t.right); }
            i++;
        }
        return root;
    }
}
