import java.util.Random;
import java.util.TreeSet;

public class SolutionTest {
    static void check(int got, int expected, String label) {
        if (got != expected) throw new AssertionError(label + ": expected " + expected + " but got " + got);
    }

    static int brute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) if (nums[i] == target) return i;
        return -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        check(s.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0), 4, "Example 1");
        check(s.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3), -1, "Example 2");
        check(s.search(new int[]{1}, 0), -1, "Example 3");
        // edge cases
        check(s.search(new int[]{1}, 1), 0, "single element found");
        check(s.search(new int[]{1, 2, 3, 4, 5}, 4), 3, "not rotated");
        check(s.search(new int[]{3, 1}, 1), 1, "two elements rotated");
        check(s.search(new int[]{-3, -1, -10, -7}, -7), 3, "negatives");
        check(s.search(new int[]{5, 1, 3}, 5), 0, "target at pivot start");
        Random rnd = new Random(3);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(12);
            TreeSet<Integer> set = new TreeSet<>();
            while (set.size() < n) set.add(rnd.nextInt(41) - 20);
            Integer[] sorted = set.toArray(new Integer[0]);
            int k = rnd.nextInt(n);
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) nums[i] = sorted[(i + k) % n];
            int target = rnd.nextInt(45) - 22;
            check(s.search(nums, target), brute(nums, target), "random #" + t);
        }
        System.out.println("All tests passed");
    }
}
