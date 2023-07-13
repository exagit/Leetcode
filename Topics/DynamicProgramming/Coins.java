package Topics.DynamicProgramming;

import java.util.Arrays;
import org.junit.Test;

public class Coins {
    @Test
    public void test1() {
        int coins[] = { 1, 2, 5 }, amount = 10;
        System.out.println(new CoinsSol().coinChange(coins, amount));
    }

    @Test
    public void test2() {
        int coins[] = { 1, 2, 5 }, amount = 9;
        System.out.println(new CoinsSol().coinChange(coins, amount));
    }

    @Test
    public void test3() {
        int coins[] = { 1 }, amount = 0;
        System.out.println(new CoinsSol().coinChange(coins, amount));
    }

    @Test
    public void test4() {
        int coins[] = { 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422 }, amount = 9864;
        System.out.println(new CoinsSol().coinChange(coins, amount));
    }
}


class CoinsSol {

    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][10001];
        for (int i = 0; i < coins.length; i++)
            Arrays.fill(dp[i], -1);
        return this.coinChangeRes(coins, coins.length - 1, amount, dp);
    }

    public int coinChangeRes(int[] coins, int n, int amount, int[][] dp) {
        if (amount == 0) {
            return 0;
        }
        if (n < 0) {
            return -1;
        }
        if (dp[n][amount] == -1) {
            int coin = coins[n];
            int includingRes = -1;
            int nonIncludingRes;
            if (amount >= coin) {
                includingRes = this.coinChangeRes(coins, n, amount - coin, dp);
            }
            nonIncludingRes = this.coinChangeRes(coins, n - 1, amount, dp);
            if (includingRes != -1 || nonIncludingRes != -1) {
                if (includingRes == -1) {
                    dp[n][amount] = nonIncludingRes;
                } else if (nonIncludingRes == -1) {
                    dp[n][amount] = 1 + includingRes;
                } else {
                    dp[n][amount] = Math.min(1 + includingRes, nonIncludingRes);
                }
            } else {
                dp[n][amount] = -1;
            }
        }
        return dp[n][amount];
    }
}
