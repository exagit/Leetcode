package UberCards.DynamicProgramming;

import java.util.Arrays;
import org.junit.Test;

public class EditDistance {

    @Test
    public void basicTest() {
        EditDistanceSolution sol = new EditDistanceSolution();
        String word1 = "horse", word2 = "ros";
        System.out.println(sol.minDistance(word1, word2));
    }

    @Test
    public void basicTest2() {
        EditDistanceSolution sol = new EditDistanceSolution();
        String word1 = "intention", word2 = "execution";
        System.out.println(sol.minDistance(word1, word2));
    }
}


class EditDistanceSolution {
    int insertCost = 1;
    int deleteCost = 1;
    int replaceCost = 1;
    int MAX_VAL = 10000;

    public int minDistance(String word1, String word2) {
        int rows = word1.length();
        int columns = word2.length();
        int dp[][] = new int[rows + 1][columns + 1];
        for (int i = 0; i <= rows; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= columns; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                int replace = dp[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    replace += this.replaceCost;
                }
                int left = this.insertCost + dp[i][j - 1];
                int up = this.deleteCost + dp[i - 1][j];
                dp[i][j] = Math.min(replace, Math.min(left, up));
            }
            // System.out.println("at...");
            // this.printDp(dp);
        }
        return dp[rows][columns];
    }

    private void printDp(int[][] dp) {
        int n = dp.length;
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
    }
}
