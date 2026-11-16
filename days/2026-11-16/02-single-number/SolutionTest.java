import java.util.*;

public class SolutionTest {
    static void check(int[] a, int exp) {
        int got = new Solution().singleNumber(a);
        if (got != exp) throw new AssertionError("nums=" + Arrays.toString(a) + " expected " + exp + " got " + got);
    }

    public static void main(String[] args) {
        check(new int[]{2, 2, 1}, 1);
        check(new int[]{4, 1, 2, 1, 2}, 4);
        check(new int[]{1}, 1);
        check(new int[]{-1, -1, -2}, -2);
        check(new int[]{0, 7, 7}, 0);
        check(new int[]{30000, -30000, 30000}, -30000);
        Random rnd = new Random(136);
        for (int it = 0; it < 200; it++) {
            int k = rnd.nextInt(10);
            List<Integer> list = new ArrayList<>();
            Set<Integer> used = new HashSet<>();
            int single = rnd.nextInt(60001) - 30000;
            used.add(single);
            list.add(single);
            for (int i = 0; i < k; i++) {
                int x;
                do { x = rnd.nextInt(60001) - 30000; } while (used.contains(x));
                used.add(x);
                list.add(x);
                list.add(x);
            }
            Collections.shuffle(list, rnd);
            int[] a = new int[list.size()];
            for (int i = 0; i < a.length; i++) a[i] = list.get(i);
            check(a, single);
        }
        System.out.println("all single number tests ok");
    }
}
