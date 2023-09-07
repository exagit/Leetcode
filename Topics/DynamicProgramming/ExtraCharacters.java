package Topics.DynamicProgramming;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class ExtraCharacters {
    @Test
    public void test1() {
        String s = "leetscode", dictionary[] = { "leet", "code", "leetcode" };
        int result = new ECString().minExtraChar(s, dictionary);
        assertEquals(1, result);
    }

    @Test
    public void test2() {
        String s = "sayhelloworld", dictionary[] = { "hello", "world" };
        int result = new ECString().minExtraChar(s, dictionary);
        assertEquals(3, result);
    }
}

/*
 * "leetscode", dictionary = ["leet","code","leetcode"]
 * i=-1;j=0...n-1
 * dp[i]+ substring (i+1, j+1)
 */
class ECString {

    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        int dp[] = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = -1; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[j] = Math.min(dp[j],
                        ((i == -1) ? 0 : dp[i]) + ((this.contains(s.substring(i + 1, j + 1), dictionary)) ? 0 : j - i));
            }
        }
        return dp[n - 1];
    }

    private boolean contains(String substring, String[] dictionary) {
        for (String word : dictionary) {
            if (substring.equals(word)) {
                return true;
            }
        }
        return false;
    }
}