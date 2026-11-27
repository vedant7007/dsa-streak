import java.util.*;

public class SolutionTest {
    static void check(int[] pre, int[] in, List<Integer> exp) {
        TreeNode t = new Solution().buildTree(pre, in);
        List<Integer> got = Support.serialize(t);
        if (!got.equals(exp)) throw new AssertionError("pre=" + Arrays.toString(pre) + " expected " + exp + " got " + got);
    }

    static TreeNode randomTree(Random r, List<Integer> vals, int[] pos, int size) {
        if (size == 0) return null;
        TreeNode t = new TreeNode(vals.get(pos[0]++));
        int left = r.nextInt(size);
        t.left = randomTree(r, vals, pos, left);
        t.right = randomTree(r, vals, pos, size - 1 - left);
        return t;
    }

    static int[] arr(List<Integer> l) {
        int[] a = new int[l.size()];
        for (int i = 0; i < a.length; i++) a[i] = l.get(i);
        return a;
    }

    public static void main(String[] args) {
        check(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}, Arrays.asList(3, 9, 20, null, null, 15, 7));
        check(new int[]{-1}, new int[]{-1}, Arrays.asList(-1));
        check(new int[]{1, 2, 3}, new int[]{3, 2, 1}, Arrays.asList(1, 2, null, 3));
        check(new int[]{1, 2, 3}, new int[]{1, 2, 3}, Arrays.asList(1, null, 2, null, 3));
        check(new int[]{-3000, 3000}, new int[]{3000, -3000}, Arrays.asList(-3000, 3000));
        int n = 3000;
        int[] pre = new int[n], in = new int[n];
        for (int i = 0; i < n; i++) { pre[i] = i; in[i] = n - 1 - i; }
        TreeNode sk = new Solution().buildTree(pre, in);
        List<Integer> gp = new ArrayList<>(), gi = new ArrayList<>();
        Support.pre(sk, gp);
        Support.in(sk, gi);
        if (!Arrays.equals(arr(gp), pre) || !Arrays.equals(arr(gi), in)) throw new AssertionError("skewed 3000 failed");
        Random r = new Random(3);
        for (int t = 0; t < 200; t++) {
            int sz = 1 + r.nextInt(15);
            List<Integer> vals = new ArrayList<>();
            for (int v = -20; v <= 20; v++) vals.add(v);
            Collections.shuffle(vals, r);
            TreeNode orig = randomTree(r, vals, new int[]{0}, sz);
            List<Integer> p = new ArrayList<>(), q = new ArrayList<>();
            Support.pre(orig, p);
            Support.in(orig, q);
            check(arr(p), arr(q), Support.serialize(orig));
        }
        System.out.println("all tests passed");
    }
}
