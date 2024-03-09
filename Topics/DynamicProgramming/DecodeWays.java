package Topics.DynamicProgramming;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class DecodeWays {
    @Test
    public void test1() {
        String s = "12";
        assertEquals(new DecodeWaysSol().numDecodings(s), 2);
    }

    @Test
    public void test2() {
        String s = "226";
        assertEquals(new DecodeWaysSol().numDecodings(s), 3);
    }

    @Test
    public void test3() {
        String s = "06";
        assertEquals(new DecodeWaysSol().numDecodings(s), 0);
    }

    @Test
    public void test4() {
        String s = "1";
        assertEquals(new DecodeWaysSol().numDecodings(s), 1);
    }

    @Test
    public void test5() {
        String s = "9";

        assertEquals(new DecodeWaysSol().numDecodings(s), 0);
    }
}

class DecodeWaysSol {

    public int numDecodings(String s) {
        int dp[] = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int dp1 = 0, dp2 = 0;
            if (i - 1 >= -1 && this.isValidRepresentation(s.substring(i, i + 1))) {
                dp1 = this.getDp(dp, i - 1);
            }
            if (i - 2 >= -1 && this.isValidRepresentation(s.substring(i - 1, i + 1))) {
                dp2 = this.getDp(dp, i - 2);
            }
            dp[i] = dp1 + dp2;
        }
        return dp[s.length() - 1];
    }

    private int getDp(int[] dp, int i) {
        if (i == -1) {
            return 1;
        }
        return dp[i];
    }

    private boolean isValidRepresentation(String str) {
        try {
            int intR = Integer.parseInt(str);
            boolean containsLeadingZero = String.valueOf(intR).length() != str.length();
            return intR >= 1 && intR <= 26 && !containsLeadingZero;
        } catch (Exception e) {
            return false;
        }
    }

    public int numDecodings2(String s) {
        Map<String, String> encodeMapping = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            char c = (char) ('A' + i);
            encodeMapping.put((i + 1) + "", c + "");
        }
        int dp[] = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i - 2; j < i; j++) {
                if (j + 1 < 0) {
                    continue;
                }
                String subs = s.substring(j + 1, i + 1);
                if (encodeMapping.containsKey(subs)) {
                    if (j < 0) {
                        dp[i] += 1;
                    } else {
                        dp[i] += dp[j];
                    }
                }
            }
        }
        return dp[s.length() - 1];
    }
}
