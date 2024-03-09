package Topics.Heaps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Test;

class Interval {
    public int start;
    public int end;

    public Interval() {
    }

    public Interval(int _start, int _end) {
        this.start = _start;
        this.end = _end;
    }
};

public class EmployeeFreeIntervals {
    @Test
    public void test1() {
        // ArrayUtil
    }

    /*
     * 
     * lastEndingTime = -Inf
     * if endingTimesMinHeap.isEmpty(){
     * freeTime.push(lastEndingTime, currInterval.startTime);
     * }
     * endingTimesMinHeap= [2,3]
     * lastEndingTime = 3;
     */
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> allIntervals = new ArrayList<>();
        for (List<Interval> employeeSchedule : schedule) {
            for (Interval interval : employeeSchedule) {
                allIntervals.add(interval);
            }
        }

        allIntervals.sort((i1, i2) -> {
            return i1.start - i2.start;
        });

        List<Interval> commonFreeIntervals = new ArrayList<>();
        PriorityQueue<Integer> endingTimesMinHeap = new PriorityQueue<Integer>((i1, i2) -> i1 - i2);
        int lastEndingTime = -1;
        for (Interval interval : allIntervals) {
            while (!endingTimesMinHeap.isEmpty() && endingTimesMinHeap.peek() < interval.start) {
                lastEndingTime = endingTimesMinHeap.poll();
            }
            if (endingTimesMinHeap.isEmpty()) {
                // start of shared someones free time and end of all common free intervals,
                // hence we need to save the free interval till now.
                if (lastEndingTime != -1) {
                    commonFreeIntervals.add(new Interval(lastEndingTime, interval.start));
                }
            }
            endingTimesMinHeap.add(interval.end);
        }
        return commonFreeIntervals;
    }
}
