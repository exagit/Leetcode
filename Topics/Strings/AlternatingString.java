package Topics.Strings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AlternatingString {

    @Test
    public void test1() {
        String str = "0100";
        assertEquals(new AlternatingString().minOperations(str), 1);
    }

    @Test
    public void test2() {
        String str = "01";
        assertEquals(new AlternatingString().minOperations(str), 0);
    }

    @Test
    public void test3() {
        String str = "1111";
        assertEquals(new AlternatingString().minOperations(str), 2);
    }

    public int minOperations(String s) {
        return Math.min(this.minChangesRequired(s, '0'), this.minChangesRequired(s, '1'));
    }

    private int minChangesRequired(String s, char c) {
        int totalChanges = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != c) {
                totalChanges += 1;
            }
            if (c == '0') {
                c = '1';
            } else if (c == '1') {
                c = '0';
            }
        }
        return totalChanges;
    }
}
