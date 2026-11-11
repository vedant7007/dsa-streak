import java.util.*;

public class SolutionTest {
    static void check(int[][] in, int[][] expected, String label) {
        int[][] copy = new int[in.length][];
        for (int i = 0; i < in.length; i++) copy[i] = in[i].clone();
        int[][] got = new Solution().merge(copy);
        Arrays.sort(got, (a, b) -> a[0] - b[0]);
        if (!Arrays.deepEquals(got, expected))
            throw new AssertionError(label + ": expected " + Arrays.deepToString(expected) + " but got " + Arrays.deepToString(got));
    }

    static int[][] brute(int[][] in) {
        boolean[] cov = new boolean[41];
        boolean[] gap = new boolean[41];
        boolean[] pt = new boolean[41];
        for (int[] iv : in) {
            for (int x = iv[0]; x <= iv[1]; x++) pt[x] = true;
            for (int x = iv[0]; x < iv[1]; x++) gap[x] = true;
        }
        List<int[]> out = new ArrayList<>();
        int x = 0;
        while (x <= 40) {
            if (!pt[x]) { x++; continue; }
            int s = x;
            while (gap[x]) x++;
            out.add(new int[]{s, x});
            x++;
        }
        return out.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        check(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}, new int[][]{{1, 6}, {8, 10}, {15, 18}}, "Example 1");
        check(new int[][]{{1, 4}, {4, 5}}, new int[][]{{1, 5}}, "Example 2");
        check(new int[][]{{4, 7}, {1, 4}}, new int[][]{{1, 7}}, "Example 3");
        check(new int[][]{{5, 5}}, new int[][]{{5, 5}}, "single point interval");
        check(new int[][]{{1, 10}, {2, 3}, {4, 5}}, new int[][]{{1, 10}}, "contained intervals");
        check(new int[][]{{2, 2}, {2, 2}, {0, 0}}, new int[][]{{0, 0}, {2, 2}}, "duplicates and zeros");
        check(new int[][]{{1, 2}, {3, 4}}, new int[][]{{1, 2}, {3, 4}}, "touching gap not merged");
        int[][] big = new int[10000][];
        for (int i = 0; i < 10000; i++) big[i] = new int[]{9999 - i, 10000 - i};
        check(big, new int[][]{{0, 10000}}, "10^4 chained intervals reversed");
        Random rnd = new Random(4);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(6);
            int[][] in = new int[n][];
            for (int i = 0; i < n; i++) { int a = rnd.nextInt(30), b = a + rnd.nextInt(8); in[i] = new int[]{a, b}; }
            check(in, brute(in), "random #" + t);
        }
        System.out.println("All tests passed");
    }
}
