package Topics.Graphs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaximalNetworkGraph {
    @Test
    public void test1() {
        int roads[][] = { { 0, 1 }, { 0, 3 }, { 1, 2 }, { 1, 3 } };
        int result = new MNG().maximalNetworkRank(4, roads);
        assertEquals(4, result);
    }

    @Test
    public void test2() {
        int roads[][] = { { 0, 1 }, { 0, 3 }, { 1, 2 }, { 1, 3 }, { 2, 3 }, { 2, 4 } };
        int result = new MNG().maximalNetworkRank(5, roads);
        assertEquals(5, result);
    }
}

class MNG {

    public int maximalNetworkRank(int n, int[][] roads) {
        int graph[][] = new int[n][n];
        for (int[] road : roads) {
            int a = road[0], b = road[1];
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        int max = 0;
        for (int a = 0; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                max = Math.max(max, this.findTotalDegrees(graph, a, b, n));
            }
        }
        return max;
    }

    private int findTotalDegrees(int[][] graph, int a, int b, int n) {
        int total = 0;
        for (int x = 0; x < n; x++) {
            total += graph[x][a];
        }

        for (int x = 0; x < n; x++) {
            total += graph[b][x];
        }
        total -= graph[a][b];
        return total;
    }
}