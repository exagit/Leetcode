package UberCards.Trees;

import static org.junit.Assert.assertEquals;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class SerializeNaryTree {

    @Test
    public void basicTest() {
        Codec c = new Codec();
        String inputData = "[1,null,3,2,4,null,5,6]";
        Node n = c.deserialize(inputData);
        String deserialized = c.serialize(n);
        assertEquals(inputData, deserialized);
    }

    @Test
    public void basicTest2() {
        Codec c = new Codec();
        String inputData =
                "[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]";
        Node n = c.deserialize(inputData);
        String deserialized = c.serialize(n);
        assertEquals(inputData, deserialized);
    }

    @Test
    public void basicTest3() {
        Codec c = new Codec();
        String inputData =
                "[]";
        Node n = c.deserialize(inputData);
        String deserialized = c.serialize(n);
        assertEquals(inputData, deserialized);
    }
}


class Codec {
    public String serialize(Node root) {
        if (root == null)
            return "[]";
        ArrayDeque<Node> queue = new ArrayDeque<>();
        List<String> list = new ArrayList<>();
        queue.add(root);
        queue.add(new Node(-1));
        while (!queue.isEmpty()) {
            Node elem = queue.pop();
            if (elem.val == -1) {
                list.add("null");
                continue;
            }
            list.add(elem.val + "");
            if (elem.children != null) {
                for (Node child : elem.children) {
                    queue.add(child);
                }
            }
            queue.add(new Node(-1));
        }
        int lastIndex = list.size() - 1;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) != "null") {
                lastIndex = i + 1;
                break;
            }
        }
        list = list.subList(0, lastIndex);
        return "[" + String.join(",", list) + "]";
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.length() <= 2) {
            return null;
        }
        String strippedData = data.substring(1, data.length() - 1);
        int parentIndex = -1, index = 0;
        Node root = null;
        String[] arr = strippedData.split(",");
        Node[] nodeArr = new Node[arr.length];
        int nodeIndex = 0;
        while (index < arr.length) {
            if (arr[index].equals("null")) {
                parentIndex++;
            } else {
                int nodeVal = Integer.parseInt(arr[index]);
                Node newNode = new Node(nodeVal);
                nodeArr[nodeIndex++] = newNode;
                if (index == 0) {
                    root = newNode;
                } else {
                    if (nodeArr[parentIndex].children == null) {
                        nodeArr[parentIndex].children = new ArrayList<>();
                    }
                    nodeArr[parentIndex].children.add(newNode);
                }
            }
            index++;
        }
        return root;
    }
}


class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
