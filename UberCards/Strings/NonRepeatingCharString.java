package UberCards.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.Test;

public class NonRepeatingCharString {
    public String reorganizeString(String s) {
        Map<Character, Integer> map = new HashMap<>();
        this.findFrequencies(s, map);
        char[] resultArray = null;
        ArrayList<Map.Entry<Character, Integer>> list =
                new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());
        Collections.sort(list, (e1, e2) -> {
            return e1.getValue() - e2.getValue();
        });
        boolean interwoven = false;
        for (Map.Entry<Character, Integer> entry : list) {
            char[] repeatedChars = this.getRepeatedChars(entry.getKey(), entry.getValue());
            if (resultArray == null) {
                resultArray = repeatedChars;
            } else {
                resultArray = this.interweave(repeatedChars, resultArray);
                interwoven = true;
                if (resultArray == null) {
                    return "";
                }
            }
        }
        if (!interwoven) {
            return "";
        }
        return new String(resultArray);
    }

    private void findFrequencies(String s, Map<Character, Integer> map) {
        for (char c : s.toCharArray()) {
            map.putIfAbsent(c, 0);
            map.put(c, map.get(c) + 1);
        }
    }

    private char[] getRepeatedChars(char c, int count) {
        String str = c + "";
        return str.repeat(count).toCharArray();
    }

    private char[] interweave(char[] repeatingArr, char[] array) {
        char result[] = new char[repeatingArr.length + array.length];
        if (array.length < repeatingArr.length - 1) {
            return null;
        }
        int j = 0, k = 0;
        for (int i = 0; i < repeatingArr.length; i++) {
            result[k++] = repeatingArr[i];
            if (j < array.length)
                result[k++] = array[j++];
        }
        while (j < array.length) {
            result[k++] = array[j++];
        }
        return result;
    }

    @Test
    public void testBasic() {
        NonRepeatingCharString sol = new NonRepeatingCharString();
        System.out.println(sol.reorganizeString("aaabbcdddd"));
    }
}
