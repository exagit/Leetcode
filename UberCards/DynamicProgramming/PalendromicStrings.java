package UberCards.DynamicProgramming;

import org.junit.Test;

public class PalendromicStrings {
    @Test
    public void testBasic() {
        PalendromicStringSolution sol = new PalendromicStringSolution();
        String str = "aaa";
        System.out.println(sol.countSubstrings(str));
    }

    @Test
    public void testBasic2() {
        PalendromicStringSolution sol = new PalendromicStringSolution();
        String str = "abc";
        System.out.println(sol.countSubstrings(str));
    }

    @Test
    public void testBasic3() {
        PalendromicStringSolution sol = new PalendromicStringSolution();
        String str = "aaabbaa";
        System.out.println(sol.countSubstrings(str));
    }
}


class PalendromicStringSolution {
    public int countSubstrings(String s) {
        int n = s.length();
        int dp[][] = new int[n][n];
        int count = 0;
        for (int end = 0; end < n; end++) {
            for (int start = 0; start <= end; start++) {
                if (start == end) {
                    dp[start][end] = 1;
                    count++;
                } else if (end - start == 1 && s.charAt(start) == s.charAt(end)) {
                    dp[start][end] = 1;
                    count++;
                } else {
                    if (s.charAt(start) == s.charAt(end) && dp[start + 1][end - 1] == 1) {
                        dp[start][end] = 1;
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
