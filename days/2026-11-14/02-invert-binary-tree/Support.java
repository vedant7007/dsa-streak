import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
}

class Support {
    // build from LeetCode level order, null = missing node
    static TreeNode build(Integer[] a) {
        if (a.length == 0 || a[0] == null) return null;
        TreeNode root = new TreeNode(a[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < a.length) {
            TreeNode cur = q.poll();
            if (i < a.length && a[i] != null) { cur.left = new TreeNode(a[i]); q.add(cur.left); }
            i++;
            if (i < a.length && a[i] != null) { cur.right = new TreeNode(a[i]); q.add(cur.right); }
            i++;
        }
        return root;
    }

    // level order with trailing nulls trimmed, like LeetCode output
    static List<Integer> toList(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur == null) { res.add(null); continue; }
            res.add(cur.val);
            q.add(cur.left);
            q.add(cur.right);
        }
        while (!res.isEmpty() && res.get(res.size() - 1) == null) res.remove(res.size() - 1);
        return res;
    }
}
