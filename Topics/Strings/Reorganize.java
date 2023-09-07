package Topics.Strings;

import static org.junit.Assert.assertEquals;

import java.util.PriorityQueue;

import org.junit.Test;

public class Reorganize {
    @Test
    public void test1() {
        String str = "aab";
        String result = (new Reorg()).reorganizeString(str);
        assertEquals("aba", result);
    }
}

class Reorg {

    public String reorganizeString(String s) {
        int freq[] = this.findFrequencies(s);
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                queue.offer(new int[] { freq[i], 97 + i });
            }
        }
        int lastChar = 95;
        String finalString = "";
        while (!queue.isEmpty()) {
            int[] maxFreqEntry = queue.poll();
            if (maxFreqEntry[1] == lastChar) {
                if (queue.size() == 0) {
                    return "";
                }
                int[] nextMaxEntry = queue.poll();
                int chosenChar = nextMaxEntry[1];
                finalString += ((char) chosenChar) + "";
                nextMaxEntry[0] -= 1;
                if (nextMaxEntry[0] != 0) {
                    queue.offer(nextMaxEntry);
                }
                queue.offer(maxFreqEntry);
                lastChar = chosenChar;
            } else {
                int chosenChar = maxFreqEntry[1];
                finalString += ((char) chosenChar) + "";
                maxFreqEntry[0] -= 1;
                if (maxFreqEntry[0] != 0) {
                    queue.offer(maxFreqEntry);
                }
                lastChar = chosenChar;
            }
        }
        return finalString;
    }

    private int[] findFrequencies(String s) {
        int freq[] = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int charAscii = s.charAt(i) - 97;
            freq[charAscii]++;
        }
        return freq;
    }
}
