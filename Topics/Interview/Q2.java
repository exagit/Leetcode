package Topics.Interview;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.List;

import org.junit.Test;

import Topics.TestUtils;

public class Q2 {

    @Test
    public void test1() {
        List<List<Integer>> maze = TestUtils.convert2dArrayToNestedList(new int[][] { { 0, 0, 0 }, { 1, 0, 0 } });
        int result = Q2.getMinimumMoves(maze, 2);

        assertEquals(2, result);
    }

    @Test
    public void test2() {
        List<List<Integer>> maze = TestUtils.convert2dArrayToNestedList(new int[][] { { 0, 0, 1 }, { 1, 0, 0 } });
        int result = Q2.getMinimumMoves(maze, 2);

        assertEquals(3, result);
    }

    public static int getMinimumMoves(List<List<Integer>> maze, int k) {
        // Write your code here
        int n = maze.size(), m = maze.get(0).size();
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { 0, 0, 0 });
        int visited[][] = new int[n][m];
        while (!queue.isEmpty()) {
            int curr[] = queue.poll();
            if (curr[0] == n - 1 && curr[1] == m - 1) {
                return curr[2];
            }
            int currx = curr[0], curry = curr[1], currDist = curr[2];

            for (int jump = 1; jump < Math.max(n, m); jump++) {
                int[][] neighbors = { { jump, 0 }, { 0, jump }, { -jump, 0 }, { 0, -jump } };
                for (int[] neighbor : neighbors) {
                    int ncurrx = currx + neighbor[0];
                    int ncurry = curry + neighbor[1];
                    if (ncurrx < 0 || ncurrx >= n || ncurry < 0 || ncurry >= m || visited[ncurrx][ncurry] == 1
                            || maze.get(ncurrx).get(ncurry) == 1) {
                        continue;
                    }
                    visited[ncurrx][ncurry] = 1;
                    queue.push(new int[] { currx + neighbor[0], curry + neighbor[1], currDist + 1 });
                }
            }
        }
        return -1;
    }
}