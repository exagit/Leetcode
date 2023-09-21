package Topics.Arrays;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.PriorityQueue;

import org.junit.Test;

public class MinimizeMaxDiffPairs {
    @Test
    public void test1() {
        int nums[] = { 10, 1, 2, 7, 1, 3 }, p = 2;
        assertEquals(1, new MMDPSol().minimizeMax(nums, p));
    }

    @Test
    public void test2() {
        int nums[] = { 4, 2, 1, 2 }, p = 1;
        assertEquals(0, new MMDPSol().minimizeMax(nums, p));
    }
}

class MMDPSol {
    public int minimizeMax(int[] nums, int p) {
        int n = nums.length;
        if (n <= 1 || p == 0)
            return 0;
        Arrays.sort(nums);
        int dp[][] = new int[n + 1][p + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b) -> b - a);
        return this.minimizeMaxInternal(nums, n, p, 0, maxHeap, dp).intValue();
    }

    private Integer minimizeMaxInternal(int[] nums, int n, int p, int i, PriorityQueue<Integer> maxHeap, int[][] dp) {
        if (p == 0) {
            return maxHeap.peek();
        }
        if (i > n - 2) {
            return Integer.MAX_VALUE;
        }

        if (dp[i][p] == Integer.MAX_VALUE) {
            int thisPair = Math.abs(nums[i + 1] - nums[i]);
            maxHeap.offer(thisPair);
            Integer withThisPair = this.minimizeMaxInternal(nums, n, p - 1, i + 2, maxHeap, dp);
            maxHeap.remove(thisPair);
            Integer withoutThisPair = this.minimizeMaxInternal(nums, n, p, i + 1, maxHeap, dp);
            dp[i][p] = Math.min(dp[i][p], Math.min(withThisPair, withoutThisPair));
        }
        return dp[i][p];
    }
}