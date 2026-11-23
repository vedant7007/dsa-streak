import java.util.*;

public class SolutionTest {
    static void brute(TreeNode t, int d, List<List<Integer>> out) {
        if (t == null) return;
        if (out.size() == d) out.add(new ArrayList<>());
        out.get(d).add(t.val);
        brute(t.left, d + 1, out);
        brute(t.right, d + 1, out);
    }

    static void check(Integer[] a, String exp) {
        String got = new Solution().levelOrder(Support.build(a)).toString().replace(" ", "");
        if (!got.equals(exp)) throw new AssertionError(Arrays.toString(a) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new Integer[]{3, 9, 20, null, null, 15, 7}, "[[3],[9,20],[15,7]]");
        check(new Integer[]{1}, "[[1]]");
        check(new Integer[]{}, "[]");
        check(new Integer[]{1, 2, null, 3, null, 4}, "[[1],[2],[3],[4]]");
        check(new Integer[]{-1000, 1000, -1000}, "[[-1000],[1000,-1000]]");
        check(new Integer[]{1, 2, 3, 4, 5, 6, 7}, "[[1],[2,3],[4,5,6,7]]");
        Integer[] chain = new Integer[1 + 2 * 1999];
        chain[0] = 0;
        for (int i = 1; i < 2000; i++) chain[2 * i] = i;
        List<List<Integer>> deep = new Solution().levelOrder(Support.build(chain));
        if (deep.size() != 2000 || deep.get(1999).get(0) != 1999) throw new AssertionError("deep chain failed");
        Random rnd = new Random(1123);
        for (int it = 0; it < 200; it++) {
            int n = rnd.nextInt(15);
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++) a[i] = (i > 0 && rnd.nextInt(4) == 0) ? null : rnd.nextInt(21) - 10;
            TreeNode root = Support.build(a);
            List<List<Integer>> exp = new ArrayList<>();
            brute(root, 0, exp);
            List<List<Integer>> got = new Solution().levelOrder(root);
            if (!got.equals(exp)) throw new AssertionError(Arrays.toString(a) + " expected " + exp + " got " + got);
        }
        System.out.println("all level order tests ok");
    }
}
