package Topics.Graphs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

public class NumberOfIslands {

    @Test
    public void test1() {
        char[][] grid = {
                { '1', '1', '1', '1', '0' },
                { '1', '1', '0', '1', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '0', '0', '0' }
        };

        int result = new NumberOfIslands().numIslands(grid);
        assertEquals(result, 1);
    }

    @Test
    public void test2() {
        char[][] grid = {
                { '1', '1', '0', '0', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '0' }
        };

        int result = new NumberOfIslands().numIslands(grid);
        assertEquals(result, 3);
    }

    public int numIslands(char[][] grid) {
        Set<Integer> visitedCells = new HashSet<Integer>();
        int M = grid.length;
        int N = grid[0].length;
        int islandsCount = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int cellNumber = this.getCellNumber(i, j, M, N);
                if (!visitedCells.contains(cellNumber) && grid[i][j] == '1') {
                    islandsCount++;
                    this.discoverIslandsUsingBfs(grid, i, j, M, N, visitedCells);
                }
            }
        }
        return islandsCount;
    }

    private void discoverIslandsUsingBfs(char[][] grid, int i, int j, int M, int N, Set<Integer> visitedCells) {
        Queue<Integer> q = new ArrayDeque<>();
        int startCellNo = this.getCellNumber(i, j, M, N);
        q.add(startCellNo);
        visitedCells.add(startCellNo);

        while (!q.isEmpty()) {
            int cellNumber = q.poll();
            int cellI = cellNumber / N;
            int cellJ = cellNumber % N;
            int neighbors[][] = {
                    { cellI + 1, cellJ },
                    { cellI - 1, cellJ },
                    { cellI, cellJ + 1 },
                    { cellI, cellJ - 1 }
            };
            for (int[] n : neighbors) {
                if (n[0] >= 0 && n[0] < M && n[1] >= 0 && n[1] < N && grid[n[0]][n[1]] == '1') {
                    int neighborCellNo = this.getCellNumber(n[0], n[1], M, N);
                    if (!visitedCells.contains(neighborCellNo)) {
                        visitedCells.add(neighborCellNo);
                        q.add(neighborCellNo);
                    }
                }
            }

        }
    }

    private Integer getCellNumber(int i, int j, int M, int N) {
        return i * N + j;
    }
}
