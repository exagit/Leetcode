package Topics.DynamicProgramming;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class UniquePath2 {
    @Test
    public void test1() {
        int[][] grid = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
        int result = new UniquePath2Sol().uniquePathsWithObstacles(grid);
        assertEquals(2, result);
    }

    @Test
    public void test2() {
        int[][] grid = { { 0, 1 }, { 0, 0 } };
        int result = new UniquePath2Sol().uniquePathsWithObstacles(grid);
        assertEquals(1, result);
    }
}


class UniquePath2Sol {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] obstacleGridPaths = new int[m][n];
        if (obstacleGrid[0][0] == 0)
            obstacleGridPaths[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] != 1)
                    for (int[] move : this.getNext(i, j, m, n)) {
                        if (obstacleGrid[move[0]][move[1]] != 1) {
                            obstacleGridPaths[move[0]][move[1]] += obstacleGridPaths[i][j];
                        }
                    }
            }
        }
        return obstacleGridPaths[m - 1][n - 1];
    }

    private List<int[]> getNext(int i, int j, int m, int n) {
        List<int[]> nextMoves = new ArrayList<>();
        if (i + 1 < m) {
            nextMoves.add(new int[] { i + 1, j });
        }
        if (j + 1 < n) {
            nextMoves.add(new int[] { i, j + 1 });
        }
        return nextMoves;
    }
}
