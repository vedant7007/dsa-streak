class Solution {
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        gain(root);
        return max;
    }

    private int gain(TreeNode node) {
        if (node == null) return 0;
        int l = Math.max(0, gain(node.left));
        int r = Math.max(0, gain(node.right));
        max = Math.max(max, node.val + l + r);
        return node.val + Math.max(l, r);
    }
}
