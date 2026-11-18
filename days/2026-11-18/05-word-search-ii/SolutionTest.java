import java.util.*;

public class SolutionTest {
    static boolean exist(char[][] b, String w, int i, int j, int k, boolean[][] vis) {
        if (k == w.length()) return true;
        if (i < 0 || j < 0 || i >= b.length || j >= b[0].length || vis[i][j] || b[i][j] != w.charAt(k)) return false;
        vis[i][j] = true;
        boolean ok = exist(b, w, i + 1, j, k + 1, vis) || exist(b, w, i - 1, j, k + 1, vis)
                || exist(b, w, i, j + 1, k + 1, vis) || exist(b, w, i, j - 1, k + 1, vis);
        vis[i][j] = false;
        return ok;
    }

    static List<String> brute(char[][] b, String[] words) {
        Set<String> res = new TreeSet<>();
        for (String w : words)
            for (int i = 0; i < b.length; i++)
                for (int j = 0; j < b[0].length; j++)
                    if (exist(b, w, i, j, 0, new boolean[b.length][b[0].length])) res.add(w);
        return new ArrayList<>(res);
    }

    static char[][] copy(char[][] b) {
        char[][] c = new char[b.length][];
        for (int i = 0; i < b.length; i++) c[i] = b[i].clone();
        return c;
    }

    static void check(char[][] b, String[] words, List<String> exp) {
        char[][] orig = copy(b);
        List<String> got = new ArrayList<>(new Solution().findWords(b, words));
        Collections.sort(got);
        List<String> e = new ArrayList<>(exp);
        Collections.sort(e);
        if (!got.equals(e)) throw new AssertionError("board=" + Arrays.deepToString(orig) + " words=" + Arrays.toString(words) + " expected " + e + " got " + got);
        if (!Arrays.deepEquals(orig, b)) throw new AssertionError("board was not restored");
    }

    public static void main(String[] args) {
        char[][] b1 = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        check(b1, new String[]{"oath", "pea", "eat", "rain"}, Arrays.asList("eat", "oath"));
        check(new char[][]{{'a', 'b'}, {'c', 'd'}}, new String[]{"abcb"}, Arrays.asList());
        check(new char[][]{{'a'}}, new String[]{"a"}, Arrays.asList("a"));
        check(new char[][]{{'a'}}, new String[]{"b", "aa"}, Arrays.asList());
        check(new char[][]{{'a', 'a'}}, new String[]{"aaa"}, Arrays.asList());
        check(new char[][]{{'a', 'b'}, {'a', 'a'}}, new String[]{"aba", "baa", "bab", "aaab", "aaa", "aaaa", "aaba"},
              Arrays.asList("aaa", "aaab", "aaba", "aba", "baa"));
        check(new char[][]{{'o', 'a', 'b', 'n'}, {'o', 't', 'a', 'e'}, {'a', 'h', 'k', 'r'}, {'a', 'f', 'l', 'v'}},
              new String[]{"oa", "oaa"}, Arrays.asList("oa", "oaa"));
        Random rnd = new Random(212);
        for (int it = 0; it < 200; it++) {
            int m = 1 + rnd.nextInt(4), n = 1 + rnd.nextInt(4);
            char[][] b = new char[m][n];
            for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) b[i][j] = (char) ('a' + rnd.nextInt(3));
            int k = 1 + rnd.nextInt(8);
            Set<String> ws = new LinkedHashSet<>();
            while (ws.size() < k) {
                int len = 1 + rnd.nextInt(5);
                StringBuilder sb = new StringBuilder();
                for (int t = 0; t < len; t++) sb.append((char) ('a' + rnd.nextInt(3)));
                ws.add(sb.toString());
            }
            String[] words = ws.toArray(new String[0]);
            check(b, words, brute(b, words));
        }
        System.out.println("all word search ii tests ok");
    }
}
