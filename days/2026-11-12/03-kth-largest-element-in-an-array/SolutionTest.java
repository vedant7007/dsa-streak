import java.util.*;

public class SolutionTest {
    static void check(int[] nums, int k, int exp) {
        int got = new Solution().findKthLargest(nums.clone(), k);
        if (got != exp) throw new AssertionError("nums=" + Arrays.toString(nums) + " k=" + k + " expected " + exp + " got " + got);
    }

    static int brute(int[] nums, int k) {
        int[] a = nums.clone();
        Arrays.sort(a);
        return a[a.length - k];
    }

    public static void main(String[] args) {
        check(new int[]{3, 2, 1, 5, 6, 4}, 2, 5);
        check(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4, 4);
        check(new int[]{7}, 1, 7);
        check(new int[]{2, 2, 2, 2}, 3, 2);
        check(new int[]{-1, -5, -3}, 1, -1);
        check(new int[]{-1, -5, -3}, 3, -5);
        int[] big = new int[100000];
        for (int i = 0; i < big.length; i++) big[i] = i - 50000;
        check(big, 1, 49999);
        check(big, 100000, -50000);
        Random rnd = new Random(11);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(20);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(21) - 10;
            int k = 1 + rnd.nextInt(n);
            check(a, k, brute(a, k));
        }
        System.out.println("all kth largest tests ok");
    }
}
