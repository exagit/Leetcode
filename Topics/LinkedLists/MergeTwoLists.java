package Topics.LinkedLists;

import org.junit.Test;

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = null;
        ListNode prevNode = null;
        ListNode smallNode = null;
        while (list1 != null || list2 != null) {
            if (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    smallNode = list1;
                    list1 = list1.next;
                } else {
                    smallNode = list2;
                    list2 = list2.next;
                }
            } else if (list1 == null) {
                smallNode = list2;
                list2 = list2.next;
            } else {
                smallNode = list1;
                list1 = list1.next;
            }
            if (head == null) {
                head = smallNode;
            }
            if (prevNode != null)
                prevNode.next = smallNode;
            prevNode = smallNode;
        }
        return head;
    }
}


public class MergeTwoLists {
    static ListNode getListForArray(int[] arr) {
        ListNode head = null;
        ListNode prev = null;
        for (int a : arr) {
            ListNode node = new ListNode(a, null);
            if (head == null) {
                head = node;
            } else {
                prev.next = node;
            }
            prev = node;
        }
        return head;
    }

    static void printList(ListNode list) {
        while (list != null) {
            System.out.print(list.val + ",");
            list = list.next;
        }
    }

    @Test
    public void testBasic() {
        int[] list1 = { 1, 2, 4 }, list2 = { 1, 3, 4 };
        Solution sol = new Solution();
        ListNode mergedList = sol.mergeTwoLists(getListForArray(list1), getListForArray(list2));
        printList(mergedList);
    }

    @Test
    public void testBasic2() {
        int[] list1 = {}, list2 = {};
        Solution sol = new Solution();
        ListNode mergedList = sol.mergeTwoLists(getListForArray(list1), getListForArray(list2));
        printList(mergedList);
    }

    @Test
    public void testBasic3() {
        int[] list1 = {}, list2 = { 0 };
        Solution sol = new Solution();
        ListNode mergedList = sol.mergeTwoLists(getListForArray(list1), getListForArray(list2));
        printList(mergedList);
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

