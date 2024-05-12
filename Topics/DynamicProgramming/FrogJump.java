package Topics.DynamicProgramming;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class FrogJump {
    @Test
    public void test1() {
        int[] stones = { 0, 1, 3, 5, 6, 8, 12, 17 };
        boolean result = new FJSol().canCross(stones);
        assertEquals(true, result);
    }

    @Test
    public void test2() {
        int[] stones = { 0, 1, 2, 3, 4, 8, 9, 11 };
        boolean result = new FJSol().canCross(stones);
        assertEquals(false, result);
    }

    @Test
    public void test3() {
        int[] stones = { 0, 1, 2, 3, 4, 5, 6, 12 };
        boolean result = new FJSol().canCross(stones);
        assertEquals(false, result);
    }

    @Test
    public void test4() {
        int[] stones = { 0, 1, 2147483647 };
        boolean result = new FJSol().canCross(stones);
        assertEquals(false, result);
    }
}

class FJSol {

    /**
     * @param stones
     * @return
     */
    public boolean canCross(int[] stones) {
        if (stones[1] != 1)
            return false;
        int n = stones.length;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.push(new int[] { 1, 1 });
        Map<Integer, Map<Integer, Integer>> visited = new HashMap<>();
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int currPos = curr[0], lastJump = curr[1];
            if (currPos == n - 1)
                return true;
            visited.putIfAbsent(currPos, new HashMap<Integer, Integer>());
            visited.get(currPos).putIfAbsent(lastJump, 0);
            if (visited.get(currPos).get(lastJump) == 1)
                continue;
            visited.get(currPos).put(lastJump, 1);
            for (int[] spot : this.findNextJumpableSpots(stones, currPos, lastJump)) {
                q.add(spot);
            }
        }
        return false;
    }

    private List<int[]> findNextJumpableSpots(int[] stones, int start, int lastJump) {
        List<int[]> spots = new ArrayList<>();
        int lbIndex = this.lowerBound(stones, start + 1, stones.length, start + lastJump - 1);
        if (lbIndex != -1) {
            for (int i = 0; i < 3 && lbIndex < stones.length; i++, lbIndex++) {
                if (Math.abs(stones[lbIndex] - stones[start] - lastJump) <= 1) {
                    spots.add(new int[] { lbIndex, stones[lbIndex] - stones[start] });
                }
            }
        }
        return spots;
    }

    // finds the first element greater or equal to the given number
    private int lowerBound(int[] stones, int start, int end, int search) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (stones[mid] < search) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}