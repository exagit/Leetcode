package UberCards.Stacks;

import java.util.PriorityQueue;
import org.junit.Test;

public class MeetingRooms {
    @Test
    public void testBasic() {
        int[][] meetings = { { 0, 30 }, { 5, 10 }, { 15, 20 } };
        MeetingRoomAllocator sol = new MeetingRoomAllocator();
        System.out.println(sol.minMeetingRooms(meetings));
    }

    @Test
    public void testBasic2() {
        int[][] meetings = { { 7, 10 }, { 2, 4 } };
        MeetingRoomAllocator sol = new MeetingRoomAllocator();
        System.out.println(sol.minMeetingRooms(meetings));
    }
}


class MeetingRoomAllocator {
    public int minMeetingRooms(int[][] intervals) {
        /**
         * array of numbers 0th - start/end time 1st - Start or end denotor 0 or 1 2nd - index of
         * meeting
         */
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });
        for (int i = 0; i < intervals.length; i++) {
            int starttime = intervals[i][0];
            int endtime = intervals[i][1];
            queue.add(new int[] { starttime, 0, i });
            queue.add(new int[] { endtime, 1, i });
        }
        int meetingCount = 0;
        int maxRoomsRequired = 0;
        while (!queue.isEmpty()) {
            int[] entry = queue.poll();
            if (entry[1] == 0) {
                meetingCount++;
                if (meetingCount > maxRoomsRequired) {
                    maxRoomsRequired = meetingCount;
                }
            } else {
                meetingCount--;
            }
        }
        return maxRoomsRequired;
    }
}
