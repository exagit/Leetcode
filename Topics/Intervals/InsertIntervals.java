package Topics.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import org.junit.Test;

public class InsertIntervals {
    @Test
    public void test1() {
        int[][] intervals = { { 1, 3 }, { 6, 9 } };
        int[] newInterval = { 2, 5 };
        int[][] result = new InsertIntervalsSol().insert(intervals, newInterval);
        for (int[] res : result) {
            System.out.println(Arrays.toString(res));
        }
    }

    @Test
    public void test2() {
        int[][] intervals = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
        int[] newInterval = { 4, 8 };
        int[][] result = new InsertIntervalsSol().insert(intervals, newInterval);
        for (int[] res : result) {
            System.out.println(Arrays.toString(res));
        }
    }
}


class InsertIntervalsSol {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        // each node in queue is value, index denoting start or end, 0 - start, 1 - end
        PriorityQueue<int[]> queue = new PriorityQueue<>(10, (arr1, arr2) -> {
            if (arr1[0] != arr2[0]) {
                return arr1[0] - arr2[0];
            } else {
                return arr1[1] - arr2[1];
            }
        });
        for (int i = 0; i < intervals.length; i++) {
            queue.add(new int[] { intervals[i][0], 0 });
            queue.add(new int[] { intervals[i][1], 1 });
        }
        queue.add(new int[] { newInterval[0], 0 });
        queue.add(new int[] { newInterval[1], 1 });
        List<int[]> finalIntervals = new ArrayList<>();
        Stack<Integer> s = new Stack<>();
        int finalIntervalStart = Integer.MAX_VALUE;
        int finalIntervalEnd = -1;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int type = curr[1];
            if (type == 0) {
                finalIntervalStart = Math.min(finalIntervalStart, curr[0]);
                s.add(curr[0]);
            }
            if (type == 1) {
                finalIntervalEnd = Math.max(finalIntervalEnd, curr[0]);
                s.pop();
            }
            if (s.isEmpty() && finalIntervalStart != Integer.MAX_VALUE && finalIntervalEnd != -1) {
                finalIntervals.add(new int[] { finalIntervalStart, finalIntervalEnd });
                finalIntervalStart = Integer.MAX_VALUE;
                finalIntervalEnd = -1;
            }
        }
        return finalIntervals.toArray(new int[0][0]);
    }
}
