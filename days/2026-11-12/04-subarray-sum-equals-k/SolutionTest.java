import java.util.*;

public class SolutionTest {
    static void check(int[] nums, int k, int exp) {
        int got = new Solution().subarraySum(nums, k);
        if (got != exp) throw new AssertionError("nums=" + Arrays.toString(nums) + " k=" + k + " expected " + exp + " got " + got);
    }

    static int brute(int[] nums, int k) {
        int c = 0;
        for (int i = 0; i < nums.length; i++) {
            int s = 0;
            for (int j = i; j < nums.length; j++) {
                s += nums[j];
                if (s == k) c++;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        check(new int[]{1, 1, 1}, 2, 2);
        check(new int[]{1, 2, 3}, 3, 2);
        check(new int[]{5}, 5, 1);
        check(new int[]{5}, 3, 0);
        check(new int[]{0, 0, 0}, 0, 6);
        check(new int[]{1, -1, 0}, 0, 3);
        check(new int[]{-1, -1, 1}, 0, 1);
        int[] big = new int[20000];
        Arrays.fill(big, 1000);
        check(big, 10000000, 10001);
        Random rnd = new Random(5);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(25);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(11) - 5;
            int k = rnd.nextInt(11) - 5;
            check(a, k, brute(a, k));
        }
        System.out.println("all subarray sum tests ok");
    }
}
