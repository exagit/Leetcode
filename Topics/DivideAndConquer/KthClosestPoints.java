package Topics.DivideAndConquer;

import java.util.Arrays;
import org.junit.Test;

public class KthClosestPoints {
    @Test
    public void test1() {
        int[][] points = { { 1, 3 }, { -2, 2 } };
        KthClosestPointsSol sol = new KthClosestPointsSol();
        int[][] res = sol.kClosest(points, 1);
        for (int[] respoints : res) {
            System.out.println(Arrays.toString(respoints));
        }
    }

    @Test
    public void test2() {
        int[][] points = { { 3, 3 }, { 5, -1 }, { -2, 4 } };
        KthClosestPointsSol sol = new KthClosestPointsSol();
        int[][] res = sol.kClosest(points, 3);
        for (int[] respoints : res) {
            System.out.println(Arrays.toString(respoints));
        }
    }

    @Test
    public void test3() {
        int[][] points = { { -2, -6 }, { -7, -2 }, { -9, 6 }, { 10, 3 }, { -8, 1 }, { 2, 8 } };
        KthClosestPointsSol sol = new KthClosestPointsSol();
        int[][] res = sol.kClosest(points, 5);
        for (int[] respoints : res) {
            System.out.println(Arrays.toString(respoints));
        }
    }
}


class KthClosestPointsSol {
    public int[][] kClosest(int[][] points, int k) {
        return this.findKClosest(points, 0, points.length - 1, k);
    }

    private int[][] findKClosest(int[][] points, int start, int end, int k) {
        if (start > end) {
            return null;
        }
        int pivotRank = this.pivot(points, start, end);
        if (pivotRank == k) {
            return Arrays.copyOfRange(points, 0, start + pivotRank);
        }
        if (k < pivotRank) {
            return this.findKClosest(points, start, start + pivotRank - 1, k);
        } else {
            return this.findKClosest(points, start + pivotRank, end, k - pivotRank);
        }
    }

    private int pivot(int[][] arr, int start, int end) {
        int pivotIndex = start;
        for (int i = start; i <= end; i++) {
            if (this.getDistance(arr[i]) < this.getDistance(arr[pivotIndex])) {
                // nums[i] needs to be moved to left of pivot
                int[] temp = arr[i];
                arr[i] = arr[pivotIndex + 1];
                arr[pivotIndex + 1] = arr[pivotIndex];
                arr[pivotIndex] = temp;
                pivotIndex++;
            }
        }
        return pivotIndex - start + 1;
    }

    private double getDistance(int[] point) {
        int x = point[0];
        int y = point[1];
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
