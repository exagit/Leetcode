package UberCards.DynamicProgramming;

import java.util.Arrays;
import org.junit.Test;

public class CombinationSum4 {
    @Test
    public void test1() {
        int nums[] = { 1, 2, 5 }, target = 5;
        /*
         * 1,1,1,1,1 1,1,1,2 1,2,2 5
         */
        CombinationSum4Sol sol = new CombinationSum4Sol();
        System.out.println(sol.combinationSum4(nums, target));
    }

    @Test
    public void test2() {
        int nums[] = { 1, 2, 3 }, target = 4;
        /*
         * 1,1,1,1 1,1,2 2,2 1,3
         * 
         * 1,1,2 2,2
         * 
         */
        CombinationSum4Sol sol = new CombinationSum4Sol();
        System.out.println(sol.combinationSum4(nums, target));
    }
}


class CombinationSum4Sol {

    public int combinationSum4(int[] nums, int target) {
        int dp[] = new int[target + 1];
        dp[0] = 0;
        Arrays.fill(dp, -1);
        return this.combinationSum4Internal(nums, target, dp);
    }

    private int combinationSum4Internal(int[] nums, int target, int dp[]) {
        if (target == 0) {
            return 1;
        }
        if (target < 0) {
            return 0;
        }

        if (dp[target] == -1) {
            int ans = 0;
            for (int i = 0; i < nums.length; i++) {
                ans += this.combinationSum4Internal(nums, target - nums[i], dp);
            }
            dp[target] = ans;
        }
        return dp[target];
    }
}
