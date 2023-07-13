package Topics.LinkedLists;

import java.util.PriorityQueue;
import org.junit.Test;

public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>((l1, l2) -> {
            return l1.val - l2.val;
        });
        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }
        ListNode prevNode = null, head = null;
        while (!queue.isEmpty()) {
            ListNode smallest = queue.poll();
            if (prevNode != null)
                prevNode.next = smallest;
            prevNode = smallest;
            if (head == null) {
                head = smallest;
            }
            if (smallest.next != null)
                queue.add(smallest.next);
        }
        return head;
    }

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
        int[][] arr = { { 1, 4, 5 }, { 1, 3, 4 }, { 2, 6 } };
        ListNode[] lists = new ListNode[arr.length];
        for (int i = 0; i < arr.length; i++) {
            lists[i] = getListForArray(arr[i]);
        }
        MergeKLists sol = new MergeKLists();
        printList(sol.mergeKLists(lists));
    }
}

