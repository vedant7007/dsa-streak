import java.util.*;

public class SolutionTest {
    static long d(int[] p) { return (long) p[0] * p[0] + (long) p[1] * p[1]; }

    static String norm(int[][] a) {
        List<String> l = new ArrayList<>();
        for (int[] p : a) l.add(p[0] + "," + p[1]);
        Collections.sort(l);
        return l.toString();
    }

    static void check(int[][] pts, int k, int[][] exp) {
        int[][] got = new Solution().kClosest(pts, k);
        if (!norm(got).equals(norm(exp))) throw new AssertionError("k=" + k + " expected " + norm(exp) + " got " + norm(got));
    }

    static void checkDist(int[][] pts, int k) {
        int[][] got = new Solution().kClosest(pts, k);
        int[][] c = pts.clone();
        Arrays.sort(c, (a, b) -> Long.compare(d(a), d(b)));
        long[] want = new long[k], have = new long[k];
        for (int i = 0; i < k; i++) { want[i] = d(c[i]); have[i] = d(got[i]); }
        Arrays.sort(have);
        if (got.length != k || !Arrays.equals(want, have)) throw new AssertionError("distance multiset mismatch k=" + k);
    }

    public static void main(String[] args) {
        check(new int[][]{{1, 3}, {-2, 2}}, 1, new int[][]{{-2, 2}});
        check(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2, new int[][]{{3, 3}, {-2, 4}});
        check(new int[][]{{0, 0}}, 1, new int[][]{{0, 0}});
        check(new int[][]{{1, 1}, {2, 2}, {3, 3}}, 3, new int[][]{{1, 1}, {2, 2}, {3, 3}});
        check(new int[][]{{-10000, -10000}, {10000, 10000}, {1, -1}}, 1, new int[][]{{1, -1}});
        check(new int[][]{{-5, 4}, {4, 6}, {2, -2}}, 2, new int[][]{{-5, 4}, {2, -2}});
        Random rnd = new Random(973);
        int[][] big = new int[10000][];
        for (int i = 0; i < big.length; i++) big[i] = new int[]{rnd.nextInt(20001) - 10000, rnd.nextInt(20001) - 10000};
        checkDist(big, 5000);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(10);
            int[][] p = new int[n][];
            for (int i = 0; i < n; i++) p[i] = new int[]{rnd.nextInt(11) - 5, rnd.nextInt(11) - 5};
            checkDist(p, 1 + rnd.nextInt(n));
        }
        System.out.println("all k closest tests ok");
    }
}
