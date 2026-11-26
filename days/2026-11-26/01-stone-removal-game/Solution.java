class Solution {
    public boolean canAliceWin(int n) {
        int k = 10;
        boolean alice = true;
        while (n >= k) {
            n -= k;
            k--;
            alice = !alice;
        }
        return !alice;
    }
}
