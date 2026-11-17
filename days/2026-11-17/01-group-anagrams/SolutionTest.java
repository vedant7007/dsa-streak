import java.util.*;

public class SolutionTest {
    static List<List<String>> norm(List<List<String>> g) {
        List<List<String>> res = new ArrayList<>();
        for (List<String> l : g) { List<String> c = new ArrayList<>(l); Collections.sort(c); res.add(c); }
        res.sort((a, b) -> String.join(",", a).compareTo(String.join(",", b)));
        return res;
    }

    static List<List<String>> brute(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        boolean[] used = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            if (used[i]) continue;
            List<String> g = new ArrayList<>();
            char[] a = strs[i].toCharArray(); Arrays.sort(a);
            for (int j = i; j < strs.length; j++) {
                char[] b = strs[j].toCharArray(); Arrays.sort(b);
                if (!used[j] && Arrays.equals(a, b)) { used[j] = true; g.add(strs[j]); }
            }
            res.add(g);
        }
        return res;
    }

    static void check(String[] in, List<List<String>> exp) {
        List<List<String>> got = norm(new Solution().groupAnagrams(in));
        List<List<String>> e = norm(exp);
        if (!got.equals(e)) throw new AssertionError("strs=" + Arrays.toString(in) + " expected " + e + " got " + got);
    }

    public static void main(String[] args) {
        check(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},
              List.of(List.of("bat"), List.of("nat", "tan"), List.of("ate", "eat", "tea")));
        check(new String[]{""}, List.of(List.of("")));
        check(new String[]{"a"}, List.of(List.of("a")));
        check(new String[]{"", ""}, List.of(List.of("", "")));
        check(new String[]{"abc", "abc", "cba"}, List.of(List.of("abc", "abc", "cba")));
        check(new String[]{"ab", "a", "b"}, List.of(List.of("ab"), List.of("a"), List.of("b")));
        check(new String[]{"aab", "abb"}, List.of(List.of("aab"), List.of("abb")));
        Random rnd = new Random(1117);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(12);
            String[] a = new String[n];
            for (int i = 0; i < n; i++) {
                int len = rnd.nextInt(4);
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < len; k++) sb.append((char) ('a' + rnd.nextInt(3)));
                a[i] = sb.toString();
            }
            check(a, brute(a));
        }
        System.out.println("all group anagrams tests ok");
    }
}
