import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

class Support {
    // adjacency list (1-indexed) -> node 1, or null for empty list
    static Node build(int[][] adj, List<Node> all) {
        if (adj.length == 0) return null;
        Node[] nodes = new Node[adj.length + 1];
        for (int i = 1; i <= adj.length; i++) { nodes[i] = new Node(i); all.add(nodes[i]); }
        for (int i = 1; i <= adj.length; i++)
            for (int x : adj[i - 1]) nodes[i].neighbors.add(nodes[x]);
        return nodes[1];
    }

    // BFS from node, returns adjacency list ordered by val; also collects visited nodes
    static List<List<Integer>> toAdj(Node node, Set<Node> seen) {
        List<List<Integer>> out = new ArrayList<>();
        if (node == null) return out;
        Map<Integer, List<Integer>> m = new TreeMap<>();
        Deque<Node> q = new ArrayDeque<>();
        q.add(node); seen.add(node);
        while (!q.isEmpty()) {
            Node c = q.poll();
            List<Integer> l = new ArrayList<>();
            for (Node nb : c.neighbors) {
                l.add(nb.val);
                if (seen.add(nb)) q.add(nb);
            }
            m.put(c.val, l);
        }
        out.addAll(m.values());
        return out;
    }
}
