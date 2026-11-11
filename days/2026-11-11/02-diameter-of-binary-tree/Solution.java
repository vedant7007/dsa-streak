class Solution {
    int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return ans;
    }

    int depth(TreeNode node) {
        if (node == null) return 0;
        int l = depth(node.left);
        int r = depth(node.right);
        ans = Math.max(ans, l + r);
        return 1 + Math.max(l, r);
    }
}
