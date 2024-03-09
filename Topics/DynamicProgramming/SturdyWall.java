package Topics.DynamicProgramming;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SturdyWall {
    @Test
    public void test1() {
        int height = 2, width = 3, bricks[] = { 1, 2 };
        assertEquals(2, new SturdyWall().buildWall(height, width, bricks));
    }

    @Test
    public void test2() {
        int height = 1, width = 1, bricks[] = { 5 };
        assertEquals(0, new SturdyWall().buildWall(height, width, bricks));
    }

    public int buildWall(int height, int width, int[] bricks) {
        Map<Integer, List<Integer>> wall = new HashMap<Integer, List<Integer>>();
        return (int) this.findWaysToFill(wall, bricks, 0, height, width);
    }

    private long findWaysToFill(Map<Integer, List<Integer>> wall, int[] bricks, int row, int height, int width) {
        wall.putIfAbsent(row, new ArrayList<>());
        long validCombinations = 0;
        for (int brick : bricks) {
            if (this.isValidBrick(brick, wall, row, width)) {
                this.placeBrickInWall(brick, wall, row);
                Integer currWidth = this.getRowWidth(wall.get(row));
                if (currWidth == width) {
                    if (row == height - 1) {
                        validCombinations += 1;
                    } else {
                        validCombinations += this.findWaysToFill(wall, bricks, row + 1, height, width);
                    }
                    validCombinations = this.modulo(validCombinations);
                } else {
                    validCombinations += this.findWaysToFill(wall, bricks, row, height, width);
                }
                this.removeLastBrickFromWall(wall, row);
            }
        }
        return validCombinations;
    }

    private void placeBrickInWall(int brick, Map<Integer, List<Integer>> wall, int rowNo) {
        List<Integer> currRow = wall.get(rowNo);
        Integer currRowWidth = this.getRowWidth(currRow);
        currRow.add(currRowWidth + brick);
    }

    private Integer getRowWidth(List<Integer> currRow) {
        Integer currRowWidth;
        if (currRow.size() > 0) {
            currRowWidth = currRow.get(currRow.size() - 1);
        } else {
            currRowWidth = 0;
        }
        return currRowWidth;
    }

    private void removeLastBrickFromWall(Map<Integer, List<Integer>> wall, int rowNo) {
        List<Integer> currRow = wall.get(rowNo);
        if (currRow.size() > 0)
            currRow.remove(currRow.size() - 1);
    }

    private boolean isValidBrick(int brick, Map<Integer, List<Integer>> wall, int rowNo, int width) {
        List<Integer> currRow = wall.get(rowNo);
        List<Integer> prevRow = rowNo > 0 ? wall.get(rowNo - 1) : null;
        Integer currRowWidth = this.getRowWidth(currRow);
        if ((currRowWidth + brick == width)
                || (currRowWidth + brick < width && (prevRow == null || !prevRow.contains(currRowWidth + brick)))) {
            return true;
        }
        return false;
    }

    private long modulo(long number) {
        return Math.floorMod(number, (int) Math.pow(10, 9) + 7);
    }
}