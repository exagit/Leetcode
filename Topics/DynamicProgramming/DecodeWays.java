package Topics.DynamicProgramming;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class DecodeWays {
    @Test
    public void test1() {
        String s = "12";
        /*
         * 1,2
         * 
         * 12
         */
        System.out.println(new DecodeWaysSol().numDecodings(s));
    }

    @Test
    public void test2() {
        String s = "1269";
        /*
         * 1,2,6,9
         * 
         * 12,6,9
         * 
         * 1,26,9
         */

        System.out.println(new DecodeWaysSol().numDecodings(s));
    }

    @Test
    public void test3() {
        String s = "06";
        /*
         * 1,2,6,9
         * 
         * 12,6,9
         * 
         * 1,26,9
         */

        System.out.println(new DecodeWaysSol().numDecodings(s));
    }
}


class DecodeWaysSol {

    public int numDecodings(String s) {
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
