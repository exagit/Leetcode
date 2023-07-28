package Topics.Matrix;

import java.util.Arrays;
import org.junit.Test;

public class RotateMatrix {
    @Test
    public void test1() {
        int matrix[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        new RotateMatrixSol().rotate(matrix);
        for (int row = 0; row < matrix.length; row++)
            System.out.println(Arrays.toString(matrix[row]));
    }

    @Test
    public void test2() {
        int matrix[][] = { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };
        new RotateMatrixSol().rotate(matrix);
        for (int row = 0; row < matrix.length; row++)
            System.out.println(Arrays.toString(matrix[row]));
    }
}

/*
 * 
 * 1 2 3 4 5 6 7 8 9
 * 
 * 
 * l = 0 to n-2*l>0 l++
 * 
 * i [l...n-l)
 * 
 * 
 * rotate i,j 4 times
 * 
 */


class RotateMatrixSol {
    public void rotate(int[][] matrix) {
        int n = matrix[0].length;
        for (int l = 0; n - 2 * l > 0; l++) {
            for (int j = l; j < n - l - 1; j++) {
                int ri = l;
                int rj = j;
                int swapOutValue = matrix[ri][rj];
                for (int c = 0; c < 4; c++) {
                    int nri = rj;
                    int nrj = n - 1 - ri;
                    swapOutValue = this.swap(matrix, nri, nrj, swapOutValue);
                    ri = nri;
                    rj = nrj;
                }
                System.out.printf("l: %d, i: %d, j: %d\n", l, ri, rj);
                this.print(matrix);
            }
        }
        return;
    }

    private void print(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++)
            System.out.println(Arrays.toString(matrix[row]));
        System.out.println();
    }

    private int swap(int[][] matrix, int nri, int nrj, int valueToSwap) {
        int swapOutValue = matrix[nri][nrj];
        matrix[nri][nrj] = valueToSwap;
        return swapOutValue;
    }
}
