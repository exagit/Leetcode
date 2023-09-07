package Topics.Strings;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;

import org.junit.Test;

public class Interleave {
    @Test
    public void test1() {
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        assertEquals(true, new InterleaveString().isInterleave(s1, s2, s3));
    }

    @Test
    public void test2() {
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc";
        assertEquals(false, new InterleaveString().isInterleave(s1, s2, s3));
    }

    @Test
    public void test3() {
        String s1 = "cbcccbabbccbbcccbbbcabbbabcababbbbbbaccaccbabbaacbaabbbc",
                s2 = "abcbbcaababccacbaaaccbabaabbaaabcbababbcccbbabbbcbbb",
                s3 = "abcbcccbacbbbbccbcbcacacbbbbacabbbabbcacbcaabcbaaacbcbbbabbbaacacbbaaaabccbcbaabbbaaabbcccbcbabababbbcbbbcbb";
        assertEquals(true, new InterleaveString().isInterleave(s1, s2, s3));
    }
}

class InterleaveString {

    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        int o = s3.length();
        if (m + n != o) {
            return false;
        }
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { 0, 0, 0 });
        int visited[][][] = new int[o][o][o];
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int firstIndex = curr[0];
            int secondIndex = curr[1];
            int thirdIndex = curr[2];
            if (firstIndex == m && secondIndex == n && thirdIndex == o) {
                return true;
            }
            if (visited[firstIndex][secondIndex][thirdIndex] == 1) {
                continue;
            }
            visited[firstIndex][secondIndex][thirdIndex] = 1;
            char charAtFirst = firstIndex < m ? s1.charAt(firstIndex) : '0';
            char charAtSecond = secondIndex < n ? s2.charAt(secondIndex) : '0';
            if (thirdIndex == o) {
                continue;
            }
            char charAtThird = s3.charAt(thirdIndex);
            if (charAtFirst != '0' && charAtFirst == charAtThird && charAtSecond != '0'
                    && charAtSecond == charAtThird) {
                queue.offer(new int[] { firstIndex + 1, secondIndex, thirdIndex + 1 });
                queue.offer(new int[] { firstIndex, secondIndex + 1, thirdIndex + 1 });
                continue;
            }
            if (charAtFirst != '0' && charAtFirst == charAtThird) {
                queue.offer(new int[] { firstIndex + 1, secondIndex, thirdIndex + 1 });
                continue;
            }
            if (charAtSecond != '0' && charAtSecond == charAtThird) {
                queue.offer(new int[] { firstIndex, secondIndex + 1, thirdIndex + 1 });
                continue;
            }
        }
        return false;
    }
}