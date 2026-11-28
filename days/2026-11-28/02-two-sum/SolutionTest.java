import java.util.*;

public class SolutionTest {
    static void check(int[] nums, int target) {
        int[] got = new Solution().twoSum(nums.clone(), target);
        if (got.length != 2 || got[0] == got[1] || got[0] < 0 || got[1] < 0 || got[0] >= nums.length || got[1] >= nums.length
                || (long) nums[got[0]] + nums[got[1]] != target)
            throw new AssertionError("nums=" + Arrays.toString(nums) + " target=" + target + " got " + Arrays.toString(got));
    }

    static void exact(int[] nums, int target, int[] exp) {
        int[] got = new Solution().twoSum(nums, target);
        Arrays.sort(got);
        if (!Arrays.equals(got, exp)) throw new AssertionError("nums=" + Arrays.toString(nums) + " expected " + Arrays.toString(exp) + " got " + Arrays.toString(got));
    }

    public static void main(String[] args) {
        exact(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1});
        exact(new int[]{3, 2, 4}, 6, new int[]{1, 2});
        exact(new int[]{3, 3}, 6, new int[]{0, 1});
        exact(new int[]{-3, 4, 3, 90}, 0, new int[]{0, 2});
        exact(new int[]{-1000000000, 1000000000}, 0, new int[]{0, 1});
        exact(new int[]{0, 4, 3, 0}, 0, new int[]{0, 3});
        int[] big = new int[10000];
        for (int i = 0; i < big.length; i++) big[i] = i * 2;
        big[9999] = 1;
        exact(big, 19997, new int[]{9998, 9999});
        Random r = new Random(3);
        for (int t = 0; t < 200; t++) {
            int n = 2 + r.nextInt(8);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = r.nextInt(21) - 10;
            int x = r.nextInt(n), y = r.nextInt(n - 1);
            if (y >= x) y++;
            check(a, a[x] + a[y]);
        }
        System.out.println("all tests passed");
    }
}
