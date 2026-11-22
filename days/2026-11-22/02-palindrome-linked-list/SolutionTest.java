import java.util.*;

public class SolutionTest {
    static boolean brute(int[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) if (a[i] != a[j]) return false;
        return true;
    }

    static void check(int[] a, boolean exp) {
        boolean got = new Solution().isPalindrome(Support.build(a));
        if (got != exp) throw new AssertionError(Arrays.toString(a) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{1, 2, 2, 1}, true);
        check(new int[]{1, 2}, false);
        check(new int[]{7}, true);
        check(new int[]{1, 2, 1}, true);
        check(new int[]{1, 1, 2}, false);
        check(new int[]{9, 9, 9, 9}, true);
        int[] big = new int[100000];
        for (int i = 0; i < big.length; i++) big[i] = Math.min(i, big.length - 1 - i) % 10;
        check(big, true);
        big[3] = (big[3] + 1) % 10;
        check(big, false);
        Random rnd = new Random(234);
        for (int it = 0; it < 200; it++) {
            int n = 1 + rnd.nextInt(10);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(3);
            if (rnd.nextBoolean()) for (int i = 0; i < n / 2; i++) a[n - 1 - i] = a[i];
            check(a, brute(a));
        }
        System.out.println("all palindrome linked list tests ok");
    }
}
