package UberCards.DynamicProgramming;

import java.util.Arrays;
import org.junit.Test;

public class PalendromicSubsequence {
    @Test
    public void testBasic() {
        PalendromicSubsequenceSolution sol = new PalendromicSubsequenceSolution();
        String str = "aaa";
        System.out.println(sol.longestPalindromeSubseq(str));
    }

    @Test
    public void testBasic2() {
        PalendromicSubsequenceSolution sol = new PalendromicSubsequenceSolution();
        String str = "abc";
        System.out.println(sol.longestPalindromeSubseq(str));
    }

    @Test
    public void testBasic3() {
        PalendromicSubsequenceSolution sol = new PalendromicSubsequenceSolution();
        String str = "bbbab";
        System.out.println(sol.longestPalindromeSubseq(str));
    }
}


class PalendromicSubsequenceSolution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int dp[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int counter = 0; counter < n - i; counter++) {
                int start = counter;
                int end = counter + i;
                if (start == end) {
                    dp[start][end] = 1;
                } else if (end - start == 1) {
                    if (s.charAt(start) == s.charAt(end)) {
                        dp[start][end] = 2;
                    } else {
                        dp[start][end] = 1;
                    }
                } else {
                    int max = 0;
                    int diagonalLeft =
                            ((start + 1 < n && end - 1 >= 0) ? dp[start + 1][end - 1] : 0);
                    if (s.charAt(start) == s.charAt(end)) {
                        max = 2 + diagonalLeft;
                    } else {
                        max = diagonalLeft;
                    }
                    int down = (start + 1 < n && end - 1 >= 0) ? dp[start + 1][end] : 0;
                    int left = (end - 1 >= 0) ? dp[start][end - 1] : 0;
                    dp[start][end] =
                            Math.max(max, Math.max(left, down));
                }
            }
        }
        return dp[0][n - 1];
    }

    private void print(int[][] dp) {
        int n = dp.length;
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
    }
}
