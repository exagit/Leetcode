package Topics.LinkedLists;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CopyWithRandomPtr {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        public Node(int val, Node random) {
            this.val = val;
            this.next = null;
            this.random = random;
        }
    }

    Node generateList(Integer[][] input) {

        Node[] nodes = new Node[input.length];

        // Create nodes
        for (int i = 0; i < input.length; i++) {
            nodes[i] = new Node(input[i][0]);
        }

        // Connect nodes in a linked list
        for (int i = 0; i < input.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }

        // Assign random pointers based on random_index
        for (int i = 0; i < input.length; i++) {
            Integer randomIndex = input[i][1];
            if (randomIndex != null) {
                nodes[i].random = nodes[randomIndex];
            }
        }

        // The head of the linked list is nodes[0]
        Node head = nodes[0];

        // Now, you have a linked list with random pointers based on the input array
        // You can perform operations on this linked list as needed
        return head;
    }

    Integer[][] linkedListToArray(Node head) {
        if (head == null) {
            return new Integer[0][2];
        }

        // Calculate the length of the linked list
        int length = 0;
        Node current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        // Create the array
        Integer[][] result = new Integer[length][2];
        current = head;

        // Fill the array with [val, random_index] pairs
        for (int i = 0; i < length; i++) {
            result[i][0] = current.val;

            // Calculate the random_index, which is the index of the random node in the
            // linked list
            Node random = current.random;
            Integer randomIndex = null;
            if (random != null) {
                Node temp = head;
                randomIndex = 0;
                while (temp != random) {
                    randomIndex++;
                    temp = temp.next;
                }
            }
            result[i][1] = randomIndex;

            current = current.next;
        }

        return result;
    }

    @Test
    public void test1() {
        Integer[][] listarray = { { 7, null }, { 13, 0 }, { 11, 4 }, { 10, 2 }, { 1, 0 } };
        // Integer[][] listarray = { { 7, null }, { 13, 0 } };
        Node head = this.generateList(listarray);
        Node copiedList = new DeepCopyList().copyRandomList(head);
        Integer[][] copiedListAsArray = this.linkedListToArray(copiedList);
        assertArrayEquals(listarray, copiedListAsArray);
    }

    @Test
    public void test2() {
        Integer[][] listarray = { { 1, 1 }, { 2, 1 } };
        Node head = this.generateList(listarray);
        Node copiedList = new DeepCopyList().copyRandomList(head);
        Integer[][] copiedListAsArray = this.linkedListToArray(copiedList);
        assertArrayEquals(listarray, copiedListAsArray);
    }

    @Test
    public void test3() {
        Integer[][] listarray = { { 3, null }, { 3, 0 }, { 3, null } };
        Node head = this.generateList(listarray);
        Node copiedList = new DeepCopyList().copyRandomList(head);
        Integer[][] copiedListAsArray = this.linkedListToArray(copiedList);
        assertArrayEquals(listarray, copiedListAsArray);
    }

    class DeepCopyList {

        public Node copyRandomList(Node head) {
            Map<Node, Node> mapping = new HashMap<>();
            System.out.println("init orig head");
            this.printList(head);
            Node copiedList = this.simpleCopyWithNext(head, mapping);
            Node copiedHead = copiedList;
            System.out.println("after copying original");
            this.printList(head);
            System.out.println("copied list");
            this.printList(copiedHead);
            System.out.println();
            int i = 0;
            while (copiedList != null) {
                i++;
                // System.out.print(copiedList.next + ":" + copiedList.random + " ");
                copiedList.random = mapping.get(copiedList.random);
                // System.out.println(copiedList.next + ":" + copiedList.random);
                copiedList = copiedList.next;
                this.printList(copiedHead);
            }
            this.printList(head);
            return copiedHead;
        }

        private void printList(Node copiedList) {
            while (copiedList != null) {
                System.out.print(
                        String.format("C-%s:N-%s:R-%s",
                                this.trimClassNameFromRef(copiedList),
                                this.trimClassNameFromRef(copiedList.next),
                                this.trimClassNameFromRef(copiedList.random)));
                copiedList = copiedList.next;
                System.out.print(",");
            }
            System.out.println();
        }

        private String trimClassNameFromRef(Node ref) {
            if (ref == null)
                return "null";
            return ref.toString().replace("Topics.LinkedLists.CopyWithRandomPtr$Node", "");
        }

        private Node simpleCopyWithNext(Node origList, Map<Node, Node> mapping) {
            Node previous = null;
            Node head = null;
            Node curr = origList;
            while (curr != null && !mapping.containsKey(curr)) {
                Node newNode = new Node(curr.val, curr.random);
                mapping.put(curr, newNode);
                if (previous != null) {
                    previous.next = newNode;
                } else {
                    head = newNode;
                }
                previous = newNode;
                curr = curr.next;
            }
            return head;
        }
    }
}
// 35->36,35
// 43->57,35