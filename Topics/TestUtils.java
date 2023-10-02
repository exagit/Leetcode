package Topics;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static void assertTwoDimArray(int[][] expected, int[][] actual) {
        int ei = expected.length;
        int ej = expected[0].length;
        int ai = actual.length;
        int aj = actual[0].length;
        assertEquals("Row count mismatch", ei, ai);
        assertEquals("Column count mismatch", ej, aj);
        for (int i = 0; i < ei; i++) {
            for (int j = 0; j < ej; j++) {
                assertEquals(String.format("Cell value mismatch at column i: %d and j: %d", i, j), expected[i][j],
                        actual[i][j]);
            }
        }
    }

    public static List<List<Integer>> convert2dArrayToNestedList(int[][] array) {
        List<List<Integer>> finalList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            finalList.add(new ArrayList<>());
            for (int j = 0; j < array[i].length; j++) {
                finalList.get(i).add(Integer.valueOf(array[i][j]));
            }
        }
        return finalList;
    }
}
