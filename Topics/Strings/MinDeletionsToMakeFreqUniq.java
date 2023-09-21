package Topics.Strings;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class MinDeletionsToMakeFreqUniq {
    @Test
    public void test1() {
        String input = "aab"; // aaa bb cc e -> 3,1 2,2 1,1
        int result = new MDMFQ().minDeletions(input);
        assertEquals(0, result);
    }

    @Test
    public void test2() {
        String input = "aaabbbcc"; // aaa bb cc e -> 3,1 2,2 1,1
        int result = new MDMFQ().minDeletions(input);
        assertEquals(2, result);
    }

    @Test
    public void test3() {
        String input = "ceabaacb"; // aaa bb cc e -> 3,1 2,2 1,1
        int result = new MDMFQ().minDeletions(input);
        assertEquals(2, result);
    }
}

class MDMFQ {
    public int minDeletions(String s) {
        // Step 1: Create a HashMap to store character frequencies
        Map<Character, Integer> charFrequency = new HashMap<>();

        // Step 2: Count character frequencies
        for (char c : s.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        // Step 3: Create a Set to store unique frequencies
        Set<Integer> uniqueFrequencies = new HashSet<>();
        int deletions = 0;

        for (int frequency : charFrequency.values()) {
            while (uniqueFrequencies.contains(frequency)) {
                // If the frequency is already in the set, decrement it until it's unique
                frequency--;
                deletions++; // Increment deletions for each decrement
            }
            if (frequency > 0) {
                uniqueFrequencies.add(frequency);
            }
        }

        return deletions;
    }
}