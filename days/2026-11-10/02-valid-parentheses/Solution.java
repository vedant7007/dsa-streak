import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public boolean isValid(String s) {
        // odd length can never be fully matched
        if (s.length() % 2 == 1) return false;
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            // for an opener, push the closer we expect to see later
            if (ch == '(') stack.push(')');
            else if (ch == '[') stack.push(']');
            else if (ch == '{') stack.push('}');
            else if (stack.isEmpty() || stack.pop() != ch) return false;
        }
        return stack.isEmpty();
    }
}
