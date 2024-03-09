package Topics.Graphs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MaxPerimeter {
    @Test
    public void test1() {
        int[][] matrix = {
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1 },
                { 0, 1, 0, 1, 1 }
        };
        int result = new MPSol().findPerimeter(matrix);
        assertEquals(11, result);
    }
}

class MPSol {

    int findPerimeter(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxPerimeter = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int perimterOfIsland = this.visit(matrix, i, j, m, n);
                maxPerimeter = Math.max(maxPerimeter, perimterOfIsland);
            }
        }
        return maxPerimeter;
    }

    int visit(int[][] matrix, int i, int j, int m, int n) {
        if (matrix[i][j] == 2 || matrix[i][j] == 0) { // either is visited or water cell
            return 0;
        }

        matrix[i][j] = 2; // Mark the cell visited
        int finalPerimeter = 0;
        if (this.isBoundary(matrix, i, j, m, n)) {
            finalPerimeter += 1;
        }
        for (int neighbor[] : this.getNeighbors(i, j, m, n)) {
            finalPerimeter += this.visit(matrix, neighbor[0], neighbor[1], m, n);
        }
        return finalPerimeter;
    }

    boolean isBoundary(int[][] matrix, int i, int j, int m, int n) {
        if (i - 1 < 0 || i + 1 >= m) {
            return true;
        }
        if (j - 1 < 0 || j + 1 >= n) {
            return true;
        }
        for (int[] neighbor : this.getNeighbors(i, j, m, n)) {
            if (matrix[neighbor[0]][neighbor[1]] == 0) {
                return true;
            }
        }
        return false;
    }

    List<int[]> getNeighbors(int i, int j, int m, int n) {
        List<int[]> neighbors = new ArrayList<>();
        if (i - 1 >= 0) {
            neighbors.add(new int[] { i - 1, j });
        }
        if (i + 1 < m) {
            neighbors.add(new int[] { i + 1, j });
        }

        if (j - 1 >= 0) {

            neighbors.add(new int[] { i, j - 1 });
        }
        if (j + 1 < n) {

            neighbors.add(new int[] { i, j + 1 });
        }
        return neighbors;
    }
}
