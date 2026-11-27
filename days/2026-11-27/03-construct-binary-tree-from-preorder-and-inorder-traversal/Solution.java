import java.util.*;

class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    int idx = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) map.put(inorder[i], i);
        return build(preorder, 0, inorder.length - 1);
    }

    TreeNode build(int[] pre, int l, int r) {
        if (l > r) return null;
        TreeNode root = new TreeNode(pre[idx++]);
        int mid = map.get(root.val);
        root.left = build(pre, l, mid - 1);
        root.right = build(pre, mid + 1, r);
        return root;
    }
}
