class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] cnt = new int[26];
        int max = 0;
        for (char c : tasks) {
            cnt[c - 'A']++;
            max = Math.max(max, cnt[c - 'A']);
        }
        int maxCnt = 0;
        for (int x : cnt) if (x == max) maxCnt++;
        int ans = (max - 1) * (n + 1) + maxCnt;
        return Math.max(ans, tasks.length);
    }
}
