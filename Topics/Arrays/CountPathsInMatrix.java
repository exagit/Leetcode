package Topics.Arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountPathsInMatrix {
    @Test
    public void test1() {
        int m = 3, n = 2;
        int result = new CountPaths().uniquePaths(m, n);
        assertEquals(3, result);
    }
}

class CountPaths {

    public int uniquePaths(int m, int n) {
        int arr[][] = new int[m][n];
        arr[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j + 1 < n)
                    arr[i][j + 1] += arr[i][j];
                if (i + 1 < m)
                    arr[i + 1][j] += arr[i][j];
            }
        }
        return arr[m - 1][n - 1];
    }
}