package Topics.Arrays;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import org.junit.Test;

public class MinimizeMaxDiffPairs {
    @Test
    public void test1() {
        int nums[] = { 10, 1, 2, 7, 1, 3 }, p = 2;
        assertEquals(new MMDPSol().minimizeMax(nums, p), 1);
    }

    @Test
    public void test2() {
        int nums[] = { 4, 2, 1, 2 }, p = 1;
        assertEquals(new MMDPSol().minimizeMax(nums, p), 0);
    }
}


class MMDPSol {
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int n = nums.length;
        int dp[][] = new int[n][p];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return this.minimizeMaxInternal(nums, n, p, 0, dp).intValue();
    }

    private Integer minimizeMaxInternal(int[] nums, int n, int p, int i, int[][] dp) {
        if (p == 0) {
            return 0;
        }
        if (i > n - 2) {
            return Integer.MAX_VALUE;
        }

        if (dp[i][p - 1] == -1) {
            int thisPair = Math.abs(nums[i + 1] - nums[i]);
            Integer withThisPair =
                    Math.max(thisPair,
                            this.minimizeMaxInternal(nums, n, p - 1, i + 2, dp));
            Integer withoutThisPair = this.minimizeMaxInternal(nums, n, p, i + 1, dp);
            dp[i][p - 1] = Math.min(withThisPair, withoutThisPair);
        }
        return dp[i][p - 1];
    }
}
