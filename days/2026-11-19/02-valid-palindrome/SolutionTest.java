import java.util.*;

public class SolutionTest {
    static boolean brute(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) if (Character.isLetterOrDigit(c)) sb.append(Character.toLowerCase(c));
        return sb.toString().equals(sb.reverse().toString());
    }

    static void check(String s, boolean exp) {
        boolean got = new Solution().isPalindrome(s);
        if (got != exp) throw new AssertionError("\"" + s + "\" expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check("A man, a plan, a canal: Panama", true);
        check("race a car", false);
        check(" ", true);
        check("a", true);
        check("0P", false);
        check(".,", true);
        check("ab_a", true);
        check("Aa", true);
        Random rnd = new Random(125);
        String alpha = "aAbB01 ,.:";
        for (int it = 0; it < 200; it++) {
            int n = rnd.nextInt(9);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) sb.append(alpha.charAt(rnd.nextInt(alpha.length())));
            String s = sb.toString();
            if (rnd.nextBoolean()) s = s + new StringBuilder(s).reverse();
            check(s, brute(s));
        }
        System.out.println("all valid palindrome tests ok");
    }
}
