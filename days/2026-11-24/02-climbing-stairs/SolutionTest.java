public class SolutionTest {
    static int brute(int n) {
        if (n <= 1) return 1;
        return brute(n - 1) + brute(n - 2);
    }

    static void check(int n, int exp) {
        int got = new Solution().climbStairs(n);
        if (got != exp) throw new AssertionError("n=" + n + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(2, 2);
        check(3, 3);
        check(1, 1);
        check(4, 5);
        check(5, 8);
        check(45, 1836311903);
        for (int n = 1; n <= 25; n++) check(n, brute(n));
        System.out.println("all climbing stairs tests ok");
    }
}
