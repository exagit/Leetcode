package Topics.Strings;

import org.junit.Test;

public class MinWindowSubstring {
    public String minWindow(String s, String t) {
        int[] tfreq = this.getFreqArr(t);
        int required = this.getUniqueChar(tfreq);
        int lead = -1;
        int back = -1;
        int[] sfreq = new int[130];
        int formed = 0;
        int minWindowSize = Integer.MAX_VALUE;
        int start = -1, end = -1;
        while (++lead < s.length()) {
            char leadChar = s.charAt(lead);
            // update sfreq and formed with window expanded
            if (tfreq[leadChar] > 0) {
                // character is in t;
                sfreq[leadChar]++;
                if (sfreq[leadChar] == tfreq[leadChar]) {
                    formed++;
                }
            }
            while (back < lead && formed >= required) {
                // check if current window back to lead is a candidate substring
                if (lead - back < minWindowSize) {
                    minWindowSize = lead - back;
                    start = lead;
                    end = back;
                }
                // remove back character and update frequencies and formed with window contracted
                back++;
                char backChar = s.charAt(back);
                if (tfreq[backChar] > 0) {
                    sfreq[backChar]--;
                    if (sfreq[backChar] < tfreq[backChar]) {
                        formed--;
                    }
                }
            }
        }
        String result = s.substring(end + 1, start + 1);
        return result.equals("") ? "EMPTY" : result;
    }

    private int getUniqueChar(int[] tfreq) {
        int count = 0;
        for (int i = 0; i < tfreq.length; i++) {
            if (tfreq[i] > 0) {
                count++;
            }
        }
        return count;
    }

    private int[] getFreqArr(String t) {
        int[] freqArr = new int[130];
        for (char c : t.toCharArray()) {
            freqArr[c]++;
        }
        return freqArr;
    }

    @Test
    public void testBasic() {
        MinWindowSubstring sol = new MinWindowSubstring();
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(sol.minWindow(s, t));
    }

    @Test
    public void testBasic2() {
        MinWindowSubstring sol = new MinWindowSubstring();
        String s = "a", t = "aa";
        System.out.println(sol.minWindow(s, t));
    }
}
