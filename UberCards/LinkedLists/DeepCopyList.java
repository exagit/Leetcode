package UberCards.LinkedLists;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class DeepCopyList {
    @Test
    public void basicCopyTest() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        n1.next = n2;
        n2.next = n3;
        n1.arbitrary = n3;
        DeepCopy sol = new DeepCopy();
        Node n1Copy = sol.deepCopy(n1);
    }
}


class DeepCopy {
    Node deepCopy(Node head) {
        Node curr = head;
        Node rootCopy = null;
        Node prevCopy = null;
        Map<Node, Node> toOld = new HashMap<>();
        Map<Node, Node> toNew = new HashMap<>();
        while (curr != null) {
            Node newCopy = new Node(curr.val);
            newCopy.arbitrary = curr.arbitrary;
            toOld.put(newCopy, curr);
            toNew.put(curr, newCopy);
            if (rootCopy == null) {
                rootCopy = newCopy;
            } else {
                prevCopy.next = newCopy;
            }
            prevCopy = newCopy;
            curr = curr.next;
        }
        curr = rootCopy;
        while (curr != null) {
            curr.arbitrary = toNew.get(curr.arbitrary);
            curr = curr.next;
        }
        return rootCopy;
    }
}


class Node {
    int val;
    Node next;
    Node arbitrary;

    Node(int val) {
        this.val = val;
    }
}
