import java.util.*;

public class SolutionTest {
    static void check(Integer[] in, Integer[] exp) {
        List<Integer> got = Support.toList(new Solution().invertTree(Support.build(in)));
        if (!got.equals(Arrays.asList(exp))) throw new AssertionError("root=" + Arrays.toString(in) + " expected " + Arrays.toString(exp) + " got " + got);
    }

    static TreeNode mirror(TreeNode t) {
        if (t == null) return null;
        return new TreeNode(t.val, mirror(t.right), mirror(t.left));
    }

    public static void main(String[] args) {
        check(new Integer[]{4, 2, 7, 1, 3, 6, 9}, new Integer[]{4, 7, 2, 9, 6, 3, 1});
        check(new Integer[]{2, 1, 3}, new Integer[]{2, 3, 1});
        check(new Integer[]{}, new Integer[]{});
        check(new Integer[]{1}, new Integer[]{1});
        check(new Integer[]{1, 2}, new Integer[]{1, null, 2});
        check(new Integer[]{1, null, 2, null, 3}, new Integer[]{1, 2, null, 3});
        check(new Integer[]{-100, -100, 100}, new Integer[]{-100, 100, -100});
        Random rnd = new Random(226);
        for (int t = 0; t < 200; t++) {
            int n = rnd.nextInt(25);
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++) a[i] = (i > 0 && rnd.nextInt(4) == 0) ? null : rnd.nextInt(201) - 100;
            List<Integer> exp = Support.toList(mirror(Support.build(a)));
            List<Integer> got = Support.toList(new Solution().invertTree(Support.build(a)));
            if (!got.equals(exp)) throw new AssertionError("random root=" + Arrays.toString(a) + " expected " + exp + " got " + got);
        }
        System.out.println("all invert binary tree tests ok");
    }
}
