package Topics.Matrix;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

import Topics.TestUtils;

public class ZeroOneMatrix {
    @Test
    public void test1() {
        int matrix[][] = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
        int result[][] = new Matrix01Sol().updateMatrix(matrix);
        TestUtils.assertTwoDimArray(result, new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } });
    }

    @Test
    public void test2() {
        int matrix[][] = { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 1, 1 } };
        int result[][] = new Matrix01Sol().updateMatrix(matrix);
        TestUtils.assertTwoDimArray(result, new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 2, 1 } });
    }

    @Test
    public void test3() {
        int matrix[][] = { { 0, 1 }, { 1, 1 } };
        int result[][] = new Matrix01Sol().updateMatrix(matrix);
        TestUtils.assertTwoDimArray(result, new int[][] { { 0, 1 }, { 1, 2 } });
    }
}

class Matrix01Sol {

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int ans[][] = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = Integer.MAX_VALUE;
            }
        }
        List<int[]> startPoints = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    startPoints.add(new int[] { i, j, 0 });
                }
            }
        }
        this.bfsUpdate(mat, ans, startPoints, m, n);
        return ans;
    }

    private void bfsUpdate(int[][] mat, int[][] ans, List<int[]> startPoints, int m, int n) {
        Queue<int[]> q = new ArrayDeque<>();
        startPoints.forEach(p -> q.offer(p));
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0], y = curr[1], dist = curr[2];
            if (dist < ans[x][y]) {
                ans[x][y] = dist;
                for (int neighbor[] : this.getNeighbors(x, y, m, n)) {
                    int nx = neighbor[0], ny = neighbor[1];
                    if (mat[nx][ny] == 1) {
                        q.offer(new int[] { nx, ny, dist + 1 });
                    }
                }
            }
        }
    }

    private List<int[]> getNeighbors(int i, int j, int m, int n) {
        List<int[]> neighbors = new ArrayList<>();
        if (i - 1 >= 0) {
            neighbors.add(new int[] { i - 1, j });
        }

        if (j - 1 >= 0) {
            neighbors.add(new int[] { i, j - 1 });
        }

        if (i + 1 < m) {
            neighbors.add(new int[] { i + 1, j });
        }

        if (j + 1 < n) {
            neighbors.add(new int[] { i, j + 1 });
        }
        return neighbors;

    }
}