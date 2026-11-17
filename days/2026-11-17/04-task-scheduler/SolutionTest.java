import java.util.*;

public class SolutionTest {
    static int brute(char[] tasks, int n) {
        int[] cnt = new int[26];
        for (char c : tasks) cnt[c - 'A']++;
        int[] last = new int[26];
        Arrays.fill(last, -1000);
        int left = tasks.length, time = 0;
        while (left > 0) {
            int best = -1;
            for (int i = 0; i < 26; i++)
                if (cnt[i] > 0 && time - last[i] > n && (best == -1 || cnt[i] > cnt[best])) best = i;
            if (best != -1) { cnt[best]--; last[best] = time; left--; }
            time++;
        }
        return time;
    }

    static void check(String s, int n, int exp) {
        int got = new Solution().leastInterval(s.toCharArray(), n);
        if (got != exp) throw new AssertionError("tasks=" + s + " n=" + n + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check("AAABBB", 2, 8);
        check("ACABDB", 1, 6);
        check("AAABBB", 3, 10);
        check("A", 0, 1);
        check("A", 100, 1);
        check("AAAA", 0, 4);
        check("AAAA", 2, 10);
        check("ABCDEFG", 2, 7);
        check("AAABBBCCCDD", 2, 11);
        Random rnd = new Random(621);
        for (int t = 0; t < 200; t++) {
            int len = 1 + rnd.nextInt(20), n = rnd.nextInt(5);
            char[] a = new char[len];
            for (int i = 0; i < len; i++) a[i] = (char) ('A' + rnd.nextInt(4));
            check(new String(a), n, brute(a, n));
        }
        char[] big = new char[10000];
        Arrays.fill(big, 'Z');
        check(new String(big), 100, 9999 * 101 + 1);
        System.out.println("all task scheduler tests ok");
    }
}
