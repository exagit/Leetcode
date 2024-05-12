package Topics.Arrays;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.TreeSet;

import org.junit.Test;

public class ExamRoom {
    /*
     * 0 1 2 3 4 5 6 7 8
     * 
     * seat - 0
     * * 1 2 3 4 5 6 7 8
     * 
     * seat - 8
     * * 1 2 3 4 5 6 7 *
     * 
     * seat - 4
     * * 1 2 3 * 5 6 7 *
     * 
     * seat - 2
     * * 1 * 3 * 5 6 7 *
     * 
     * leave(4)
     * * 1 * 3 4 5 6 7 *
     * 
     * seat - 5
     * * 1 * 3 4 * 6 7 *
     */
    @Test
    public void test1() {
        ExamRoomImpl room = new ExamRoomImpl(9);
        assertEquals(0, room.seat());
        assertEquals(8, room.seat());
        assertEquals(4, room.seat());
        assertEquals(2, room.seat());
        room.leave(4);
        assertEquals(5, room.seat());
    }

    /*
     * 0 1 2 3 4 5 6 7
     * seat - 0
     * * 1 2 3 4 5 6 7
     * 
     * seat - 7
     * * 1 2 3 4 5 6 *
     * 
     * seat - 3
     * * 1 2 * 4 5 6 *
     * 
     * seat - 5
     * * 1 2 * 4 * 6 *
     * 
     * leave(3)
     * * 1 2 3 4 * 6 *
     * 
     * seat - 2
     * * 1 * 3 4 * 6 *
     */
    @Test
    public void test2() {
        ExamRoomImpl room = new ExamRoomImpl(8);
        assertEquals(0, room.seat());
        assertEquals(7, room.seat());
        assertEquals(3, room.seat());
        assertEquals(5, room.seat());
        room.leave(3);
        assertEquals(2, room.seat());
    }
}

class ExamRoomImpl {

    TreeSet<Integer> occupied;
    int size;

    public ExamRoomImpl(int n) {
        this.occupied = new TreeSet<>();
        this.size = n;
    }

    public int seat() {
        if (this.occupied.size() == 0) {
            this.occupied.add(0);
            return 0;
        }
        if (this.occupied.size() == 1) {
            this.occupied.add(this.size - 1);
            return this.size - 1;
        }
        int maxclosestDistance = 0;
        int candidate = 0;
        Iterator<Integer> it = this.occupied.iterator();
        int prevOccupiedIndex = it.next();
        while (it.hasNext()) {
            int currOccIndex = it.next();
            int closestDistance = (currOccIndex - prevOccupiedIndex) / 2;
            if (closestDistance > maxclosestDistance) {
                maxclosestDistance = closestDistance;
                candidate = (currOccIndex + prevOccupiedIndex) / 2;
            }
            prevOccupiedIndex = currOccIndex;
        }

        this.occupied.add(candidate);
        return candidate;
    }

    public void leave(int p) {
        this.occupied.remove(p);
    }
}