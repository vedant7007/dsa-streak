class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0, tank = 0, st = 0;
        for (int i = 0; i < gas.length; i++) {
            int d = gas[i] - cost[i];
            total += d;
            tank += d;
            if (tank < 0) {
                st = i + 1;
                tank = 0;
            }
        }
        return total < 0 ? -1 : st;
    }
}
