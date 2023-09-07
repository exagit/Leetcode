package Topics.LinkedLists;

import org.junit.Test;

public class ReversePortionOfList {
    ListNode convertToList(int[] input) {
        ListNode head = null;
        ListNode previous = null;
        // Create nodes
        for (int i = 0; i < input.length; i++) {
            ListNode newNode = new ListNode(input[i]);
            if (head == null) {
                head = newNode;
                previous = newNode;
            } else {
                previous.next = newNode;
                previous = newNode;
            }
        }
        return head;
    }

    @Test
    public void test1() {
        int input[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        ListNode head = this.convertToList(input);
        ListNode result = new ReverseLinkedList2().reverseBetween(head, 5, 6);
        this.printList(result);
    }

    private void printList(ListNode list) {
        while (list != null) {
            System.out.print(list.val + "->");
            list = list.next;
        }
        System.out.print("null");
        System.out.println();
    }
}

class ReverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        int counter = 0; // represents the position/index in the list
        ListNode traverser = head;
        ListNode firstHalfEnd = null;
        while (counter + 1 <= left - 1) {
            counter++;
            firstHalfEnd = traverser;
            traverser = traverser.next;
        }
        ListNode portionEnd = traverser;
        // start reversing list from current position till counter reaches right index
        // in list
        ListNode previousPtr = null;
        ListNode nextPtr;
        // at this point counter points to index before left, so we jump to next to
        // point to the start of the portion list
        counter++;
        while (counter <= right) {
            counter++;
            nextPtr = traverser.next;
            traverser.next = previousPtr;
            previousPtr = traverser;
            traverser = nextPtr;
        }
        ListNode portionHead = previousPtr;
        ListNode secondHalfStart = traverser;
        if (firstHalfEnd != null) {
            firstHalfEnd.next = portionHead;
        } else {
            head = portionHead;
        }
        portionEnd.next = secondHalfStart;
        return head;
    }
}