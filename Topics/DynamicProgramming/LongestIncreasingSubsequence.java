package Topics.DynamicProgramming;

import java.util.Arrays;
import org.junit.Test;

public class LongestIncreasingSubsequence {
    @Test
    public void testBasic() {
        int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };
        System.out.println(new LISSolution().lengthOfLIS(nums));
    }

    @Test
    public void testBasic2() {
        int[] nums = { 0, 1, 0, 3, 2, 3 };
        System.out.println(new LISSolution().lengthOfLIS(nums));
    }

    @Test
    public void testBasic3() {
        int[] nums = { 1, 3, 6, 7, 9, 4, 10, 5, 6 };
        System.out.println(new LISSolution().lengthOfLIS(nums));
    }
}


class LISSolution {

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int ans = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    ans = Math.max(ans, dp[i]);
                }
            }
        }
        return ans;
    }
}
