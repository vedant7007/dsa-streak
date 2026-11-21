import java.util.*;

public class SolutionTest {
    static int[] brute(int[] parent, String s) {
        int n = parent.length;
        int[] np = parent.clone();
        for (int x = 1; x < n; x++) {
            int y = parent[x];
            while (y != -1 && s.charAt(y) != s.charAt(x)) y = parent[y];
            if (y != -1) np[x] = y;
        }
        int[] ans = new int[n];
        for (int x = 0; x < n; x++) {
            int y = x;
            while (y != -1) { ans[y]++; y = np[y]; }
        }
        return ans;
    }

    static void check(int[] parent, String s, int[] exp) {
        int[] got = new Solution().findSubtreeSizes(parent, s);
        if (!Arrays.equals(got, exp))
            throw new AssertionError(Arrays.toString(parent) + " s=" + s + " expected " + Arrays.toString(exp) + " got " + Arrays.toString(got));
    }

    public static void main(String[] args) {
        check(new int[]{-1, 0, 0, 1, 1, 1}, "abaabc", new int[]{6, 3, 1, 1, 1, 1});
        check(new int[]{-1, 0, 4, 0, 1}, "abbba", new int[]{5, 2, 1, 1, 1});
        check(new int[]{-1}, "z", new int[]{1});
        check(new int[]{-1, 0, 1, 2}, "aaaa", new int[]{4, 3, 2, 1});
        check(new int[]{-1, 0, 1, 2}, "abcd", new int[]{4, 3, 2, 1});
        check(new int[]{-1, 0, 1, 2, 3}, "ababa", new int[]{5, 2, 2, 1, 1});
        int n = 100000;
        int[] chain = new int[n];
        chain[0] = -1;
        for (int i = 1; i < n; i++) chain[i] = i - 1;
        char[] cs = new char[n];
        Arrays.fill(cs, 'q');
        int[] exp = new int[n];
        for (int i = 0; i < n; i++) exp[i] = n - i;
        check(chain, new String(cs), exp);
        Random rnd = new Random(331);
        for (int it = 0; it < 200; it++) {
            int m = 1 + rnd.nextInt(12);
            int[] p = new int[m];
            p[0] = -1;
            for (int i = 1; i < m; i++) p[i] = rnd.nextInt(i);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++) sb.append((char) ('a' + rnd.nextInt(3)));
            check(p, sb.toString(), brute(p, sb.toString()));
        }
        System.out.println("all find subtree sizes tests ok");
    }
}
