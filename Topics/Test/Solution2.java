package Topics.Test;

import java.util.PriorityQueue;
import org.junit.Test;

public class Solution2 {

    @Test
    public void test1() {
        int[] A = { 1, 3, 1, 2, 6, 4 };
        System.out.println(this.solution(A));
    }

    @Test
    public void test2() {
        int[] A = { 2, 3 };
        System.out.println(this.solution(A));
    }

    public int solution(int[] A) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> a - b);
        for (int num : A) {
            if (num > 0) {
                queue.add(num);
            }
        }
        int ans = 1;
        if (queue.size() > 0 && queue.peek() == 1) {
            int current = queue.poll();
            while (queue.size() > 0 && (current == queue.peek() || current + 1 == queue.peek())) {
                current = queue.poll();
            }
            ans = current + 1;
        }
        return ans;
    }
}
