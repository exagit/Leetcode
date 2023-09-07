package Topics.Strings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RepeatedSubstring {
    @Test
    public void test1() {
        String s = "abab";
        assertEquals(new RSol().repeatedSubstringPattern(s), true);
    }

    @Test
    public void test2() {
        String s = "abc";
        assertEquals(new RSol().repeatedSubstringPattern(s), false);
    }
}

class RSol {

    public boolean repeatedSubstringPattern(String s) {
        for (int subLen = 1; subLen <= s.length() / 2; subLen++) {
            int start = 0;
            int end = start + subLen;
            String subStr = s.substring(start, end);
            do {
                if (!s.substring(start, end).equals(subStr)) {
                    break;
                }
                start = end;
                end = end + subLen;
            } while (end <= s.length());
            if (start == s.length()) {
                return true;
            }
        }
        return false;
    }
}