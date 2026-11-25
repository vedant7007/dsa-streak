import java.util.*;

public class SolutionTest {
    static void check(TreeNode p, TreeNode q, boolean exp, String name) {
        boolean got = new Solution().isSameTree(p, q);
        if (got != exp) throw new AssertionError(name + ": expected " + exp + " got " + got);
    }

    static String ser(TreeNode t) {
        return t == null ? "#" : "(" + t.val + "," + ser(t.left) + "," + ser(t.right) + ")";
    }

    static TreeNode rand(Random r, int depth) {
        if (depth == 0 || r.nextInt(4) == 0) return null;
        return new TreeNode(r.nextInt(3), rand(r, depth - 1), rand(r, depth - 1));
    }

    public static void main(String[] args) {
        check(Support.build(1, 2, 3), Support.build(1, 2, 3), true, "example 1");
        check(Support.build(1, 2), Support.build(1, null, 2), false, "example 2");
        check(Support.build(1, 2, 1), Support.build(1, 1, 2), false, "example 3");
        check(null, null, true, "both empty");
        check(Support.build(0), null, false, "one empty");
        check(Support.build(-5), Support.build(-5), true, "single negative");
        check(Support.build(1, 1, 1, 1), Support.build(1, 1, 1, null, 1), false, "same values different shape");
        Random r = new Random(11);
        for (int t = 0; t < 200; t++) {
            TreeNode a = rand(r, 4), b = r.nextBoolean() ? rand(r, 4) : a;
            check(a, b, ser(a).equals(ser(b)), "random " + ser(a) + " vs " + ser(b));
        }
        System.out.println("same-tree: all tests passed");
    }
}
