import java.util.*;

public class SolutionTest {
    static TreeNode insert(TreeNode t, int v) {
        if (t == null) return new TreeNode(v);
        if (v < t.val) t.left = insert(t.left, v);
        else t.right = insert(t.right, v);
        return t;
    }

    static void check(Integer[] a, int k, int exp) {
        int got = new Solution().kthSmallest(Support.build(a), k);
        if (got != exp) throw new AssertionError("root=" + Arrays.toString(a) + " k=" + k + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new Integer[]{3, 1, 4, null, 2}, 1, 1);
        check(new Integer[]{5, 3, 6, 2, 4, null, null, 1}, 3, 3);
        check(new Integer[]{7}, 1, 7);
        check(new Integer[]{0}, 1, 0);
        check(new Integer[]{2, 1, 3}, 3, 3);
        check(new Integer[]{5, 4, null, 3, null, 2, null, 1}, 5, 5);
        check(new Integer[]{1, null, 2, null, 3, null, 4}, 4, 4);
        Random rnd = new Random(1118);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(15);
            Set<Integer> set = new HashSet<>();
            while (set.size() < n) set.add(rnd.nextInt(100));
            List<Integer> vals = new ArrayList<>(set);
            Collections.shuffle(vals, rnd);
            TreeNode root = null;
            for (int v : vals) root = insert(root, v);
            Collections.sort(vals);
            int k = 1 + rnd.nextInt(n);
            int got = new Solution().kthSmallest(root, k);
            if (got != vals.get(k - 1)) throw new AssertionError("vals=" + vals + " k=" + k + " got " + got);
        }
        System.out.println("all kth smallest in bst tests ok");
    }
}
