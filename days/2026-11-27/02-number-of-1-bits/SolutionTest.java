import java.util.*;

public class SolutionTest {
    static void check(int n, int exp) {
        int got = new Solution().hammingWeight(n);
        if (got != exp) throw new AssertionError("n=" + n + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(11, 3);
        check(128, 1);
        check(2147483645, 30);
        check(1, 1);
        check(Integer.MAX_VALUE, 31);
        check(1 << 30, 1);
        check(7, 3);
        Random r = new Random(1);
        for (int t = 0; t < 200; t++) {
            int n = 1 + r.nextInt(Integer.MAX_VALUE);
            int c = 0;
            for (char ch : Integer.toBinaryString(n).toCharArray()) if (ch == '1') c++;
            check(n, c);
        }
        System.out.println("all tests passed");
    }
}
