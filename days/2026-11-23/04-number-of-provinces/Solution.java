class Solution {
    int[] par;

    int find(int x) {
        if (par[x] != x) par[x] = find(par[x]);
        return par[x];
    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        par = new int[n];
        for (int i = 0; i < n; i++) par[i] = i;
        int cnt = n;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    int a = find(i), b = find(j);
                    if (a != b) {
                        par[a] = b;
                        cnt--;
                    }
                }
            }
        }
        return cnt;
    }
}
