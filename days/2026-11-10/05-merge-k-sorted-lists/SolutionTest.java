import java.util.*;

public class SolutionTest {
    static void check(List<Integer> got, List<Integer> expected, String label) {
        if (!got.equals(expected)) throw new AssertionError(label + ": expected " + expected + " but got " + got);
    }

    static List<Integer> run(int[][] arrays) {
        ListNode[] lists = new ListNode[arrays.length];
        for (int i = 0; i < arrays.length; i++) lists[i] = Support.build(arrays[i]);
        return Support.toList(new Solution().mergeKLists(lists));
    }

    static List<Integer> brute(int[][] arrays) {
        List<Integer> all = new ArrayList<>();
        for (int[] a : arrays) for (int v : a) all.add(v);
        Collections.sort(all);
        return all;
    }

    public static void main(String[] args) {
        check(run(new int[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}}), List.of(1, 1, 2, 3, 4, 4, 5, 6), "Example 1");
        check(run(new int[][]{}), List.of(), "Example 2");
        check(run(new int[][]{{}}), List.of(), "Example 3");
        // edge cases
        check(run(new int[][]{{5}}), List.of(5), "single list single node");
        check(run(new int[][]{{}, {-3, -1}, {}}), List.of(-3, -1), "empty lists mixed in, negatives");
        check(run(new int[][]{{2, 2}, {2}, {2, 2}}), List.of(2, 2, 2, 2, 2), "all duplicates");
        int[][] many = new int[10000][];
        for (int i = 0; i < many.length; i++) many[i] = new int[]{10000 - i};
        check(run(many), brute(many), "10^4 lists of one node");
        Random rnd = new Random(5);
        for (int t = 0; t < 200; t++) {
            int k = rnd.nextInt(6);
            int[][] arrays = new int[k][];
            for (int i = 0; i < k; i++) {
                int len = rnd.nextInt(6);
                arrays[i] = new int[len];
                for (int j = 0; j < len; j++) arrays[i][j] = rnd.nextInt(21) - 10;
                Arrays.sort(arrays[i]);
            }
            check(run(arrays), brute(arrays), "random #" + t);
        }
        System.out.println("All tests passed");
    }
}
