package Topics.Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Q1 {
    @Test
    public void test1() {
        List<String> words = Arrays.asList(new String[] { "duel", "speed", "dule", "car" });
        List<String> queries = Arrays.asList(new String[] { "deul", "spede" });
        System.out.println(Q1.getSearchResults(words, queries));
    }

    public static List<List<String>> getSearchResults(List<String> words, List<String> queries) {
        // Write your code here
        Collections.sort(words);
        Map<String, List<String>> categorizedAnagrams = Q1.categorizeAnagrams(words);
        List<List<String>> result = new ArrayList<>(queries.size());
        for (String query : queries) {
            List<String> anagrams = categorizedAnagrams.get(Q1.findCanonicalForm(query));
            result.add(anagrams);
        }
        return result;
    }

    private static String findCanonicalForm(String query) {
        char[] charArray = query.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    private static Map<String, List<String>> categorizeAnagrams(List<String> words) {
        Map<String, List<String>> categorized = new HashMap<>();
        for (String word : words) {
            String key = Q1.findCanonicalForm(word);
            categorized.putIfAbsent(key, new ArrayList<>());
            categorized.get(key).add(word);
        }
        return categorized;
    }
}