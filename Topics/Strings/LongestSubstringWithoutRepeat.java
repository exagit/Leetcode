package Topics.Strings;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LongestSubstringWithoutRepeat {
    @Test
    public void test1() {
        String inp = "abcada";
        int result = new LSWRSol().lengthOfLongestSubstring(inp);
        assertEquals(4, result);
    }

    @Test
    public void test2() {
        String inp = "baaaab";
        int result = new LSWRSol().lengthOfLongestSubstring(inp);
        assertEquals(2, result);
    }

    @Test
    public void test3() {
        String inp = "aaa";
        int result = new LSWRSol().lengthOfLongestSubstring(inp);
        assertEquals(1, result);
    }
}


class LSWRSol {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;
        int left = 0, right = 0;
        int freq[] = new int[125];
        int maxWinLen = 0;
        do {
            char newchar = s.charAt(right);
            freq[newchar]++;
            while (freq[newchar] > 1 && left < right) {
                char leftchar = s.charAt(left);
                left++;
                freq[leftchar]--;
            }
            int windowLen = right - left + 1;
            maxWinLen = Math.max(maxWinLen, windowLen);
        } while (++right < s.length());
        return maxWinLen;
    }
}
