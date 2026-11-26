public class SolutionTest {
    static boolean brute(int n) {
        int turn = 0;
        for (int k = 10; k >= 1; k--) {
            if (n < k) return turn == 1;
            n -= k;
            turn ^= 1;
        }
        return turn == 1;
    }

    static void check(int n, boolean exp, String name) {
        boolean got = new Solution().canAliceWin(n);
        if (got != exp) throw new AssertionError(name + ": n=" + n + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(12, true, "example 1");
        check(1, false, "example 2");
        check(9, false, "just below 10");
        check(10, true, "exactly 10");
        check(18, true, "10 + 8, bob cannot take 9");
        check(19, false, "10 + 9, alice cannot take 8");
        check(26, false, "26 - 10 - 9 = 7, alice cannot take 8");
        check(27, true, "10 + 9 + 8 exactly");
        check(50, true, "max n");
        for (int n = 1; n <= 50; n++) check(n, brute(n), "all n " + n);
        System.out.println("stone-removal-game: all tests passed");
    }
}
