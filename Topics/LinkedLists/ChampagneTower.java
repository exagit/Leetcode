package Topics.LinkedLists;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ChampagneTower {
    @Test
    public void test1() {
        int poured = 1, query_row = 1, query_glass = 1;
        double result = this.champagneTower(poured, query_row, query_glass);
        assertEquals(0, result, 0);
    }

    @Test
    public void test2() {
        int poured = 2, query_row = 1, query_glass = 1;
        double result = this.champagneTower(poured, query_row, query_glass);
        assertEquals(0.5, result, 0);
    }

    @Test
    public void test3() {
        int poured = 100000009, query_row = 33, query_glass = 17;
        double result = this.champagneTower(poured, query_row, query_glass);
        assertEquals(1, result, 0);
    }

    public double champagneTower(int poured, int query_row, int query_glass) {
        /*
         * start with 1 row with entire amount poured into the only glass
         * traverse the entire current row and check if any glass has quantity >1,
         * distribute quantity-1 into next row (create if not exists) two glasses
         * equally.
         * if next row exists jump to next row and continue the step 2 again.
         */
        List<double[]> glassRows = new ArrayList<>();
        double[] firstRow = new double[1];
        firstRow[0] = poured;
        glassRows.add(firstRow);

        int currentRowInd = -1;
        while (glassRows.size() - 1 > currentRowInd) {
            currentRowInd++;
            double[] currRow = glassRows.get(currentRowInd);
            for (int i = 0; i < currRow.length; i++) {
                double currGlassCap = currRow[i];
                if (currGlassCap > 1) {
                    currRow[i] = 1.0;
                }
                double leftOver = currGlassCap - 1;
                if (leftOver > 0) {
                    if (currentRowInd + 1 != 100) {
                        this.createNextRowIfNotExists(glassRows, currentRowInd + 1);
                        double[] nextRow = glassRows.get(currentRowInd + 1);
                        nextRow[i] = nextRow[i] + (leftOver / 2);
                        nextRow[i + 1] = nextRow[i + 1] + (leftOver / 2);
                    }
                }
            }
        }
        if (query_row > glassRows.size() - 1) {
            return 0;
        }
        double[] queriedRow = glassRows.get(query_row);
        return queriedRow[query_glass];
    }

    private void createNextRowIfNotExists(List<double[]> glassRows, int i) {
        if (glassRows.size() - 1 < i) {
            System.out.println("created row " + i);
            glassRows.add(new double[i + 1]);
        }
    }
}