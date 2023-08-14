package Topics.Strings;

import org.junit.Test;

public class LongestRepeatingCharacter {
    @Test
    public void test1() {
        String inp = "AABABB";
        int result = new LRCSol().characterReplacement(inp, 1);
        System.out.println(result);
    }

    @Test
    public void test2() {
        String inp = "ABAB";
        int result = new LRCSol().characterReplacement(inp, 2);
        System.out.println(result);
    }

    @Test
    public void test3() {
        String inp = "BAAA";
        int result = new LRCSol().characterReplacement(inp, 0);
        System.out.println(result);
    }
}


/*
 * BAAA A-1 B-1+1+1
 */
class LRCSol {
    public int characterReplacement(String s, int k) {
        int[] freq = this.getFrequencies(s);
        int[] invFreq = new int[26];
        int j = -1;
        int finalAns = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            this.updateInvFreq(invFreq, freq, c, 1);
            while (!this.checkIfValidSubstring(invFreq, freq, k)) {
                j++;
                char cToDelete = s.charAt(j);
                this.updateInvFreq(invFreq, freq, cToDelete, -1);
            }
            int candidateSubstringLength = i - j;
            finalAns = Math.max(finalAns, candidateSubstringLength);

        }
        return finalAns;
    }

    private boolean checkIfValidSubstring(int[] invFreq, int[] freq, int k) {
        for (int charInd = 0; charInd < 26; charInd++) {
            if (freq[charInd] != 0 && invFreq[charInd] <= k)
                return true;
        }
        return false;

    }

    private void updateInvFreq(int[] invFreq, int[] freq, char c, int val) {
        for (int charInd = 0; charInd < 26; charInd++) {
            char cArIndex = (char) (charInd + 65);
            if (freq[charInd] != 0 && cArIndex != c) {
                invFreq[charInd] += val;
            }
        }
    }

    private int[] getFrequencies(String s) {
        int res[] = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'A';
            res[c]++;
        }
        return res;
    }
}
