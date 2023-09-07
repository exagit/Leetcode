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
    public int minTaps(int n, int[] ranges) {
        List<int[]> tapRanges = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            if (ranges[i] == 0)
                continue;
            int rangeStart = i - ranges[i];
            rangeStart = rangeStart < 0 ? 0 : rangeStart;
            int rangeEnd = i + ranges[i];
            rangeEnd = rangeEnd > n ? n : rangeEnd;
            tapRanges.add(new int[] { rangeStart, rangeEnd, i });
        }
        tapRanges.sort((a, b) -> a[0] - b[0]);

        int result = 0;
        List<int[]> rangeCovered = new ArrayList<>();
        int[] maxTapFinishEvent = tapRanges.get(0);
        int[] firstTapFinishEvent = tapRanges.get(0);
        int[] currTapEvent = null;
        if (firstTapFinishEvent[0] != 0)
            return -1;
        for (int i = 1; i < tapRanges.size(); i++) {
            currTapEvent = tapRanges.get(i);
            if (currTapEvent[0] <= firstTapFinishEvent[1]) {
                maxTapFinishEvent = this.maxFinishEvent(maxTapFinishEvent, currTapEvent);
            } else {
                if (currTapEvent[0] > firstTapFinishEvent[1] + 1)
                    return -1;
                // If first and max tap finish events belong to the same tap increment the total
                // taps required by 1 else by 2.
                if (maxTapFinishEvent[2] == firstTapFinishEvent[2]) {
                    result += 1;
                    maxTapFinishEvent = currTapEvent;
                    firstTapFinishEvent = currTapEvent;
                } else {
                    if (firstTapFinishEvent[0] == maxTapFinishEvent[0]) {
                        result += 1;
                    } else {
                        result += 2;
                    }
                    firstTapFinishEvent = maxTapFinishEvent;
                }
                rangeCovered.add(new int[] { firstTapFinishEvent[0], maxTapFinishEvent[1] });

                // while (currTapEvent[0] <= maxTapFinishEvent[1]) {
                // i++;
                // if (i >= tapRanges.size())
                // break;
                // currTapEvent = tapRanges.get(i);
                // }
            }
        }

        // If first and max tap finish events belong to the same tap increment the total
        // taps required by 1 else by 2.
        if (maxTapFinishEvent[2] == firstTapFinishEvent[2]) {
            result += 1;
            maxTapFinishEvent = currTapEvent;
            firstTapFinishEvent = currTapEvent;
        } else {
            if (firstTapFinishEvent[0] == maxTapFinishEvent[0]) {
                result += 1;
            } else {
                result += 2;
            }
            firstTapFinishEvent = maxTapFinishEvent;
        }
        rangeCovered.add(new int[] { firstTapFinishEvent[0], maxTapFinishEvent[1] });
        return maxTapFinishEvent[1] != n ? -1 : result;
    }

    private int[] maxFinishEvent(int[] firstTapEvent, int[] secondTapEvent) {
        return firstTapEvent[1] > secondTapEvent[1] ? firstTapEvent : secondTapEvent;
    }
}
