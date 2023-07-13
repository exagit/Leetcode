package UberCards.DynamicProgramming;

import org.junit.Test;

public class JumpGame {
    @Test
    public void test1() {
        int[] nums = { 2, 3, 1, 1, 4 };
        System.out.println(new JumpGameSol().canJump(nums));
    }

    @Test
    public void test2() {
        int[] nums = { 3, 2, 1, 0, 4 };
        System.out.println(new JumpGameSol().canJump(nums));
    }
}


class JumpGameSol {

    public boolean canJump(int[] nums) {
        boolean dp[] = new boolean[nums.length];
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums[i]; j > 0; j--) {
                if (j + i < nums.length && dp[i])
                    dp[j + i] = dp[i];
            }
        }
        return dp[nums.length - 1];
    }
}
