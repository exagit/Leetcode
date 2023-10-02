package Topics.Strings;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Subsequence {
    @Test
    public void test1() {
        String s = "abc", t = "aebuc";
        assertTrue(this.isSubsequence(s, t));
    }

    @Test
    public void test2() {
        String s = "aba", t = "abaa";
        assertTrue(this.isSubsequence(s, t));
    }

    @Test
    public void test3() {
        String s = "aaa", t = "baa";
        assertFalse(this.isSubsequence(s, t));
    }

    @Test
    public void test4() {
        String s = "rjufvjafbxnbgriwgokdgqdqewn",
                t = "mjmqqjrmzkvhxlyruonekhhofpzzslupzojfuoztvzmmqvmlhgqxehojfowtrinbatjujaxekbcydldglkbxsqbbnrkhfdnpfbuaktupfftiljwpgglkjqunvithzlzpgikixqeuimmtbiskemplcvljqgvlzvnqxgedxqnznddkiujwhdefziydtquoudzxstpjjitmiimbjfgfjikkjycwgnpdxpeppsturjwkgnifinccvqzwlbmgpdaodzptyrjjkbqmgdrftfbwgimsmjpknuqtijrsnwvtytqqvookinzmkkkrkgwafohflvuedssukjgipgmypakhlckvizmqvycvbxhlljzejcaijqnfgobuhuiahtmxfzoplmmjfxtggwwxliplntkfuxjcnzcqsaagahbbneugiocexcfpszzomumfqpaiydssmihdoewahoswhlnpctjmkyufsvjlrflfiktndubnymenlmpyrhjxfdcq";
        assertFalse(this.isSubsequence(s, t));
    }

    /**
     * for string t, make map of character vs occurence index list
     * a - [1,5,9]
     * b-[3,8]
     * c-[2]
     * 
     * for given string s, start with lastcharindex =0
     * search efficiently for the first character index in the list for the
     * character in map >= lastcharindex => return the index of the first character
     * found in t
     * update the value of lastcharindex to the above found index + 1
     * move to the next character and find the second character index and so on..
     * if before reaching end of string s , we don't find the index we return false
     * after reaching end return true
     */
    public boolean isSubsequence(String s, String t) {
        Map<Character, List<Integer>> freqsMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            freqsMap.putIfAbsent(c, new ArrayList<>());
            freqsMap.get(c).add(i);
        }

        int lastcharindex = 0;
        for (char c : s.toCharArray()) {
            List<Integer> freqsArr = freqsMap.get(c);
            if (freqsArr == null) {
                return false;
            }
            int found = this.findGreaterEquals(freqsArr, lastcharindex);
            if (found == -1) {
                return false;
            }
            lastcharindex = found + 1;
        }
        return true;
    }

    private int findGreaterEquals(List<Integer> freqsArr, int lastcharindex) {
        int start = 0, end = freqsArr.size() - 1;
        if (lastcharindex > freqsArr.get(end)) {
            return -1;
        }
        while (start <= end) {
            int mid = (start + end) / 2;
            if (lastcharindex == freqsArr.get(mid)) {
                return freqsArr.get(mid);
            } else if (lastcharindex > freqsArr.get(mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return freqsArr.get(start);
    }
}