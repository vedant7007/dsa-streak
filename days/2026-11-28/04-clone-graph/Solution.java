import java.util.*;

class Solution {
    Map<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (map.containsKey(node)) return map.get(node);
        Node cur = new Node(node.val);
        map.put(node, cur);
        for (Node nb : node.neighbors) cur.neighbors.add(cloneGraph(nb));
        return cur;
    }
}
