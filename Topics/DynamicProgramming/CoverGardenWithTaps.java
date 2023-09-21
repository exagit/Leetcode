package Topics.DynamicProgramming;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CoverGardenWithTaps {
    @Test
    public void test1() {
        int n = 5, ranges[] = { 3, 4, 1, 1, 0, 0 };
        int result = new GardenTaps().minTaps(n, ranges);
        assertEquals(1, result);
    }

    @Test
    public void test2() {
        int n = 3, ranges[] = { 0, 0, 0, 0 };
        int result = new GardenTaps().minTaps(n, ranges);
        assertEquals(-1, result);
    }

    @Test
    public void test3() {
        int n = 7, ranges[] = { 1, 2, 1, 0, 2, 1, 0, 1 };
        int result = new GardenTaps().minTaps(n, ranges);
        assertEquals(3, result);
    }

    @Test
    public void test4() {
        int n = 35, ranges[] = { 1, 0, 4, 0, 4, 1, 4, 3, 1, 1, 1, 2, 1, 4, 0, 3, 0, 3, 0, 3, 0, 5, 3, 0, 0, 1, 2, 1, 2,
                4, 3, 0, 1, 0, 5, 2 };
        int result = new GardenTaps().minTaps(n, ranges);
        assertEquals(6, result);
    }
}

class GardenTaps {
    /*
     * sort the range by start,
     * keep track of the range covered till now, if the range covered skips a number
     * return -1
     * starting from the first encountered tap, until you hit its end, keep taking
     * max of all the tap ranges' end
     * after receiving the end of the first tap, check if the max belongs to the
     * same tap, if yes increment the count by one, if no increment the count by 2
     * return the result at the end
     */

    /*
     * maintain two variables,
     * firsttap and secondtap
     * initialize both to first tap range,
     * initialize another variable firstTapEnd
     * while currentTap start < firstTapEnd, keep taking max and store in secondtap
     * var
     * else
     * if firsttap==secondtap => firsttap=secondtap=currtap;
     * }
     */
    public int minTaps(int n, int[] ranges) {
        List<int[]> tapRanges = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            int rangeStart = i - ranges[i];
            rangeStart = rangeStart < 0 ? 0 : rangeStart;
            int rangeEnd = i + ranges[i];
            rangeEnd = rangeEnd > n ? n : rangeEnd;
            tapRanges.add(new int[] { rangeStart, rangeEnd, i });
        }
        tapRanges.sort((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        int result = 0;
        int[] secondTap = tapRanges.get(0);
        int[] firstTap = tapRanges.get(0);
        if (firstTap[0] != 0)
            return -1;
        for (int[] currTap : tapRanges) {
            if (currTap[0] <= firstTap[1]) {
                secondTap = this.maxFinishEvent(secondTap, currTap);
            } else {
                result++;
                System.out.println(firstTap[2]);
                if (currTap[0] > secondTap[1])
                    return -1;
                // If first and max tap finish events belong to the same tap increment the total
                // taps required by 1 else by 2.
                if (secondTap == firstTap) {
                    firstTap = secondTap = currTap;
                } else {
                    // firsttap[0] == secondTap[0] will never be a case because of the way we sort
                    // the tap ranges.
                    firstTap = secondTap;
                }
            }
        }
        System.out.println(firstTap[2]);
        if (firstTap == secondTap) {
            result += 1;
        } else {
            result += 2;
            System.out.println(secondTap[2]);
        }
        if (secondTap[1] != n)
            return -1;

        return result;
    }

    private int[] maxFinishEvent(int[] firstTapEvent, int[] secondTapEvent) {
        if (firstTapEvent[1] == secondTapEvent[1]) {
            return firstTapEvent[0] > secondTapEvent[0] ? secondTapEvent : firstTapEvent;
        }
        return firstTapEvent[1] > secondTapEvent[1] ? firstTapEvent : secondTapEvent;
    }
}
