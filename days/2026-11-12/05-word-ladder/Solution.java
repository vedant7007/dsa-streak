import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return 0;
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        set.remove(beginWord);
        int steps = 1;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int s = 0; s < sz; s++) {
                char[] cur = q.poll().toCharArray();
                for (int i = 0; i < cur.length; i++) {
                    char old = cur[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == old) continue;
                        cur[i] = c;
                        String w = new String(cur);
                        if (set.contains(w)) {
                            if (w.equals(endWord)) return steps + 1;
                            set.remove(w);
                            q.add(w);
                        }
                    }
                    cur[i] = old;
                }
            }
            steps++;
        }
        return 0;
    }
}
