package Topics.Strings;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class LongestStringChain {

    @Test
    public void test1() {
        String words[] = { "abcd", "dbqca" };
        assertEquals(1, this.longestStrChain(words));
    }

    @Test
    public void test2() {
        String words[] = { "xbc", "pcxbcf", "xb", "cxbc", "pcxbc" };
        assertEquals(5, this.longestStrChain(words));
    }

    @Test
    public void test3() {
        String words[] = { "a", "b", "ba", "bca", "bda", "bdca" };
        assertEquals(4, this.longestStrChain(words));
    }

    public int longestStrChain(String[] words) {
        Arrays.sort(words, (s1, s2) -> (s1.length() - s2.length()));
        int n = words.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int globalMax = 1;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (this.isSuccessor(words[i], words[j])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    globalMax = Math.max(globalMax, dp[i]);
                }
            }
        }
        return globalMax;
    }

    private boolean isSuccessor(String word, String pred) {
        int wl = word.length() - 1, pl = pred.length() - 1;
        if (wl != pl + 1) {
            return false;
        }
        int maxShift = 1;
        while (wl >= 0 && pl >= 0) {
            if (word.charAt(wl) != pred.charAt(pl) && maxShift == 1) {
                maxShift = 0;
                wl--;
            } else if (word.charAt(wl) == pred.charAt(pl)) {
                wl--;
                pl--;
            } else {
                return false;
            }
        }
        return true;
    }
}