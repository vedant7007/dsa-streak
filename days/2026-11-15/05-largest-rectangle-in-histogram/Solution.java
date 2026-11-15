import java.util.*;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length, ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            int h = i == n ? 0 : heights[i];
            while (!st.isEmpty() && heights[st.peek()] >= h) {
                int top = st.pop();
                int w = st.isEmpty() ? i : i - st.peek() - 1;
                ans = Math.max(ans, heights[top] * w);
            }
            st.push(i);
        }
        return ans;
    }
}
