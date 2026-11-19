import java.util.*;

class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> st = new ArrayDeque<>();
        for (String t : tokens) {
            if (t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/")) {
                int b = st.pop(), a = st.pop();
                if (t.equals("+")) st.push(a + b);
                else if (t.equals("-")) st.push(a - b);
                else if (t.equals("*")) st.push(a * b);
                else st.push(a / b);
            } else {
                st.push(Integer.parseInt(t));
            }
        }
        return st.pop();
    }
}
