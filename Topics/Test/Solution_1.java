package Topics.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import org.junit.Test;

public class Solution_1 {
    @Test
    public void test1() {
        int A[] = { 405, 45, 01110, 010 };
        System.out.println(this.solution(A));
    }

    public int solution(int[] A) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num : A) {
            int key = this.getFirstLast(num);
            map.putIfAbsent(key, new PriorityQueue<>((a, b) -> b - a));
            map.get(key).add(num);
        }
        int ans = -1;
        for (int key : map.keySet()) {
            if (map.get(key).size() > 1) {
                PriorityQueue<Integer> q = map.get(key);
                int thisans = q.poll() + q.poll();
                ans = Math.max(ans, thisans);
            }
        }
        return ans;
    }

    private int getFirstLast(int num) {
        int lastDigit = num % 10;
        int firstDigit = lastDigit;
        while (num != 0) {
            firstDigit = num % 10;
            num = num / 10;
        }
        return (firstDigit * 10) + lastDigit;
    }
}
