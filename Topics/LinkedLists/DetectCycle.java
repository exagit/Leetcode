package Topics.LinkedLists;

public class DetectCycle {

}

class Cycle {

    // uses two pointer approach fast and slow, to check if they land on the same
    // node.
    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;
        ListNode slow = head;
        ListNode fast = head;
        while (slow.next != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}