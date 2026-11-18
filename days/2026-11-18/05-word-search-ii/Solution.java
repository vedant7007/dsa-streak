import java.util.*;

class Solution {
    class Node {
        Node[] next = new Node[26];
        String word;
    }

    List<String> res = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {
        Node root = new Node();
        for (String w : words) {
            Node cur = root;
            for (char c : w.toCharArray()) {
                if (cur.next[c - 'a'] == null) cur.next[c - 'a'] = new Node();
                cur = cur.next[c - 'a'];
            }
            cur.word = w;
        }
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                dfs(board, i, j, root);
        return res;
    }

    void dfs(char[][] b, int i, int j, Node p) {
        if (i < 0 || j < 0 || i >= b.length || j >= b[0].length) return;
        char c = b[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return;
        p = p.next[c - 'a'];
        if (p.word != null) {
            res.add(p.word);
            p.word = null;
        }
        b[i][j] = '#';
        dfs(b, i + 1, j, p);
        dfs(b, i - 1, j, p);
        dfs(b, i, j + 1, p);
        dfs(b, i, j - 1, p);
        b[i][j] = c;
    }
}
