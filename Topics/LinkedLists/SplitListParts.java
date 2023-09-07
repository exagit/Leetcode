package Topics.LinkedLists;

import org.junit.Test;

public class SplitListParts {
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
        ListNode[] result = new SplitLinkedList().splitListToParts(head, 3);
        this.printList(result);
    }

    @Test
    public void test2() {
        int input[] = { 1, 2, 3 };
        ListNode head = this.convertToList(input);
        ListNode[] result = new SplitLinkedList().splitListToParts(head, 5);
        this.printList(result);
    }

    private void printList(ListNode[] listNodeArr) {
        for (ListNode list : listNodeArr) {
            while (list != null) {
                System.out.print(list.val + "->");
                list = list.next;
            }
            System.out.print("null");
            System.out.println();
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class SplitLinkedList {

    public ListNode[] splitListToParts(ListNode head, int k) {
        int length = this.findLength(head);
        int div = length / k;
        int rem = length % k;

        ListNode[] result = new ListNode[k];

        int r = 0;
        ListNode nodePtr = head;
        for (int i = 0; i < k; i++) {
            int listPathLength = div;
            if (i < rem) {
                listPathLength += 1;
            }
            ListNode newList = nodePtr;
            result[r++] = newList;
            nodePtr = this.forwardPtr(nodePtr, listPathLength);
        }
        return result;
    }

    private int findLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    private ListNode forwardPtr(ListNode nodePtr, int forwardLength) {
        if (forwardLength == 0 || nodePtr == null)
            return nodePtr;
        while (forwardLength-- > 1 && nodePtr.next != null) {
            nodePtr = nodePtr.next;
        }
        // reach last node, then save next node to return and return this last node
        ListNode toReturn = nodePtr.next;
        nodePtr.next = null;
        return toReturn;
    }
}