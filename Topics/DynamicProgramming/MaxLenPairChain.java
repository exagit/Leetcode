package Topics.DynamicProgramming;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class MaxLenPairChain {
    @Test
    public void test1() {
        int pairs[][] = { { 1, 2 }, { 2, 3 }, { 3, 4 } };
        int result = new MLPC().findLongestChain(pairs);
        assertEquals(2, result);
    }

    @Test
    public void test2() {
        int pairs[][] = { { 1, 2 }, { 7, 8 }, { 4, 5 } };
        int result = new MLPC().findLongestChain(pairs);
        assertEquals(3, result);
    }
}

class MLPC {

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a1, a2) -> {
            return a1[0] - a2[0];
        });
        int len = pairs.length;
        int dp[] = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    max = Math.max(max, 1 + dp[j]);
                }
            }
            dp[i] = max;
        }
        return dp[len - 1];
    }
}