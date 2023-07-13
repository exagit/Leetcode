package Topics.Intervals;

import java.util.Arrays;
import java.util.PriorityQueue;
import org.junit.Test;

public class MeetingRooms2 {
    @Test
    public void test1() {
        int[][] meetings = { { 1, 3 }, { 2, 4 }, { 6, 8 }, { 10, 14 }, { 7, 9 } };
        System.out.println(MeetingRooms2Sol.meetingRooms(meetings));
    }

    @Test
    public void test2() {
        int[][] meetings = { { 1, 3 }, { 3, 10 }, { 12, 20 } };
        System.out.println(MeetingRooms2Sol.meetingRooms(meetings));
    }

    @Test
    public void test3() {
        int[][] meetings = { { 1, 3 }, { 5, 8 }, { 10, 19 }, { 15, 20 }, { 9, 9 } };
        System.out.println(MeetingRooms2Sol.meetingRooms(meetings));
    }
}


class MeetingRooms2Sol {
    public static int meetingRooms(int intervals[][]) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> queue = new PriorityQueue<>(10, (a, b) -> a - b);
        int ans = 0;
        for (int i = 0; i < intervals.length; i++) {
            int meetingStart = intervals[i][0];
            while (queue.size() > 0 && queue.peek().intValue() <= meetingStart) {
                queue.poll();
            }
            queue.add(intervals[i][1]);
            ans = Math.max(ans, queue.size());
        }
        return ans;
    }
}
