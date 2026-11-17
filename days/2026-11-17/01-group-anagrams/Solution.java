import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] cnt = new int[26];
            for (char c : s.toCharArray()) cnt[c - 'a']++;
            StringBuilder sb = new StringBuilder();
            for (int x : cnt) sb.append(x).append('#');
            map.computeIfAbsent(sb.toString(), k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
