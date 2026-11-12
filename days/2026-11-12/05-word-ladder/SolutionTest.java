import java.util.*;

public class SolutionTest {
    static void check(String b, String e, List<String> list, int exp) {
        int got = new Solution().ladderLength(b, e, new ArrayList<>(list));
        if (got != exp) throw new AssertionError(b + " -> " + e + " " + list + " expected " + exp + " got " + got);
    }

    static boolean adj(String a, String b) {
        int d = 0;
        for (int i = 0; i < a.length(); i++) if (a.charAt(i) != b.charAt(i)) d++;
        return d == 1;
    }

    static int brute(String b, String e, List<String> list) {
        List<String> nodes = new ArrayList<>(new LinkedHashSet<>(list));
        if (!nodes.contains(e)) return 0;
        nodes.add(0, b);
        int n = nodes.size();
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[0] = 1;
        Deque<Integer> q = new ArrayDeque<>();
        q.add(0);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 1; v < n; v++) {
                if (dist[v] == -1 && adj(nodes.get(u), nodes.get(v))) {
                    dist[v] = dist[u] + 1;
                    q.add(v);
                }
            }
        }
        int idx = nodes.lastIndexOf(e);
        return dist[idx] == -1 ? 0 : dist[idx];
    }

    public static void main(String[] args) {
        check("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"), 5);
        check("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log"), 0);
        check("a", "c", Arrays.asList("a", "b", "c"), 2);
        check("hot", "dog", Arrays.asList("hot", "dog"), 0);
        check("hot", "dot", Arrays.asList("dot", "dot", "hot"), 2);
        check("ab", "cd", Arrays.asList("cd"), 0);
        check("hit", "hot", Arrays.asList("hot"), 2);
        Random rnd = new Random(9);
        for (int t = 0; t < 200; t++) {
            int len = 2 + rnd.nextInt(2);
            String b = rw(rnd, len);
            String e;
            do { e = rw(rnd, len); } while (e.equals(b));
            List<String> list = new ArrayList<>();
            int n = rnd.nextInt(15);
            for (int i = 0; i < n; i++) list.add(rw(rnd, len));
            if (rnd.nextBoolean()) list.add(e);
            check(b, e, list, brute(b, e, list));
        }
        System.out.println("all word ladder tests ok");
    }

    static String rw(Random rnd, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) sb.append((char) ('a' + rnd.nextInt(3)));
        return sb.toString();
    }
}
