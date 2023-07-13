package Topics.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String id = this.findLettersId(str);
            map.putIfAbsent(id, new ArrayList<>());
            map.get(id).add(str);
        }
        return new ArrayList<>(map.values());
    }

    private String findLettersId(String str) {
        int[] arr = new int[26];
        for (char s : str.toCharArray()) {
            arr[s - 'a']++;
        }
        return Arrays.toString(arr);
    }

    @Test
    public void testAnagrams() {
        GroupAnagrams sol = new GroupAnagrams();
        String strs[] = { "eat", "tea", "tan", "nat", "bat" };
        for (List<String> group : sol.groupAnagrams(strs)) {
            System.out.println(Arrays.asList(group));
        }
    }
}
