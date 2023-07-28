package Topics.Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class PacificAtlanticFlow {
    @Test
    public void test1() {
        int[][] heights = { { 1, 2, 2, 3, 5 }, { 3, 2, 3, 4, 4 }, { 2, 4, 5, 3, 1 },
                { 6, 7, 1, 4, 5 }, { 5, 1, 1, 2, 4 } };
        List<List<Integer>> result = new PAFSol().pacificAtlantic(heights);
        result.forEach(r -> System.out.println(r.toString()));
    }

    @Test
    public void test2() {
        int[][] heights = { { 3, 3, 3, 3, 3, 3 }, { 3, 0, 3, 3, 0, 3 }, { 3, 3, 3, 3, 3, 3 } };
        List<List<Integer>> result = new PAFSol().pacificAtlantic(heights);
        result.forEach(r -> System.out.println(r.toString()));
    }
}


class PAFSol {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] coveragePac = new int[m][n];
        int[][] coverageAtl = new int[m][n];

        // Pacific points
        for (int j = 0; j < n; j++) {
            int i = 0;
            this.visitDfs(coveragePac, heights, i, j, m, n);
        }

        for (int i = 1; i < m; i++) {
            int j = 0;
            this.visitDfs(coveragePac, heights, i, j, m, n);
        }

        // Atlantic points
        for (int j = 0; j < n; j++) {
            int i = m - 1;
            this.visitDfs(coverageAtl, heights, i, j, m, n);
        }

        for (int i = 0; i < m - 1; i++) {
            int j = n - 1;
            this.visitDfs(coverageAtl, heights, i, j, m, n);
        }

        return this.findCommonPoints(coverageAtl, coveragePac, m, n);
    }

    private List<List<Integer>> findCommonPoints(int[][] coverageAtl, int[][] coveragePac, int m,
            int n) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (coverageAtl[i][j] == 1 && coveragePac[i][j] == 1) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    private void visitDfs(int[][] coverageMatrix, int[][] heights, int i, int j, int m, int n) {
        if (coverageMatrix[i][j] == 0) {
            coverageMatrix[i][j] = 1;

            List<int[]> neighbors = this.getNeighbors(i, j, m, n);
            for (int[] neighbor : neighbors) {
                int nx = neighbor[0], ny = neighbor[1];
                if (heights[nx][ny] >= heights[i][j]) {
                    this.visitDfs(coverageMatrix, heights, nx, ny, m, n);
                }
            }
        }
    }

    private List<int[]> getNeighbors(int i, int j, int m, int n) {
        List<int[]> neighbors = new ArrayList<>(4);
        if (i + 1 < m) {
            neighbors.add(new int[] { i + 1, j });
        }
        if (i - 1 >= 0) {
            neighbors.add(new int[] { i - 1, j });
        }

        if (j + 1 < n) {
            neighbors.add(new int[] { i, j + 1 });
        }
        if (j - 1 >= 0) {
            neighbors.add(new int[] { i, j - 1 });
        }
        return neighbors;
    }
}
